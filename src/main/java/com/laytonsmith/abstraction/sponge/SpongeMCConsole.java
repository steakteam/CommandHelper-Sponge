package com.laytonsmith.abstraction.sponge;

import com.laytonsmith.abstraction.MCConsoleCommandSender;
import org.spongepowered.api.command.source.ConsoleSource;

public class SpongeMCConsole extends SpongeMCCommandSender implements MCConsoleCommandSender {

	ConsoleSource console;

	public SpongeMCConsole(ConsoleSource console) {
		super(console);
		this.console = console;
	}

	@Override
	public ConsoleSource getHandle() {
		return console;
	}

	@Override
	public boolean isOp() {
		return true;
	}
}
