package tk.sherrao.sherdiscordapi.audio;

public abstract class AudioAttemptResponse {

	public static enum Skip {
		QUEUE_EMPTY(), SUCCESS();
		
	}
	
	public static enum Stop {
		ALREADY_STOPPED(), SUCCESS();
		
	}
	
	public static enum Resume {
		ALREADY_PLAYING(), SUCCESS();
		
	}
	
	public static enum Pause {
		ALREADY_PAUSED(), SUCCESS();	
		
	}
	
}