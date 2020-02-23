package tk.sherrao.sherdiscordapi.events.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import tk.sherrao.sherdiscordapi.Player;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public class AudioStopEvent extends AudioEvent {
	
	private final Player player;
	
	public AudioStopEvent( GlobalEventManager manager, AudioTrack track, Player player ) {
		super( manager, "AudioStopEvent", track );
		
		this.player = player;
		
	}
	
	public final Player getPlayer() {
		return player;
		
	}
	
}