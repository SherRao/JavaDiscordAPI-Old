package tk.sherrao.sherdiscordapi.events.chat;

import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.sherdiscordapi.Server;
import tk.sherrao.sherdiscordapi.events.AbstractEvent;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;
import tk.sherrao.sherdiscordapi.Player;

public class ChatEvent extends AbstractEvent {

	protected final Player user;
	protected final Server server;
	protected final TextChannel channel;
	protected final String command;
	
	public ChatEvent( GlobalEventManager manager, final Player user, final Server server, final TextChannel channel, final String command ) {
		super( manager, "ConsoleCommandEvent" );
		
		this.user = user;
		this.server = server;
		this.channel = channel;
		this.command = command;
		
	}
		
	public Player getUser() {
		return user;
	
	}

	public Server getServer() {
		return server;
	
	}
	
	public TextChannel getChannel() {
		return channel;

	}
	
	public String getCommand() {
		return command;
	
	}

}