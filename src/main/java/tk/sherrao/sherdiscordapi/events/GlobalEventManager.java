package tk.sherrao.sherdiscordapi.events;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;

public class GlobalEventManager extends ApiComponent {

	public GlobalEventManager( DiscordBot bot ) {
		super( bot, "GlobalEventManager" );
		
	}

	@Override
	public void load() {}

	@Override
	public void shutdown() {}
	
	public void registerListener( EventListener listener ) {
		
		
	}
	
}
