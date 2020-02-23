package tk.sherrao.sherdiscordapi.audio;

import java.net.URL;
import java.util.List;
import java.util.Stack;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;
import tk.sherrao.sherdiscordapi.Server;
import tk.sherrao.sherdiscordapi.audio.AudioAttemptResponse.Pause;
import tk.sherrao.sherdiscordapi.audio.AudioAttemptResponse.Resume;
import tk.sherrao.sherdiscordapi.audio.AudioAttemptResponse.Skip;
import tk.sherrao.sherdiscordapi.audio.AudioAttemptResponse.Stop;

public class AudioHandler extends ApiComponent {

	protected AudioPlayerManager manager;
	protected AudioPlayer player;
	
	protected List<Server> connectedTo;
	
	protected Stack<AudioTrack> queue;
	protected AudioTrack current;
	
	public AudioHandler( DiscordBot bot ) {
		super( bot, "AudioHandler" );
		
		this.manager = new DefaultAudioPlayerManager();
		this.player = manager.createPlayer();
		this.queue = new Stack<>();
		
	}
	
	@Override
	public void load() {
		AudioSourceManagers.registerLocalSource( manager );
		AudioSourceManagers.registerRemoteSources( manager );
		
	}

	@Override
	public void shutdown() {
		
	}
	
	public void disconnect( Server server ) {
		
	}
	
	public void requestSong( String searchQuery ) {}
	
	public void requestSong( URL link ) {}
	
	public Stop attemptStop() {
		return null;
		
	}
	
	public Skip attemptSkip() {
		return null;
		
	}
	
	public Pause attemptPause() {
		return null;
		
	}
	
	public Resume attemptResume() {
		return null;
		
	}

	
}