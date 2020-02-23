package tk.sherrao.sherdiscordapi.logging;

public enum LogLevel {

	TRACE( "Trace" ), FINE( "Fine" ), INFO( "Info" ), WARNING( "WARNING" ), ERROR( "ERROR" ), SEVERE( "SEVERE" ); 
	
	private String name;
	
	LogLevel( String name ) {
		this.name = name;
		
	}
	
	@Override
	public String toString() {
		return name;
		
	}

	public boolean canLog( LogLevel log ) {
		return canLog( log, this );
		
	}
	
	public static boolean canLog( LogLevel log, LogLevel message ) {
		return message.ordinal() <= log.ordinal();
		
	}
	
}
