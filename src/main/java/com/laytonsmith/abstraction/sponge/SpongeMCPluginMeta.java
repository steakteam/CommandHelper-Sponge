package com.laytonsmith.abstraction.sponge;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.MCPluginMeta;
import com.laytonsmith.abstraction.pluginmessages.MCMessenger;
import com.laytonsmith.abstraction.pluginmessages.MCPluginMessageListenerRegistration;
import org.spongepowered.api.network.ChannelRegistrar;

import java.util.Set;

/**
 * Created by jb_aero on 3/14/2016.
 */
public class SpongeMCPluginMeta extends MCPluginMeta implements MCMessenger {

	Object plugin;
	ChannelRegistrar channelRegistrar;

	public SpongeMCPluginMeta(Object plugin, ChannelRegistrar registrar) {
		this.plugin = plugin;
		this.channelRegistrar = registrar;
	}

	@Override
	public void closeOutgoingChannel0(String s) {

	}

	@Override
	public void openOutgoingChannel0(String s) {

	}

	@Override
	public void closeIncomingChannel0(String s) {

	}

	@Override
	public void openIncomingChannel0(String s) {

	}

	@Override
	protected void sendIncomingMessage0(MCPlayer mcPlayer, String s, byte[] bytes) {

	}

	@Override
	public MCPluginMessageListenerRegistration registerIncomingPluginChannel(String s) {
		return null;
	}

	@Override
	public boolean isIncomingChannelRegistered(String s) {
		return false;
	}

	@Override
	public void unregisterIncomingPluginChannel(String s) {

	}

	@Override
	public Set<String> getIncomingChannels() {
		return null;
	}

	@Override
	public void closeAllChannels() {

	}
}
