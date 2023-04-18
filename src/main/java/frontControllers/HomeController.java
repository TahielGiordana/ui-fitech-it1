package frontControllers;

import init.InitCore;
import pojos.Machine;
import pojos.Person;

public class HomeController {

    private InitCore initCore;

    public HomeController(){
        this.initCore = new InitCore();
    }

    public boolean validate(Person persona, Machine machine){
        return  initCore.executeValidators(persona,machine);
    }

}