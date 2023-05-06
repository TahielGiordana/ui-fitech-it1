package frontControllers;

import interfaces.Observer;
import services.ValidatorManager;
import views.Home;

import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private ValidatorManager validatorManager;
    private Home home;

    public HomeController(Home home, ValidatorManager validatorManager){
        this.home = home;
        this.validatorManager = validatorManager;
    }

    public void startValidationTask(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                post(validatorManager.getActualUserName(), validatorManager.getActualMachineCode());
            }
        }, 0, 3000);
    }

    public void post(String userName, String machineCode){
        this.validatorManager.validate(userName,machineCode);
    }

    @Override
    public void update() {
        boolean validationResult = validatorManager.getValidationResult();
        home.updateResult(validationResult);
    }

}