package tk.sherrao.sherdiscordapi.commands;

import java.util.Map;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;

public class ConsoleCommandManager extends ApiComponent {

	protected Map<String, ChatCommand> commands;
	
	public ConsoleCommandManager( DiscordBot bot ) {
		super( bot, "ConsoleCommandManager" );
		
	}

	@Override
	public void load() {}

	@Override
	public void shutdown() {}
	
	public void registerCommand( ChatCommand command ) { }
	
	public void unregisterCommand( String command ) { }
	
}