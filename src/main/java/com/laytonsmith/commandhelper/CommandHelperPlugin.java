package com.laytonsmith.commandhelper;

import com.google.inject.Inject;
import com.laytonsmith.PureUtilities.ClassLoading.ClassDiscovery;
import com.laytonsmith.PureUtilities.ClassLoading.ClassDiscoveryCache;
import com.laytonsmith.PureUtilities.Common.OSUtils;
import com.laytonsmith.PureUtilities.Common.ReflectionUtils;
import com.laytonsmith.PureUtilities.Common.StreamUtils;
import com.laytonsmith.PureUtilities.Common.StringUtils;
import com.laytonsmith.PureUtilities.ExecutionQueue;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.TermColors;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.MCServer;
import com.laytonsmith.abstraction.StaticLayer;
import com.laytonsmith.abstraction.sponge.SpongeConvertor;
import com.laytonsmith.abstraction.sponge.SpongeMCCommandBlock;
import com.laytonsmith.abstraction.sponge.SpongeMCCommandSender;
import com.laytonsmith.abstraction.sponge.SpongeMCConsole;
import com.laytonsmith.abstraction.sponge.entities.SpongeMCPlayer;
import com.laytonsmith.core.AliasCore;
import com.laytonsmith.core.CHLog;
import com.laytonsmith.core.Installer;
import com.laytonsmith.core.MethodScriptExecutionQueue;
import com.laytonsmith.core.Prefs;
import com.laytonsmith.core.Profiles;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.extensions.ExtensionManager;
import com.laytonsmith.core.profiler.Profiler;
import com.laytonsmith.persistence.PersistenceNetwork;
import org.mcstats.Metrics;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Plugin(id = PomData.GROUP + "." + PomData.ARTIFACT_ID, name = PomData.NAME,
		version = PomData.VERSION, description = PomData.DESCRIPTION)
public class CommandHelperPlugin {

	private static AliasCore ac;
	public static MCServer myServer;
	public static CommandHelperPlugin self;

	@Inject
	private Metrics metrics;

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	public static ExecutorService hostnameLookupThreadPool;
	public static ConcurrentHashMap<String, String> hostnameLookupCache;
	private static int hostnameThreadPoolID = 0;
	public Profiler profiler;
	public final ExecutionQueue executionQueue = new MethodScriptExecutionQueue("CommandHelperExecutionQueue",
			"default"
	);
	public PersistenceNetwork persistenceNetwork;
	public Profiles profiles;
	//public boolean firstLoad = true;
	public long interpreterUnlockedUntil = 0;
	private Thread loadingThread;


	/**
	 * Listener for the plugin system.
	 */
	final CommandHelperPlayerListener playerListener = new CommandHelperPlayerListener(this);
	/**
	 * Interpreter listener
	 */
	public final CommandHelperInterpreterListener interpreterListener = new CommandHelperInterpreterListener(this);

	/**
	 * Server Command Listener, for console commands
	 */
	// final CommandHelperServerListener serverListener = new CommandHelperServerListener();
	@Listener
	public void onLoad(GamePreInitializationEvent event) {
		Implementation.setServerType(Implementation.Type.SPONGE);

		CommandHelperFileLocations.setDefault(new CommandHelperFileLocations(), configDir.toFile());
		CommandHelperFileLocations.getDefault().getCacheDirectory().mkdirs();
		CommandHelperFileLocations.getDefault().getCacheDirectory().mkdirs();

		try {
			Prefs.init(CommandHelperFileLocations.getDefault().getPreferencesFile());
		} catch (IOException ex) {
			Logger.getLogger(CommandHelperPlugin.class.getName()).log(Level.SEVERE, null, ex);
		}

		Prefs.SetColors();
		CHLog.initialize(CommandHelperFileLocations.getDefault().getConfigDirectory());
		Installer.Install(CommandHelperFileLocations.getDefault().getConfigDirectory());
		if (new SimpleVersion(System.getProperty("java.version")).lt(new SimpleVersion("1.7"))) {
			CHLog.GetLogger().w(CHLog.Tags.GENERAL,
					"You appear to be running a version of Java older than Java 7. You should have plans"
							+ " to upgrade at some point, as " + Implementation.GetServerType().getBranding() + " may require it at some point.",
					Target.UNKNOWN
			);
		}

		self = this;

		ClassDiscoveryCache cdc = new ClassDiscoveryCache(CommandHelperFileLocations.getDefault().getCacheDirectory());
		ClassDiscovery.getDefaultInstance().setDebugMode(true);
		cdc.setLogger(Logger.getLogger(CommandHelperPlugin.class.getName()));
		ClassDiscovery.getDefaultInstance().setClassDiscoveryCache(cdc);
		ClassDiscovery.getDefaultInstance().addDiscoveryLocation(ClassDiscovery.GetClassContainer(CommandHelperPlugin.class));
		ClassDiscovery.getDefaultInstance().addDiscoveryLocation(ClassDiscovery.GetClassContainer(Game.class));

		StreamUtils.GetSystemOut().println("[CommandHelper] Running initial class discovery,"
				+ " this will probably take a few seconds...");

		// ReflectionUtils.set(StaticLayer.class, "convertor", new SpongeConvertor()); // ClassDiscovery broke
		myServer = StaticLayer.GetServer();
		StreamUtils.GetSystemOut().println("[CommandHelper] Loading extensions in the background...");

		loadingThread = new Thread("extensionloader") {
			@Override
			public void run() {
				ExtensionManager.AddDiscoveryLocation(CommandHelperFileLocations.getDefault().getExtensionsDirectory());

				if (OSUtils.GetOS() == OSUtils.OS.WINDOWS) {
					// Using StreamUtils.GetSystemOut() here instead of the logger as the logger doesn't
					// immediately print to the console.
					StreamUtils.GetSystemOut().println("[CommandHelper] Caching extensions...");
					ExtensionManager.Cache(CommandHelperFileLocations.getDefault().getExtensionCacheDirectory());
					StreamUtils.GetSystemOut().println("[CommandHelper] Extension caching complete.");
				}

				ExtensionManager.Initialize(ClassDiscovery.getDefaultInstance());
				StreamUtils.GetSystemOut().println("[CommandHelper] Extension loading complete.");
			}
		};

		loadingThread.start();
	}

