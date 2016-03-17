package com.laytonsmith.abstraction.sponge.events;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.events.MCPlayerChatEvent;
import com.laytonsmith.abstraction.events.MCPlayerJoinEvent;
import com.laytonsmith.abstraction.events.MCPlayerLoginEvent;
import com.laytonsmith.abstraction.events.MCPlayerQuitEvent;
import com.laytonsmith.abstraction.sponge.entities.SpongeMCPlayer;
import org.spongepowered.api.event.message.MessageChannelEvent.Chat;
import org.spongepowered.api.event.network.ClientConnectionEvent.Disconnect;
import org.spongepowered.api.event.network.ClientConnectionEvent.Join;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.text.Text;

import java.util.List;

public class SpongePlayerEvents {

	public class SpongeMCPlayerJoinEvent implements MCPlayerJoinEvent {

		Join join;

		public SpongeMCPlayerJoinEvent(Join event) {
			join = event;
		}

		@Override
		public Join _GetObject() {
			return join;
		}

		@Override
		public String getJoinMessage() {
			return _GetObject().getMessage().toPlain();
		}

		@Override
		public void setJoinMessage(String s) {
			_GetObject().setMessage(Text.of(s));
		}

		@Override
		public MCPlayer getPlayer() {
			return new SpongeMCPlayer(_GetObject().getTargetEntity());
		}
	}

	public class SpongeMCPlayerLoginEvent implements MCPlayerLoginEvent {

		Login login;

		public SpongeMCPlayerLoginEvent(Login event) {
			this.login = event;
		}

		@Override
		public Login _GetObject() {
			return login;
		}

		@Override
		public String getName() {
			return _GetObject().getProfile().getName().orElse(_GetObject().getProfile().getUniqueId().toString());
		}

		@Override
		public String getUniqueId() {
			return _GetObject().getProfile().getUniqueId().toString();
		}

		@Override
		public String getKickMessage() {
			return _GetObject().getMessage().toPlain();
		}

		@Override
		public void setKickMessage(String s) {
			_GetObject().setMessage(Text.of(s));
		}

		@Override
		public String getResult() {
			return null;
		}

		@Override
		public void setResult(String s) {

		}

		@Override
		public String getIP() {
			return _GetObject().getConnection().getAddress().getAddress().getHostAddress();
		}

		@Override
		public String getHostname() {
			return _GetObject().getConnection().getAddress().getHostName();
		}

		@Override
		public MCPlayer getPlayer() {
			return null;
		}
	}

	public static class SpongeMCPlayerQuitEvent implements MCPlayerQuitEvent {

		Disconnect event;

		public SpongeMCPlayerQuitEvent(Disconnect event) {
			this.event = event;
		}

		@Override
		public Disconnect _GetObject() {
			return event;
		}

		@Override
		public String getMessage() {
			return _GetObject().getMessage().toPlain();
		}

		@Override
		public void setMessage(String s) {
			_GetObject().setMessage(Text.of(s));
		}

		@Override
		public SpongeMCPlayer getPlayer() {
			return new SpongeMCPlayer(_GetObject().getTargetEntity());
		}
	}

	public static class SpongeMCPlayerChatEvent implements MCPlayerChatEvent {

		Chat event;

		public SpongeMCPlayerChatEvent(Chat event) {
			this.event = event;
		}

		@Override
		public Chat _GetObject() {
			return event;
		}

		@Override
		public String getMessage() {
			return _GetObject().getMessage().toPlain();
		}

		@Override
		public void setMessage(String s) {
			_GetObject().setMessage(Text.of(s));
		}

		@Override
		public String getFormat() {
			return null;
		}

		@Override
		public void setFormat(String s) {

		}

		@Override
		public List<MCPlayer> getRecipients() {
			return null;
		}

		@Override
		public void setRecipients(List<MCPlayer> list) {

		}

		@Override
		public MCPlayer getPlayer() {
			return null;
		}
	}
}
