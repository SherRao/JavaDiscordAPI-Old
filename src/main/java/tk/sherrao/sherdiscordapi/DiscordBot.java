package tk.sherrao.sherdiscordapi;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.GameImpl;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import tk.sherrao.sherdiscordapi.audio.AudioHandler;
import tk.sherrao.sherdiscordapi.commands.ChatCommandManager;
import tk.sherrao.sherdiscordapi.commands.ConsoleCommandManager;
import tk.sherrao.sherdiscordapi.events.GlobalEventManager;

public class DiscordBot implements Runnable {

	protected final DiscordBotWrapper wrapper;
	protected final String name;
	protected JDA jda;
	protected boolean useChatCommandManager, useAudioHandler, useConsoleCommandManager;
	protected ChatCommandManager chatCommandManager;
	protected AudioHandler audioHandler;
	protected ConsoleCommandManager consoleCommandManager;
	
	protected boolean useServerCache, usePlayerCache;
	protected Map<String, Server> serverCache;
	protected Map<String, Player> playerCache;
	
	protected final String workspace;
	
	public DiscordBot( DiscordBotWrapper wrapper, String name ) 
			throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException, UnsupportedEncodingException {
		this.wrapper = wrapper;
		this.name = name;
		
		this.workspace = wrapper.workspace;

	} 
	
	public void onLoad() 
			throws Exception {
		useChatCommandManager = true;
		useAudioHandler = true;
		useConsoleCommandManager = true;
		
	}
	
	public void onEnable()
			throws Exception {
		this.jda = new JDABuilder( AccountType.BOT )
				.setAudioEnabled( true )
				.setStatus( OnlineStatus.ONLINE )
				.setEnableShutdownHook( true ) 
				.setToken( wrapper.TOKEN )
				.setIdle( false )
				.setMaxReconnectDelay(32)
				.buildBlocking();
		this.chatCommandManager = new ChatCommandManager( this );
		this.audioHandler = new AudioHandler( this );
		this.consoleCommandManager = new ConsoleCommandManager( this );
		
		this.serverCache = Collections.synchronizedMap( new HashMap<>() );
		this.playerCache = Collections.synchronizedMap( new HashMap<>() );
		
		chatCommandManager.load();
		audioHandler.load();
		consoleCommandManager.load();
		
	}
	
	public void onDisable()
			throws Exception {
		chatCommandManager.shutdown();
		audioHandler.shutdown();
		consoleCommandManager.shutdown();
		
	}

	@Override
	public void run() {
		
		
	}
	
	public void setSubtitle( String subtitle ) {
		this.setSubtitle( subtitle, null );
		
	}
	
	public void setSubtitle( String subtitle, String url ) {
		jda.getPresence().setPresence( OnlineStatus.ONLINE, new GameImpl( subtitle, url, null ) );
		
	}
	
	public String getCurrentWorkspacePath() {
		return workspace;
		
	}
	
	public final String getName() {
		return name;
		
	}
	
	public final String getVersion() {
		return null;
		
	}
	
	public JDA getJDA() {
		return jda;
		
	}
	
	public final ChatCommandManager getChatCommandManager() {
		return chatCommandManager;
		
	}
	
	public final AudioHandler getAudioHandler() {
		return audioHandler;
		
	}
	
	public final ConsoleCommandManager getConsoleCommandManager() {
		return consoleCommandManager;
		
	}
	
	public final GlobalEventManager getGlobalEventManager() {
		return null;
		
	}
	
	public final Server getServer( Guild guild ) {
		return getServer( guild.getName() );
		
	}
	
	public final Server getServer( String name ) {
		String str = name.toLowerCase();
		return serverCache.getOrDefault( str, getInternalServer( str ) );
		
	}
	
	private Server getInternalServer( String name ) {
		for( Guild guild : jda.getGuilds() ) {
			if( guild.getName().equals( name ) ) {
				Server server = Factory.server( this, guild );
				serverCache.put( name, server );
				return server;

			} else
				continue;
			
		}
		
		return null;
		
	}
	
	public final Player getPlayer( Member member ) {
		return getPlayer( member.getUser() );
		
	}
	
	public final Player getPlayer( User user ) {
		return getPlayer( user.getName() );
		
	}
	
	public final Player getPlayer( String name ) {
		String str = name.toLowerCase();
		return playerCache.getOrDefault( str, getInternalPlayer( str ) );
		
	}
	
	private Player getInternalPlayer( String name ) {
		for( User user : jda.getUsers() ) {
			if( user.getName().equals( name ) ) {
				Player player = Factory.player( this, user );
				playerCache.put( name, player );
				return player;
				
			} else
				continue;
			
		}
		
		return null;
		
	}
	
}