	@Listener
	public void onEnable(GameInitializationEvent event) {
		if (loadingThread.isAlive()) {
			StreamUtils.GetSystemOut().println("[CommandHelper] Waiting for extension loading to complete...");

			try {
				loadingThread.join();
			} catch (InterruptedException ex) {
				Logger.getLogger(CommandHelperPlugin.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		//Metrics
		Metrics.Graph graph = metrics.createGraph("Player count");
		graph.addPlotter(new Metrics.Plotter("Player count") {

			@Override
			public int getValue() {
				return Static.getServer().getOnlinePlayers().size();
			}
		});
		metrics.addGraph(graph);
		metrics.start();

		try {
			//This may seem redundant, but on a /reload, we want to refresh these
			//properties.
			Prefs.init(CommandHelperFileLocations.getDefault().getPreferencesFile());
		} catch (IOException ex) {
			Logger.getLogger(CommandHelperPlugin.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (Prefs.UseSudoFallback()) {
			Logger.getLogger(CommandHelperPlugin.class.getName()).log(Level.WARNING,
					"In your preferences, use-sudo-fallback is turned on. Consider turning this off if you can."
			);
		}
		CHLog.initialize(CommandHelperFileLocations.getDefault().getConfigDirectory());

		String script_name = Prefs.ScriptName();
		String main_file = Prefs.MainFile();
		boolean showSplashScreen = Prefs.ShowSplashScreen();
		if (showSplashScreen) {
			StreamUtils.GetSystemOut().println(TermColors.reset());
			//StreamUtils.GetSystemOut().flush();
			StreamUtils.GetSystemOut().println("\n\n\n" + Static.Logo());
		}
		ac = new AliasCore(new File(CommandHelperFileLocations.getDefault().getConfigDirectory(), script_name),
				CommandHelperFileLocations.getDefault().getLocalPackagesDirectory(),
				CommandHelperFileLocations.getDefault().getPreferencesFile(),
				new File(CommandHelperFileLocations.getDefault().getConfigDirectory(), main_file), this
		);
		ac.reload(null, null, true);

		//Clear out our hostname cache
		hostnameLookupCache = new ConcurrentHashMap<>();
		//Create a new thread pool, with a custom ThreadFactory,
		//so we can more clearly name our threads.
		hostnameLookupThreadPool = Executors.newFixedThreadPool(3, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "CommandHelperHostnameLookup-" + (++hostnameThreadPoolID));
			}
		});
		for (Player p : Sponge.getServer().getOnlinePlayers()) {
			//Repopulate our cache for currently online players.
			//New players that join later will get a lookup done
			//on them at that time.
			Static.HostnameCache(p.getName(), p.getConnection().getAddress().getHostName());
		}

		//interpreter events
//		registerEvents(interpreterListener);
//		registerEvents(serverListener);

		//Script events
		StaticLayer.Startup(this);

		playerListener.loadGlobalAliases();
//		interpreterListener.reload();

		Static.getLogger().log(Level.INFO, "[CommandHelper] CommandHelper {0} enabled", PomData.VERSION);
	}

	@Listener
	public void onDisable(GameStoppingEvent event) {
		//free up some memory
		StaticLayer.GetConvertor().runShutdownHooks();
		stopExecutionQueue();

		ExtensionManager.Cleanup();

		ac = null;
	}

	public static AliasCore getCore() {
		return ac;
	}

	public void stopExecutionQueue() {
		for (String queue : executionQueue.activeQueues()) {
			executionQueue.clear(queue);
		}
	}

	public void registerEvents(Object listener) {
		// getServer().getPluginManager().registerEvents(listener, this);
	}

	@Listener
	public void onCommand(SendCommandEvent event) {
		Optional<CommandSource> source = event.getCause().get("Source", CommandSource.class);
		if (!source.isPresent()) {
			myServer.broadcastMessage("Skipping command.");
			return;
		}

		MCCommandSender sender;

		if (source.get() instanceof Player) {
			sender = new SpongeMCPlayer((Player) source.get());
		} else if (source.get() instanceof ConsoleSource) {
			sender = new SpongeMCConsole((ConsoleSource) source.get());
		} else if (source.get() instanceof CommandBlockSource) {
			sender = new SpongeMCCommandBlock((CommandBlockSource) source.get());
		} else {
			sender = new SpongeMCCommandSender(source.get());
		}
	}
}
