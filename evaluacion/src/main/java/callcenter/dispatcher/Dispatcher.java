/**
 * 
 */
package callcenter.dispatcher;

import java.util.ArrayList;

import callcenter.entities.Director;
import callcenter.entities.Employee;
import callcenter.entities.Operador;
import callcenter.entities.Supervisor;
import callcenter.entities.dispatcher.Call;

/**
 * La clase {@link #Dispatcher} se encarga de asignar la tarea de atender una llamada a una persona del call center.
 * <br>
 * La idea es que cada persona hace la tarea de atender la llamada, por lo tando la persona se modela como una tarea, 
 * mas especificamente un thread @see Employee, @see Runnable.
 * 
 * @author Manuel
 *
 */
public class Dispatcher {

	
	/**
	 * La propiedad {@link #employees} se encarga de mantener la estructura de la organizacion del call center. 
	 */
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	
	/**
	 * Construcor
	 */
	public Dispatcher() {
		this.initializePersonel();
	}
	
	/**
	 * Inicializa la estructura del personal del call center. 
	 */
	public void initializePersonel() {
		this.employees.add(new Operador());
		this.employees.add(new Operador());
		this.employees.add(new Operador());
		this.employees.add(new Supervisor());
		this.employees.add(new Supervisor());
		this.employees.add(new Director());
	}

	
	/**
	 * @param call
	 * @throws InterruptedException
	 */
	public void dispatchCall(Call call) throws InterruptedException {
		Employee employee =  this.firstFreeEmployee();
		if (employee ==  null) {
			this.wait(5000);
		} else {
			employee.run();
		}		
	}


	/**
	 * Busca el primer empleado libre en el call center
	 * @see #Employee
	 * @see Employee#isFree()
	 * @return employee {@link #employee} retorna un empleado o null si no hay libres.
	 */
	private Employee firstFreeEmployee() {
		int index=0;
		for (Employee employee : this.employees) {
			if (employee.isFree()) {
				employee.setFree(false);
				return  employee;
			}
			index++;
		}
		return null;
	}
}
