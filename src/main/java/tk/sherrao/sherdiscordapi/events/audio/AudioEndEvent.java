package tk.sherrao.sherdiscordapi.events.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import tk.sherrao.sherdiscordapi.Player;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public class AudioEndEvent extends AudioEvent {

	private final Player player;
	
	public AudioEndEvent( GlobalEventManager manager, AudioTrack track, Player player ) {
		super( manager, "AudioEndEvent", track );
		
		this.player = player;
		
	}
	
	public final Player getPlayer() {
		return player;
		
	}
	
}
