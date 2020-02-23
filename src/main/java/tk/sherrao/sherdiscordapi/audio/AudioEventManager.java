package tk.sherrao.sherdiscordapi.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;
import tk.sherrao.sherdiscordapi.events.audio.AudioPauseEvent;
import tk.sherrao.sherdiscordapi.events.audio.AudioResumeEvent;
import tk.sherrao.sherdiscordapi.events.audio.AudioStartEvent;
import tk.sherrao.sherdiscordapi.events.audio.AudioStopEvent;

public abstract class AudioEventManager extends ApiComponent {

	protected AudioEventAdapter eventListener;
	
	public AudioEventManager( DiscordBot bot, ApiComponent parent ) {
		super( bot, parent );
		
		eventListener = new AudioEventAdapter() {
			
			@Override
			public void onPlayerPause( AudioPlayer player ) { 
				onPause( new AudioPauseEvent( bot.getGlobalEventManager(), player.getPlayingTrack(), null ) );
				
			}

			@Override
			public void onPlayerResume( AudioPlayer player ) {
				onResume( new AudioResumeEvent( bot.getGlobalEventManager(), player.getPlayingTrack(), null ) );
				
			}

			@Override
			public void onTrackStart( AudioPlayer player, AudioTrack track ) {
				onStart( new AudioStartEvent( bot.getGlobalEventManager(), player.getPlayingTrack(), null ) );
				
			}

			@Override
			public void onTrackEnd( AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason ) {
				onStop( new AudioStopEvent( bot.getGlobalEventManager(), player.getPlayingTrack(), null ) );
				
			}

			@Override
			public void onTrackException( AudioPlayer player, AudioTrack track, FriendlyException exception ) {}

			@Override
			public void onTrackStuck( AudioPlayer player, AudioTrack track, long thresholdMs ) {}
			
		};
		
	}
	
	public abstract void onPause( AudioPauseEvent event );
	
	public abstract void onResume( AudioResumeEvent event );

	public abstract void onStart( AudioStartEvent event );
	
	public abstract void onStop( AudioStopEvent event );
	
	public void onStuck() {}
	
	public void onException() {}
	
}