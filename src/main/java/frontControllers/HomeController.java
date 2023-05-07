package frontControllers;

import interfaces.Observer;
import services.ValidationEngine;
import views.Home;

import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private final ValidationEngine validationEngine;
    private final Home home;

    public HomeController(Home home, ValidationEngine validationEngine){
        this.home = home;
        this.validationEngine = validationEngine;
    }

    public void startValidationTask(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                post(validationEngine.getActualUserName(), validationEngine.getActualMachineCode());
            }
        }, 0, 3000);
    }

    public void post(String userName, String machineCode){
        this.validationEngine.validate(userName,machineCode);
    }

    @Override
    public void update() {
        boolean validationResult = validationEngine.getValidationResult();
        home.updateResult(validationResult);
    }

}