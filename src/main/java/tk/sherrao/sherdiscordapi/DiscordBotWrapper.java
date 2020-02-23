package tk.sherrao.sherdiscordapi;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DiscordBotWrapper {
	
	public String TOKEN;
	public String PREFIX;
	
	protected String name;
	protected String[] vargs;
	protected DiscordBot bot;
	
	protected String workspace;
	protected File file;
	protected boolean firstTime;
	protected JsonObject json;

	public static void main( String... vargs ) 
			throws Exception {		
		double d = Double.MAX_VALUE - Double.MIN_VALUE;
		System.out.println( d );
		
		DiscordBotWrapper w = new DiscordBotWrapper( "TestBot" );
		w.load();
		w.start();
		
	}
	
	public DiscordBotWrapper( String name, String... vargs ) {
		this.name = name;
		this.vargs = vargs;
	
	}

	public void load() 
			throws Exception {
		workspace = URLDecoder.decode( new File( super.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() ).getParent(), "UTF-8" );
		file = new File( workspace, "data/bot.yml" );
		firstTime = !file.exists();
		if( firstTime ) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		
		}
			
		Path path = Paths.get( file.getPath() );
		JsonParser parser = new JsonParser();
		if( !firstTime ) {
			JsonReader reader = new JsonReader( Files.newBufferedReader( path ) );
			reader.setLenient( true );
			json = parser.parse( reader ).getAsJsonObject();
			JsonElement token = json.get( "token" );
			this.TOKEN = token.getAsString();	
			
			JsonElement prefix = json.get( "prefix" );
			this.PREFIX = prefix.getAsString();
			
			reader.close();
			
		} else {
			JsonWriter writer = new JsonWriter( Files.newBufferedWriter( path,  StandardOpenOption.WRITE, StandardOpenOption.APPEND ) );
			writer.setIndent( "    " );
			writer.setLenient( true );
			writer.beginObject()
				.name( "token" )
				.value( "0000000000000000000000000000000000000000000000000000000000000000" )
				.name( "prefix" )
				.value( "!" )
				.endObject()
				.flush();
			
			writer.close();

		}

		bot = new DiscordBot( this, name );
		bot.onLoad();
		
	}
	
	public void start() {
		try {
			bot.onEnable();
			
		} catch( Exception e ) {  }
		
	}
	
	public final String[] getVargs() {
		return vargs;
		
	}
	
	public final DiscordBot getBot() {
		return bot;
		
	}
	
}