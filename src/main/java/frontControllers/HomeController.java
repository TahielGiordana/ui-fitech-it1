package frontControllers;

import init.InitCore;
import pojos.Machine;
import pojos.Person;
import services.RoutineFinder;
import validators.RoutineValidator;

public class HomeController {

    private InitCore initCore;

    public HomeController(){
        this.initCore = new InitCore();
    }

    public boolean validar(Person persona, Machine machine){
        return  initCore.executeValidators(persona,machine);
    }

}