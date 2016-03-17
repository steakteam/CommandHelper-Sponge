
package com.laytonsmith.commandhelper;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.MethodScriptFileLocations;

import java.io.File;

/**
 *
 */
public class CommandHelperFileLocations extends MethodScriptFileLocations {

	private static CommandHelperFileLocations defaultInstance = null;
	private static File configDir;

	public static CommandHelperFileLocations getDefault() {
		if (defaultInstance == null) {
			setDefault(new CommandHelperFileLocations(), new File("./" + Implementation.GetServerType().getBranding()));
		}
		return defaultInstance;
	}

	public static void setDefault(CommandHelperFileLocations provider, File configDirectory) {
		defaultInstance = provider;
		configDir = configDirectory;
		MethodScriptFileLocations.setDefault(defaultInstance);
	}

	@Override
	public File getConfigDirectory() {
		return configDir;
	}
}
