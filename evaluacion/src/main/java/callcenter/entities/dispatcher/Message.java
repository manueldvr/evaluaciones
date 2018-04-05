/**
 * 
 */
package callcenter.entities.dispatcher;

import java.util.Random;

/**
 * @author Manuel
 *
 */
public class Message {

	/** Limite superior para una generacion de numeros seudoaleatorios.	 */
	private static final int BOUND_RANDOM = 100; 
	
	/** content of the message	 */
	private String content; 
	
	/** Message identification number.	 */
	private int id;
	
	private static Random randomForId = new Random();

	/** */
	public Message(int id, String content) {
		this.content = content;
		this.id = id;
	}
	
	/** Default construtor */
	public Message() {
		this(randomForId.nextInt(BOUND_RANDOM), new String("A message for " + randomForId.nextInt()));
	}
	
	/**
	 * @return the message
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param message the message to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Print main message data.
	 * @return String 
	 */
	public String print() {
		return "Message Id:" + this.getId() + " - content=" + this.getContent() + ".";
	}
}
