package tk.sherrao.sherdiscordapi;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import tk.sherrao.sherdiscordapi.Response.BanResponse;
import tk.sherrao.sherdiscordapi.Response.KickResponse;
import tk.sherrao.sherdiscordapi.Response.MuteResponse;
import tk.sherrao.sherdiscordapi.Response.UnbanResponse;
import tk.sherrao.sherdiscordapi.Response.UnmuteResponse;

public abstract class Server extends ApiComponent {
	
	protected final Guild guild;
	
	public Server( DiscordBot bot, Guild guild ) {
		super( bot, "Server-" + guild.getName() );
		
		this.guild = guild;
		
	}
	
	public abstract MuteResponse mute( Player player );
	public abstract MuteResponse mute( User user );
	public abstract MuteResponse mute( Member member );
	
	public abstract MuteResponse mute( Player player, String reason );
	public abstract MuteResponse mute( User user, String reason );
	public abstract MuteResponse mute( Member member, String reason );
	
	public abstract MuteResponse mute( Player player, String reason, long time );
	public abstract MuteResponse mute( User user, String reason, long time );
	public abstract MuteResponse mute( Member member, String reason, long time );
	
	public abstract UnmuteResponse unmute( Player player );
	public abstract UnmuteResponse unmute( User user );
	public abstract UnmuteResponse unmute( Member member );
	
	public abstract KickResponse kick( Player player );
	public abstract KickResponse kick( User user );
	public abstract KickResponse kick( Member member );
	
	public abstract KickResponse kick( Player player, String reason );
	public abstract KickResponse kick( User user, String reason );
	public abstract KickResponse kick( Member member, String reason );

	public abstract BanResponse ban( Player player );
	public abstract BanResponse ban( User user );
	public abstract BanResponse ban( Member member );
	
	public abstract BanResponse ban( Player player, String reason );
	public abstract BanResponse ban( User user, String reason );
	public abstract BanResponse ban( Member member, String reason );

	public abstract UnbanResponse unban( Player player );
	public abstract UnbanResponse unban( User user );
	public abstract UnbanResponse unban( Member member );
	
	public abstract Member getMember( User user );
	public abstract Member getMember( String name );

	public abstract Player getPlayer( Member member );
	public abstract Player getPlayer( User user );
	public abstract Player getPlayer( String name );
	
	public abstract Guild getGuild();
	public abstract String getIcon();
	public abstract long getCreation();
	public abstract String getId();
	
}