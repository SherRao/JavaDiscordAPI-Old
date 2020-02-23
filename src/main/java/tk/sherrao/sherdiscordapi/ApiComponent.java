package tk.sherrao.sherdiscordapi;

import tk.sherrao.sherdiscordapi.logging.Logger;

public abstract class ApiComponent {

	protected final DiscordBot bot;
	protected final Logger log;
	private final String name;
	private final String toString;
	
	public ApiComponent( final DiscordBot bot, final String name ) {
		this.bot = bot;
		this.name = name;
		this.log = new Logger( this );
		this.toString = toString( this );
		
	}
	
	public ApiComponent( final DiscordBot bot, final ApiComponent parent ) {
		this.bot = bot;
		this.name = parent.getName();
		this.log = parent.getLogger();
		this.toString = toString( this );
		
	}

	public void load() {}
	
	public void shutdown() {}
	
	public final DiscordBot getBot() { 
		return bot; 
		
	}
	
	public final String getName() {
		return name;
		
	}
	
	public final Logger getLogger() {
		return log;
		
	}

	@Override 
	public final String toString() { 
		return toString; 
		
	}
	
	public static final String toString( ApiComponent component ) { 
		return component.getBot().getName() + "(V" + component.getBot().getVersion() + "): " + component.getName(); 
		
	}
	
}