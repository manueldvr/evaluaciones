/**
 * 
 */
package callcenter.entities.dispatcher;

import java.util.Random;

/**
 * @author Manuel
 *
 */
public class Call {
	
	/** Limite superior para una generacion de numeros seudoaleatorios.	 */
	private static final int BOUND_RANDOM = 100; 
	
	private int id;
	

	private Message message;
	
	private static Random randomForId = new Random();

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message mensaje) {
		this.message = mensaje;
	}
	
	public Call() {
		this(randomForId.nextInt(BOUND_RANDOM), new Message());
	}


	public Call(int id, Message message) {
		this.setId(id);
		this.setMessage(message);	
	}
	
	/**
	 * Print main message data.
	 * @return String 
	 */
	public String print() {
		return "Call Id:" + this.getId() + " - message=" + this.getMessage().print();
	}
}
