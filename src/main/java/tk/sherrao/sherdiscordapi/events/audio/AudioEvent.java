package tk.sherrao.sherdiscordapi.events.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import tk.sherrao.sherdiscordapi.events.AbstractEvent;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public abstract class AudioEvent extends AbstractEvent {

	private AudioTrack track;
	
	public AudioEvent( GlobalEventManager manager, String name, AudioTrack track ) {
		super( manager, name );

		this.track = track;
		
	}
	
	public final AudioTrack getAudioTrack() {
		return track;
		
	}

}