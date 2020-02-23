package tk.sherrao.sherdiscordapi.configuration;

public interface Serializable<T> {

	Object serialise();
	
	
	T deserialise();
	
}