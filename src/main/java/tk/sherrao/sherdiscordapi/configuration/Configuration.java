package tk.sherrao.sherdiscordapi.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.iterators.ArrayIterator;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import tk.sherrao.sherdiscordapi.ApiComponent;
import tk.sherrao.sherdiscordapi.DiscordBot;

public class Configuration extends ApiComponent {

	protected final File file;
	protected boolean firstTime;
	
	protected final Path path;
	protected BufferedReader in;
	protected BufferedWriter out;

	protected Gson gson;
	protected JsonParser parser;
	protected JsonObject config;
	
	public Configuration( final DiscordBot bot, final String filename ) 
				throws IOException {
		this( bot, new File( bot.getCurrentWorkspacePath() + "\\data", (filename.endsWith( ".json" ) ? filename : filename.concat( ".json" )) ) );
		
	}
	
	public Configuration( final DiscordBot bot, final File file ) 
			throws IOException {
		super( bot, "Configuration I/O/" + file.getName() );
		
		this.file = file;
		this.firstTime = !file.exists();
		file.getParentFile().mkdirs();
		file.createNewFile();
		
		this.path = Paths.get( file.getPath() );
		this.in = Files.newBufferedReader( path );
		this.out = Files.newBufferedWriter( path, StandardOpenOption.WRITE, StandardOpenOption.APPEND );
	
		this.gson = new GsonBuilder()
				.serializeNulls()
				.setPrettyPrinting()
				.setLenient()
				.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES )
				.create();
		this.parser = new JsonParser();
		this.config = parser.parse( in ).getAsJsonObject();
		
	}
	
	public boolean set( String key, Object value ) {
		try {
			JsonObject json = this.config;
			if( key.contains( "\\." ) ) {
				for( Iterator<String> it = new ArrayIterator<>( key.split( "\\." ) ); it.hasNext(); ) {
					String str = it.next();
					if( it.hasNext() )
						json = json.get( str ).getAsJsonObject();
			
					else json = json.getAsJsonObject();
				
				}
			
				json.add( key, json );
				this.config = json;
			
			} else {
				json.add( key, json );
				this.config = json;
			
			}

			return true;
			
		} catch( JsonParseException e ) { return false; }
		
	}
	 
	public JsonElement get( String key ) {
		JsonElement result = null;
		if( key.contains( "." ) ) {
			Iterator<String> it = Arrays.asList( key.split( "\\." ) ).iterator();
			JsonObject object = config.getAsJsonObject( it.next() );
			for( ;it.hasNext(); ) {
				String next = it.next();
				if( it.hasNext() )
					object = object.getAsJsonObject( next );
				
				else result = object.get( next );
					
			}
			
		} else result = config.get( key );

		return result;
					
	}
	
	public String getString( String key ) {
		return get( key ).getAsString();
		
	}
	
	public String getString( String key, String def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsString() );
		
	}
	
	public boolean getBoolean( String key ) {
		return get( key ).getAsBoolean();
		
	}
	
	public boolean getBoolean( String key, boolean def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsBoolean() );
		
	}
	
	public int getInt( String key ) {
		return get( key ).getAsInt();
		
	}
	
	public int getInt( String key, int def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsInt() );
		
	}
	
	public long getLong( String key ) {
		return get( key ).getAsLong();
		
	}
	
	public long getLong( String key, long def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsLong() );
		
	}
	
	public double getDouble( String key ) {
		return get( key ).getAsDouble();
			
		}
		
		public double getDouble( String key, double def ) {
			JsonElement result = get( key );
			return (result.isJsonNull() ? def : result.getAsDouble() );		
		
	}
	
	public float getFloat( String key ) {
		return get( key ).getAsFloat();
		
	}
	
	public float getFloat( String key, float def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsFloat() );
		
	}
	
	public byte getByte( String key ) {
		return get( key ).getAsByte();
		
	}
	
	public byte getByte( String key, byte def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsByte() );
		
	}
	
	public char getChar( String key ) {
		return get( key ).getAsCharacter();
		
	}
	
	public char getChar( String key, char def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsCharacter() );
		
	}
	
	public short getShort( String key ) {
		return get( key ).getAsShort();
		
	}
	
	public short getShort( String key, short def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsShort() );
		
	}
	
	public Number getNumber( String key ) {
		return get( key ).getAsNumber();
		
	}
	
	public Number getNumber( String key, Number def ) {
		JsonElement result = get( key );
		return (result.isJsonNull() ? def : result.getAsNumber() );		

	}
	
	
	
	public List<String> getStringList( String key ) {
		List<String> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsString() ); } );
		return result;
		
	}
	
	public List<Boolean> getBooleanList( String key ) {
		List<Boolean> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsBoolean() ); } );
		return result;
		
	}
	
	public List<Integer> getIntegerList( String key ) {
		List<Integer> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsInt() ); } );
		return result;
		
	}
	
	public List<Long> getLongList( String key ) {
		List<Long> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsLong() ); } );
		return result;
		
	}
	
	public List<Double> getDoubleList( String key ) {
		List<Double> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsDouble() ); } );
		return result;
		
	}
	
	public List<Float> getFloatList( String key ) {
		List<Float> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsFloat() ); } );
		return result;
		
	}
	
	public List<Byte> getByteList( String key ) {
		List<Byte> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsByte() ); } );
		return result;
		
	}
	
	public List<Character> getCharList( String key ) {
		List<Character> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsCharacter() ); } );
		return result;
		
	}
	
	public List<Short> getShortList( String key ) {
		List<Short> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsShort() ); } );
		return result;
		
	}
	
	public List<Number> getNumberList( String key ) {
		List<Number> result = new ArrayList<>();
		get( key ).getAsJsonArray().forEach( (e) -> { result.add( e.getAsNumber() ); } );
		return result;
		
	}
	
	
	
	public final File getFile() { 
		return file;
		
	}
	
	public final boolean isFirstTime() {
		return firstTime;
		
	}
	
	public final Path getPath() {
		return path;
		
	}
	
	
}