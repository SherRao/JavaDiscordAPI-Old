package tk.sherrao.sherdiscordapi.commands;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.Server;
import tk.sherrao.sherdiscordapi.Player;

public abstract class ConsoleCommand extends ApiComponent {

	private final ConsoleCommandManager manager;
	private final String command;
	private final String usage;
	private final String[] aliases;
	
	public ConsoleCommand( final ConsoleCommandManager manager, final String command, final String usage, final String... aliases ) {
		super( manager.getBot(), manager );
		
		this.manager = manager;
		this.command = command;
		this.usage = usage;
		this.aliases = aliases;

	}
	
	public abstract void onExcute( Player user, Server server, String alias, String... args );
	
	protected ConsoleCommandManager getManager() {
		return manager;
		
	}
	
	public final String getCommandAsString() {
		return command;
		
	}
	
	public final String getUsage() {
		return usage;
		
	}
	
	public final String[] getAliases() {
		return aliases;
		
	}
	
}