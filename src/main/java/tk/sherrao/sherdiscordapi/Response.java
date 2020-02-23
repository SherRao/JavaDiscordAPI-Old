package tk.sherrao.sherdiscordapi;

public abstract class Response {

	private Response() {}
	
	static public enum BanResponse {

		SUCCESS, NO_PERMISSION, EXECUTOR_REMOVED_FROM_SERVER, TARGET_ALREADY_BANNED, TARGET_NOT_ON_SERVER;
		
	}
	
	static public enum UnbanResponse {

		SUCCESS, NO_PERMISSION, EXECUTOR_REMOVED_FROM_SERVER, TARGET_NOT_BANNED, TARGET_NOT_ON_SERVER;

	}
	
	static public enum MuteResponse {

		SUCCESS, NO_PERMISSION, EXECUTOR_REMOVED_FROM_SERVER, TARGET_ALREADY_MUTED, TARGET_NOT_ON_SERVER;
		
	}
	
	static public enum UnmuteResponse {

		SUCCESS, NO_PERMISSION, EXECUTOR_REMOVED_FROM_SERVER, TARGET_NOT_MUTED, TARGET_NOT_ON_SERVER;
		
	}
	
	static public enum KickResponse {

		SUCCESS, NO_PERMISSION, EXECUTOR_REMOVED_FROM_SERVER, TARGET_NOT_ON_SERVER;

	}
	
}
