/**
 * 
 */
package callcenter.simulador.exceptions;

/**
 * Exception particular para activar la cancelacion en la produccion o consumo de llamadas.
 *  
 * @author Manuel
 *
 */
public class ConsumeCanceledException extends Exception {
	
	private static final long serialVersionUID = 3018828512143293128L;
	
	public ConsumeCanceledException() {
		super();
	}
	
	public ConsumeCanceledException(String message) {
		super(message);
	}

}
