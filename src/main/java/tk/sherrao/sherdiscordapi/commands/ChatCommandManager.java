package tk.sherrao.sherdiscordapi.commands;

import java.util.Map;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;

public class ChatCommandManager extends ApiComponent {

	protected Map<String, ChatCommand> commands;
	
	public ChatCommandManager( DiscordBot bot ) {
		super( bot, "ChatCommandManager" );
		
	}	
	
	@Override
	public void load() {}
	
	@Override
	public void shutdown() {}	
	
	public void registerCommand( ChatCommand command ) { }
	
	public void unregisterCommand( String command ) { }

}