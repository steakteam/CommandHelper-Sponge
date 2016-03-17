package com.laytonsmith.abstraction.sponge;

import com.laytonsmith.abstraction.MCBlockCommandSender;
import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.sponge.SpongeMCCommandSender;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;

public class SpongeMCCommandBlock extends SpongeMCCommandSender implements MCBlockCommandSender {

	CommandBlockSource source;
	public SpongeMCCommandBlock(CommandBlockSource commandBlockSource) {
		super(commandBlockSource);
		this.source = commandBlockSource;
	}

	@Override
	public CommandBlockSource getHandle() {
		return source;
	}

	@Override
	public MCBlock getBlock() {
		return null;
	}
}
