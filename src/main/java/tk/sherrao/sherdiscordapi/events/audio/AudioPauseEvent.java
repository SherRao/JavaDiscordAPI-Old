package tk.sherrao.sherdiscordapi.events.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import tk.sherrao.sherdiscordapi.Player;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public class AudioPauseEvent extends AudioEvent  {

	private final Player player;
	
	public AudioPauseEvent( GlobalEventManager manager, AudioTrack track, Player player ) {
		super( manager, "AudioPauseEvent", track );
		
		this.player = player;
		
	}
	
	public final Player getPlayer() {
		return player;
		
	}
	
}
