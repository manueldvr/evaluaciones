/**
 * 
 */
package callcenter.simulador;

import java.util.LinkedList;
import java.util.Random;

import callcenter.dispatcher.Dispatcher;
import callcenter.entities.dispatcher.Call;
import callcenter.simulador.exceptions.ConsumeCanceledException;
import callcenter.util.CallCenterSimulatorConstants;

/**
 * @author Manuel
 *
 */
public class Processor {
	
	/**
	 * La lista {@link #queueOfMessagesSize()} se encarga de mantener una queue, compartida para los 
	 * metodos {@link #produce()} y {@link #consume()}.
	 * <br>
	 * <br>
	 * El productor hace un append, es decir inserta una llamada al final de la lista. <br>
	 * El consumidor remueve un elemento, llamada, del principio de la lista.
	 * 
	 */
	private LinkedList<Call> queueOfMessages = new LinkedList<Call>();

	Object lock = new Object();
	
	/**
	 * La propiedad {@link #counter} es un contador especial para restringir el funcionamiento del 
	 * pattern producer/consumer. Restringe la produccion de llamadas a una cota de 10, 
	 * {@link CallCenterSimulatorConstants#MAX}.
	 * <br>
	 * *La restriccion es parte de los requerimientos del ejercicio.
	 * <br>
	 * Cuando se cumple la cota se deja de producir llamadas, o lo que es lo mismo, que el call center deja de 
	 * atender llamadas.
	 * 
	 */
	private int counter = 0;
	
	/**
	 * El metodo produce gestona la continua creacion de llamadas.
	 * *siempre que no se alcance la restriccion de 10 llamadas.
	 * 
	 * @throws InterruptedException
	 * @throws ConsumeCanceledException 
	 */
	public void produce() throws InterruptedException, ConsumeCanceledException {

		boolean go = true;		
		while (go) {
			
			synchronized (lock) {
				if (counter == CallCenterSimulatorConstants.MAX) {
					go = false;                   // si se alcanza la restriccion, deja de producir.
					break;
				}
				if (queueOfMessages.size() == CallCenterSimulatorConstants.MAX){ 
					lock.wait();                  // wait production, si la queue está llena.
				}
				counter ++;                       // incrementa el contador para controlar la *restriccion. 
				queueOfMessages.add(new Call());  // inserta una llamada a la queue.
				lock.notify();                    // notifies al consumidor que ahora puede iniciar el consumo de llamdas.
			}
		}
	}
	
	/**
	 * El metodo consume es llamado por el thread que consume mensajes.
	 * @throws InterruptedException
	 * @throws ConsumeCanceledException 
	 */
	public void consume() throws InterruptedException, ConsumeCanceledException {
		
		Random random = new Random();
		Dispatcher dispatcher = new Dispatcher();
		
		while (true) {
			synchronized (lock) {
				if (queueOfMessages.size() == 0) {
					if (this.counter == CallCenterSimulatorConstants.MAX)
						throw new ConsumeCanceledException("Simulation terminated. Call queue empty <<<<<");
					else
						lock.wait();                                           // espera si est vacia, si no hay llamadas.
				}
				System.out.print("List size is = " + queueOfMessages.size());
				
				Call removedCall = queueOfMessages.removeFirst();              // recupera una llamada
				
				System.out.println("; value is = " + removedCall.print());
				dispatcher.dispatchCall(removedCall);                          // asigna la llamada a alguien del personal
				lock.notify();	                                               // despierta al productor
			}
			// **
			Thread.sleep(random.nextInt(10));
		}
	}
	
	/**
	 * Muestra el tamaño de la queue.
	 * @return size cantidad de elementos
	 */
	public int queueOfMessagesSize() {
		return this.queueOfMessages.size();
	}

	/**
	 * muestra el estado del contador
	 * @return counter
	 */
	public Object getCounter() {
		return this.counter;
	}
}
