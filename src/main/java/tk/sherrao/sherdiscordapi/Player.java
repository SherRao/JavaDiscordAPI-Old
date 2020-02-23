package tk.sherrao.sherdiscordapi;

import java.util.List;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import tk.sherrao.sherdiscordapi.Response.BanResponse;
import tk.sherrao.sherdiscordapi.Response.KickResponse;
import tk.sherrao.sherdiscordapi.Response.MuteResponse;
import tk.sherrao.sherdiscordapi.Response.UnbanResponse;
import tk.sherrao.sherdiscordapi.Response.UnmuteResponse;

public abstract class Player extends ApiComponent {
	
	protected final User user;
	
	public Player( DiscordBot bot, User user ) {
		super( bot, "Player-" + user.getName() );
		
		this.user = user;
		
	}

	public abstract PrivateChannel openPM();
	
	public abstract MuteResponse mute( Server server );
	public abstract MuteResponse mute( Server server, String reason );
	public abstract MuteResponse mute( Server server, String reason, long time );
	public abstract UnmuteResponse unmute( Server server );
	
	public abstract KickResponse kick( Server server );
	public abstract KickResponse kick( Server server, String reason );
	
	public abstract BanResponse ban( Server server );
	public abstract BanResponse ban( Server server, String reason );
	public abstract UnbanResponse unban( Server server );
	
	public abstract boolean hasPermission( Server server, String permission );
	public abstract boolean hasPermission( Server server, Permission permission );
	
	public abstract boolean hasRole( Server server, String role );
	public abstract boolean hasRole( Server server, Role role );
	
	public abstract String getDisplayName( Server server );
	public abstract String getDiscrim();
	public abstract String getFullDiscordName();
	public abstract String getAvatar();
	
	public abstract long getCreation();
	public abstract String getId();
	
	public abstract List<Role> getRole( Server server );
	public abstract List<Server> getServers();
	public abstract Member getMember( Server server );
	public abstract Member getMember( Guild guild );
	public abstract User getUser();
	
}