/**
 * 
 */
package callcenter.entities;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

import callcenter.util.CallCenterSimulatorConstants;

/**
 * Entidad basica de Empleado.
 * Representa una perona dentro de la organización con la tarea de atender una llamada.
 * <br>
 * La tarea se simula con la implementacion del metodo {@link #run()}. <br> 
 * La duracion de la tarea se simula con el metodo {@link Thread#sleep(long)}. Y el valor de la duracion con la 
 * generacion de un numero seudoaleatorio {@link ThreadLocalRandom}.
 * <br>
 * @see Direcor
 * @see Supervisor
 * @see {@link #run()}
 * @see Runnable
 * 
 * @author Manuel
 *
 */
public class Employee implements Runnable{
	
	
	/**
	 * Nombre
	 */
	private String nombre; 
	
	/**
	 * Apellido
	 */
	private String apellido; 
	
	/** 	 * 
	 */
	private boolean isFree = true; 
	
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Indica si tiene asignada tarea o no (isFree en true).
	 * @return the isFree
	 */
	public boolean isFree() {
		return isFree;
	}

	/**
	 * @param isFree the isFree to set
	 */
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
			System.out.println("Starting");
			try {
				Thread.sleep(callDuration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Completed.");
			this.setFree(true);
	}

	/**
	 * Simula la duracion de la tarea de atender una llamada.
	 * @see ThreadLocalRandom#current()
	 * @see ThreadLocalRandom#nextInt(int, int)
	 * @return  Integer between min/origin and max/bound, inclusive.
	 */
	private long callDuration() {
	    int randomNum = ThreadLocalRandom.current().nextInt(CallCenterSimulatorConstants.TMIN, 
	    		 CallCenterSimulatorConstants.TMAX);
	    return randomNum;
	}
}
