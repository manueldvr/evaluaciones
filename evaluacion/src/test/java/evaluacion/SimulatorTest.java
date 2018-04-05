/**
 * 
 */
package evaluacion;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;

import callcenter.simulador.CallCenterSimulator;

/**
 * Tests para el simulador.
 * <p>
 * {@link https://es.wikipedia.org/wiki/JUnit}
 * 
 * @see CallCenterSimulator
 * @see CallCenterSimulator#startSimulation()
 * @author Manuel
 * @version 1
 */
public class SimulatorTest {

	/** La constante <code>SIZE_ZERO</code> se utiliza para controlar que efectivamente la queue de mensaje se haya 
	 *  consumido.
	 */
	private static final int SIZE_ZERO = 0;
	
	/** La constante <code>MAX_10_CALLS</code> controla que la cantidad de llamadas al Call Center sean 10.
	 *  se usa para test segun requerimientos.
	 */
	private static final int MAX_10_CALLS = 10;
	
	/**	MAX_LOCALTEST_CALLS controla que la cantidad de llamadas al Call Center sean 3.
	 *  se usa para un debuging rapido. 
	 */
	private static final int MAX_LOCALTEST_CALLS = 3;

	
	/**
	 * Para tests rapidos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	@Ignore
	public void testCaseReducedCalls() throws InterruptedException { 
		CallCenterSimulator callCenter = new CallCenterSimulator();
		
		callCenter.startSimulation();
		
		assertNotNull(callCenter.getProcessor());
		assertEquals(callCenter.getProcessor().getCounter(), MAX_LOCALTEST_CALLS);
	    assertEquals(callCenter.getProcessor().queueOfMessagesSize(), SIZE_ZERO);
	}
	
	/**
	 * El metodo <code>testCaseMAXCalls</code> controla que la cantidad de llamadas al Call Center 
	 * sean 10.
	 * @see CallCenterSimulator#startSimulation()
	 * @throws InterruptedException 
	 */
	@Test
	public void testCaseMAXCalls() throws InterruptedException {
		
		CallCenterSimulator callCenter = new CallCenterSimulator();
		
		callCenter.startSimulation();
		
		assertNotNull(callCenter.getProcessor());
		assertEquals(callCenter.getProcessor().getCounter(), MAX_10_CALLS);
	    assertEquals(callCenter.getProcessor().queueOfMessagesSize(), SIZE_ZERO);
	}
}
