/**
 * 
 */
package callcenter.simulador;

import callcenter.simulador.exceptions.ConsumeCanceledException;

/**
 * La clase {@link #CallCenterSimulator()} simula un call center.
 * Utiliza la clase {@link #processor} para producir y consumir mensajes.
 * </br>
 * Based on Producer Consumer pattern.
 * 
 * @see #Processor
 * @author Manuel
 *
 */
public class CallCenterSimulator {
	
    /**
     * La clase Processor implementa las proncipales operaciones del pattern productor/consumidor.
     */
    private Processor processor;

	
	public CallCenterSimulator() {
        processor = new Processor();
	}
	
	/**
	 * El metodo startSimulation da inicio a la primer parte de la simulación:
	 * <li> la produccion de mensajes: <br>
	 * 	    el thread <code>producerThread</code> crea llamadas (<code>Calls</code>)
	 * <li> el consumo de mensajes: <br>
	 *  el thread <code>consumerThread</code> saca/consume las llamadas.
	 * <br>
	 * <br>
	 * @see tutorial 7 use of {@link java.util.concurrent.BlockingQueue}
	 * <br>
	 * @throws InterruptedException
	 */
	public void startSimulation() throws InterruptedException {
        
        Thread producerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException intExc) {
                	System.out.println("Simulation terminated.   <<<<<");
                } catch (ConsumeCanceledException e) {
                	System.out.println(e.getMessage());
					e.printStackTrace();
				} 
            }
        });
        Thread consumerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                	e.printStackTrace();
                } catch (ConsumeCanceledException e) {
                	System.out.println(e.getMessage());
					e.printStackTrace();
				}
            }
        });
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
	}
	
	
    /**
     * Main method.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

    	CallCenterSimulator callCenterSimulator = new CallCenterSimulator();    	
    	callCenterSimulator.startSimulation();
    }
    
    
	/**
	 * @return the processor
	 */
	public Processor getProcessor() {
		return processor;
	}   
}
