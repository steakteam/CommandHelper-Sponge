package com.laytonsmith.commandhelper;

import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.enums.MCChatColor;
import com.laytonsmith.abstraction.sponge.SpongeMCCommandBlock;
import com.laytonsmith.abstraction.sponge.SpongeMCCommandSender;
import com.laytonsmith.abstraction.sponge.SpongeMCConsole;
import com.laytonsmith.abstraction.sponge.entities.SpongeMCPlayer;
import com.laytonsmith.abstraction.sponge.events.SpongeServerEvents;
import com.laytonsmith.core.AliasCore;
import com.laytonsmith.core.InternalException;
import com.laytonsmith.core.Prefs;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.persistence.DataSourceException;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.command.SendCommandEvent;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event listener for Hey0's server mod.
 *
 * @author sk89q
 */
public class CommandHelperPlayerListener {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger("Minecraft");

    /**
     * List of global aliases.
     */
    private AliasCore ac;
    private CommandHelperPlugin plugin;

    public CommandHelperPlayerListener(CommandHelperPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Load global aliases.
     */
    public void loadGlobalAliases() {
        ac = CommandHelperPlugin.getCore();
    }

    /**
     * Find and run aliases for a player for a given command.
     *
     * @param command
     * @return
     */
    public boolean runAlias(String command, MCCommandSender player) throws DataSourceException {
        return CommandHelperPlugin.getCore().alias(command, player);
    }

    /**
     * Called when a player attempts to use a command
     *
     * @param event Relevant event details
     */
    @Listener
    public void onPlayerCommandPreprocess(SendCommandEvent event) {
        Optional<CommandSource> source = event.getCause().get(NamedCause.SOURCE, CommandSource.class);
        if (!source.isPresent()) {
            CommandHelperPlugin.myServer.broadcastMessage("Skipping command.");
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

        if (CommandHelperPlugin.self.interpreterListener
                .isInInterpreterMode(sender.getName())) {
            //They are in interpreter mode, so we want it to handle this, not everything else.
            return;
        }

        SpongeServerEvents.SpongeMCCommandEvent adaptorEvent = new SpongeServerEvents.SpongeMCCommandEvent(event);

        EventUtils.TriggerExternal(adaptorEvent);
        EventUtils.TriggerListener(Driver.PLAYER_COMMAND, "player_command", adaptorEvent);
        if (event.isCancelled()) {
            return;
        }

        if (!Prefs.PlayDirty()) {
            if (event.isCancelled()) {
                return;
            }
        } //If we are playing dirty, ignore the cancelled flag

        String command = '/' + event.getCommand();
        String args = event.getArguments();
        if (StringUtils.isNotEmpty(args)) {
            command = command + ' ' + args;
        }

        try {
            if (runAlias(command, sender)) {
                adaptorEvent.cancel();
            }
        } catch (InternalException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ConfigRuntimeException e) {
            logger.log(Level.WARNING, e.getMessage());
        } catch (Throwable e) {
            sender.sendMessage(MCChatColor.RED + "Command failed with following reason: " + e.getMessage());
            //Obviously the command is registered, but it somehow failed. Cancel the event.
            adaptorEvent.cancel();
            e.printStackTrace();
        }
    }
}