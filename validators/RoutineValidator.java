package validators;

import interfaces.Validator;
import pojos.Machine;
import pojos.Person;
import pojos.Routine;
import services.RoutineFinder;

public class RoutineValidator implements Validator {

	public RoutineValidator() {
	}

	@Override
	public boolean validate(Person person, Machine machine) {
		RoutineFinder routineFinder = new RoutineFinder();
		Routine routineByPerson = routineFinder.getRoutineByPerson(person);
		return routineByPerson != null && routineByPerson.getMachine().getCode().equalsIgnoreCase(machine.getCode());
	}

}