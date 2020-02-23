package tk.sherrao.sherdiscordapi.commands;

import tk.sherrao.sherdiscordapi.ApiComponent;

public abstract class ChatCommand extends ApiComponent {

	private final ChatCommandManager manager;
	private final String command;
	private final String usage;
	private final String[] aliases;
	
	public ChatCommand( final ChatCommandManager manager, final String command, final String usage, final String... aliases ) {
		super( manager.getBot(), manager );
		
		this.manager = manager;
		this.command = command;
		this.usage = usage;
		this.aliases = aliases;

	}
	
	public abstract void onExcute( String alias, String... args );
	
	protected ChatCommandManager getManager() {
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
