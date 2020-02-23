package tk.sherrao.sherdiscordapi.events;

import tk.sherrao.sherdiscordapi.ApiComponent;

public abstract class AbstractEvent extends ApiComponent {

	private final GlobalEventManager manager;
	
	public AbstractEvent( final GlobalEventManager manager, final String name ) {
		super( manager.getBot(), manager );
		
		this.manager = manager;
		
	}
	
	public final GlobalEventManager getEventManager() {
		return manager;
		
	}
	
}