package tk.sherrao.sherdiscordapi;

import java.util.List;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;
import net.dv8tion.jda.core.managers.GuildController;
import tk.sherrao.sherdiscordapi.Response.BanResponse;
import tk.sherrao.sherdiscordapi.Response.KickResponse;
import tk.sherrao.sherdiscordapi.Response.MuteResponse;
import tk.sherrao.sherdiscordapi.Response.UnbanResponse;
import tk.sherrao.sherdiscordapi.Response.UnmuteResponse;

class Factory {

	protected static final Player player( DiscordBot bot, User user ) {
		return new Player( bot, user ) {

			@Override
			public PrivateChannel openPM() {
				return user.openPrivateChannel().complete();
			
			}

			@Override
			public MuteResponse mute( Server server ) {
				return server.mute( this );
			
			}

			@Override
			public MuteResponse mute( Server server, String reason ) {
				return server.mute( this, reason );
				
			}

			@Override
			public MuteResponse mute( Server server, String reason, long time ) {
				return server.mute( this, reason, time );
			
			}

			@Override
			public UnmuteResponse unmute( Server server ) {
				return server.unmute( this );
			
			}

			@Override
			public KickResponse kick( Server server ) {
				return server.kick( this );
			
			}

			@Override
			public KickResponse kick( Server server, String reason ) {
				return server.kick( this, reason );
			
			}

			@Override
			public BanResponse ban( Server server ) {
				return server.ban( this );
			
			}

			@Override
			public BanResponse ban( Server server, String reason ) {
				return server.ban( this, reason );
			
			}

			@Override
			public UnbanResponse unban( Server server ) {
				return server.unban( this );
			
			}

			@Override
			public boolean hasPermission( Server server, String permission ) {
				return false;
			
			}

			@Override
			public boolean hasPermission( Server server, Permission permission ) {
				return false;
			
			}

			@Override
			public boolean hasRole( Server server, String role ) {
				return false;
		
			}

			@Override
			public boolean hasRole( Server server, Role role ) {
				return false;
			
			}

			@Override
			public String getDisplayName( Server server ) {
				return null;
				
			}

			@Override
			public String getDiscrim() {
				return user.getDiscriminator();
			
			}

			@Override
			public String getFullDiscordName() {
				return user.getName();
			
			}

			@Override
			public String getAvatar() {
				return user.getEffectiveAvatarUrl();
		
			}

			@Override
			public long getCreation() {
				return user.getCreationTime().toInstant().toEpochMilli();
		
			}

			@Override
			public String getId() {
				return user.getId();
		
			}

			@Override
			public List<Role> getRole( Server server ) {
				return null;
		
			}

			@Override
			public List<Server> getServers() {
				return null;
		
			}

			@Override
			public Member getMember( Server server ) {
				return server.getMember( user );
			
			}

			@Override
			public Member getMember( Guild guild ) {
				return guild.getMember( user );
			
			}

			@Override
			public User getUser() {
				return user;
			
			}
			
		};
	}
	
	
	protected static final Server server( DiscordBot bot, Guild guild ) {
		return new Server( bot, guild ) {

			private GuildController controller;
			
			{
				controller = this.guild.getController();
				
			}
			
			@Override
			public MuteResponse mute( Player player ) {
				return mute( player, null, -1 );
			
			}

			@Override
			public MuteResponse mute( User user ) {
				return mute( user, null, -1 );
			
			}

			@Override
			public MuteResponse mute( Member member ) {
				return mute( member, null, -1 );
			
			}

			@Override
			public MuteResponse mute( Player player, String reason ) {
				return mute( player, reason, -1 );
			
			}

			@Override
			public MuteResponse mute( User user, String reason ) {
				return mute( user, reason, -1 );
			
			}

			@Override
			public MuteResponse mute( Member member, String reason ) {
				return mute( member, reason, -1 );
		
			}
			
			@Override
			public MuteResponse mute( Player player, String reason, long time ) {
				return mute( player.getUser(), reason, time );
		
			}

			@Override
			public MuteResponse mute( User user, String reason, long time ) {
				if( guild.isMember( user ) )
					return mute( guild.getMember( user ), reason, time );
					
				else return MuteResponse.TARGET_NOT_ON_SERVER;
				
			}

			@Override
			public MuteResponse mute( Member member, String reason, long time ) {
				if( !member.getVoiceState().isMuted() ) {
					try {
						controller.setMute( member, true ).complete();
						return MuteResponse.SUCCESS;
						
					} catch( ErrorResponseException e ) { 
						switch( e.getErrorResponse() ) {
							case MISSING_PERMISSIONS:
								return MuteResponse.NO_PERMISSION;
								
							case MISSING_ACCESS:
								return MuteResponse.EXECUTOR_REMOVED_FROM_SERVER;
								
							case UNKNOWN_MEMBER:
								return MuteResponse.TARGET_NOT_ON_SERVER;
							
							default:
								throw new RuntimeException( "wtf did you do?" );
							 
						}
					}
					
				} else return MuteResponse.TARGET_ALREADY_MUTED;
					
			}

			@Override
			public UnmuteResponse unmute( Player player ) {
				return unmute( player.getUser() );
		
			}

			@Override
			public UnmuteResponse unmute( User user ) {
				if( guild.isMember( user ) )
					return unmute( guild.getMember( user ) );
					
				else return UnmuteResponse.TARGET_NOT_ON_SERVER;		
			
			}

			@Override
			public UnmuteResponse unmute( Member member ) {
				if( !member.getVoiceState().isMuted() ) {
					try {
						controller.setMute( member, false ).complete();
						return UnmuteResponse.SUCCESS;
						
					} catch( ErrorResponseException e ) { 
						switch( e.getErrorResponse() ) {
							case MISSING_PERMISSIONS:
								return UnmuteResponse.NO_PERMISSION;
								
							case MISSING_ACCESS:
								return UnmuteResponse.EXECUTOR_REMOVED_FROM_SERVER;
								
							case UNKNOWN_MEMBER:
								return UnmuteResponse.TARGET_NOT_ON_SERVER;
							
							default:
								throw new RuntimeException( "wtf did you do?" );
							 
						}
					}
					
				} else return UnmuteResponse.TARGET_NOT_MUTED;			
			}

			@Override
			public KickResponse kick( Player player ) {
				return kick( player, null );
			
			}

			@Override
			public KickResponse kick( User user ) {
				return kick( user, null );
			
			}

			@Override
			public KickResponse kick( Member member ) {
				return kick( member, null );
		
			}

			@Override
			public KickResponse kick( Player player, String reason ) {
				return kick( player.getUser(), reason );
		
			}

			@Override
			public KickResponse kick( User user, String reason ) {
				if( guild.isMember( user ) )
					return kick( guild.getMember( user ), reason );
					
				else return KickResponse.TARGET_NOT_ON_SERVER;		
			
			}

			@Override
			public KickResponse kick( Member member, String reason ) {
				try {
					controller.kick( member, reason ).complete();
					return KickResponse.SUCCESS;
						
				} catch( ErrorResponseException e ) { 
					switch( e.getErrorResponse() ) {
						case MISSING_PERMISSIONS:
							return KickResponse.NO_PERMISSION;
								
						case MISSING_ACCESS:
							return KickResponse.EXECUTOR_REMOVED_FROM_SERVER;
								
						case UNKNOWN_MEMBER:
							return KickResponse.TARGET_NOT_ON_SERVER;
							
						default:
							throw new RuntimeException( "wtf did you do?" );
							 
					}
				}
			}

			@Override
			public BanResponse ban( Player player ) {
				return ban( player, null );
		
			}

			@Override
			public BanResponse ban( User user ) {
				return ban( user, null );
		
			}

			@Override
			public BanResponse ban( Member member ) {
				return ban( member, null );
		
			}

			@Override
			public BanResponse ban( User user, String reason ) {
				if( guild.isMember( user ) )
					return ban( guild.getMember( user ), reason );
					
				else return BanResponse.TARGET_NOT_ON_SERVER;	
			
			}
			
			@Override
			public BanResponse ban( Player player, String reason ) {
				return ban( player.getUser(), reason );
			
			}

			@Override
			public BanResponse ban( Member member, String reason ) {
				try {
					controller.ban( member, 0, reason ).complete();
					return BanResponse.SUCCESS;
						
				} catch( ErrorResponseException e ) { 
					switch( e.getErrorResponse() ) {
						case MISSING_PERMISSIONS:
							return BanResponse.NO_PERMISSION;
								
						case MISSING_ACCESS:
							return BanResponse.EXECUTOR_REMOVED_FROM_SERVER;
								
						case UNKNOWN_MEMBER:
							return BanResponse.TARGET_NOT_ON_SERVER;
							
						default:
							throw new RuntimeException( "wtf did you do?" );
							 
					}
				}
			}

			@Override
			public UnbanResponse unban( Player player ) {
				return unban( player.getUser() );
			
			}

			@Override
			public UnbanResponse unban( User user ) {
				if( guild.isMember( user ) )
					return unban( guild.getMember( user ) );
					
				else return UnbanResponse.TARGET_NOT_ON_SERVER;				
			
			}

			@Override
			public UnbanResponse unban( Member member ) {
				try {
					controller.unban( member.getUser() ).complete();
					return UnbanResponse.SUCCESS;
						
				} catch( ErrorResponseException e ) { 
					switch( e.getErrorResponse() ) {
						case MISSING_PERMISSIONS:
							return UnbanResponse.NO_PERMISSION;
								
						case MISSING_ACCESS:
							return UnbanResponse.EXECUTOR_REMOVED_FROM_SERVER;
								
						case UNKNOWN_MEMBER:
							return UnbanResponse.TARGET_NOT_ON_SERVER;
							
						default:
							throw new RuntimeException( "wtf did you do?" );
							 
					}
				}
			}

			@Override
			public Member getMember( User user ) {
				return null;
				
			}

			@Override
			public Member getMember( String name ) {
				return null;
			
			}

			@Override
			public Player getPlayer( Member member ) {
				return null;
			
			}

			@Override
			public Player getPlayer( User user ) {
				return null;
			
			}

			@Override
			public Player getPlayer( String name ) {
				return null;
			
			}

			@Override
			public Guild getGuild() {
				return guild;
			
			}

			@Override
			public String getIcon() {
				return guild.getIconUrl();
			
			}

			@Override
			public long getCreation() {
				return guild.getCreationTime().toInstant().toEpochMilli();
			
			}

			@Override
			public String getId() {
				return guild.getId();
			
			}

		};
	}
	
}