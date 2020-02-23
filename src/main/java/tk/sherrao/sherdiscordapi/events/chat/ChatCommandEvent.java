package tk.sherrao.sherdiscordapi.events.chat;

import tk.sherrao.sherdiscordapi.events.AbstractEvent;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public class ChatCommandEvent extends AbstractEvent {

	public ChatCommandEvent( GlobalEventManager manager ) {
		super( manager, "ChatCommandEvent" );
		
	}

}
