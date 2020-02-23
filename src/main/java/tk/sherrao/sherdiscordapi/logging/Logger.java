package tk.sherrao.sherdiscordapi.logging;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import tk.sherrao.sherdiscordapi.ApiComponent;

public class Logger {

	protected final String name;
	protected LogLevel logLevel;
	
	protected boolean printStackTrace;
	protected String outputFormat;
	protected DateFormat dateFormat;
	protected List<PrintStream> outputs;

	public Logger( final ApiComponent component ) {
		this( component, LogLevel.INFO );
		
	}

	public Logger( final ApiComponent component, final ApiComponent parent ) {
		this( component, parent, LogLevel.INFO );
		
	}
	
	public Logger( final ApiComponent component, final LogLevel level ) {
		this( component, null, level );
	
	}
	
	public Logger( final ApiComponent component, final ApiComponent parent, final LogLevel level ) {
		this.name = component.getName() + (parent == null ? "" : "/" + parent.getName() );	
		this.logLevel = level;
		
		this.printStackTrace = true;
		this.outputFormat = new StringBuilder()
				.append( "<time> <level>/" )
				.append( name )
				.append( ": <output>" )
				.toString();
		this.dateFormat = new SimpleDateFormat( "zzz [yyyy-MM-dd HH:mm:ss]" );
		this.outputs = Arrays.asList( System.out );
		
	}
	
	private void doLog( String log, LogLevel level, Throwable... throwables ) {
		String str = outputFormat.replace( "<time>", dateFormat.format( Calendar.getInstance().getTime() )
				.replace( "<level>", level.toString() )
				.replace( "<output>", log ) );
		
		if( throwables.length != 0 && printStackTrace ) {
			for( PrintStream out : outputs ) {
				out.println( str );
				for( Throwable t : throwables )
					t.printStackTrace( out );
				
			}
			
		} else 
			for( PrintStream out : outputs )
				out.println( str );
		
	}
		
	public final void trace( String log ) {
		doLog( log, LogLevel.TRACE);
		
	}
	
	public final void fine( String log ) {
		doLog( log, LogLevel.FINE );
		
	}
	
	public final void info( String log ) {
		doLog( log, LogLevel.INFO );
		
	}
	
	public final void warning( String log ) {
		doLog( log, LogLevel.WARNING );
		
	}
	
	public final void error( String log ) {
		doLog( log, LogLevel.ERROR );
		
	}
	
	public final void severe( String log ) {
		doLog( log, LogLevel.SEVERE );
		
	}
	
	
	public final void addOutputSource( PrintStream out ) {
		outputs.add( out );
		
	}
	
	public final void removeOutputSource( PrintStream out ) {
		outputs.removeIf( (o) -> o.equals( out ) );	

	}
	
	
	public final void printStackTrace( boolean print ) {
		this.printStackTrace = print;
		
	}
	
	public final String getName() { 
		return name;
		
	}
	
}