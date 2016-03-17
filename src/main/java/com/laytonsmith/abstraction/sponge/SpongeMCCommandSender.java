package com.laytonsmith.abstraction.sponge;

import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.MCServer;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.List;

public class SpongeMCCommandSender implements MCCommandSender {

	CommandSource source;

	public SpongeMCCommandSender(CommandSource sender) {
		this.source = sender;
	}

	@Override
	public void sendMessage(String s) {
		getHandle().sendMessage(Text.of(s));
	}

	@Override
	public MCServer getServer() {
		return null;
	}

	@Override
	public String getName() {
		return getHandle().getName();
	}

	@Override
	public boolean isOp() {
		return false;
	}

	@Override
	public boolean hasPermission(String s) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String s) {
		return false;
	}

	@Override
	public List<String> getGroups() {
		return null;
	}

	@Override
	public boolean inGroup(String s) {
		return false;
	}

	@Override
	public CommandSource getHandle() {
		return source;
	}
}
