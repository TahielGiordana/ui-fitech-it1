package frontControllers;

import init.Core;
import interfaces.Observer;
import views.Home;

import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private Core core;
    private Home home;

    public HomeController(Home home){
        this.home = home;
        this.core = home.getCore();
        core.addObserver(this);
    }

    public void startValidationTask(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                validate(core.getActualUserName(),core.getActualMachineCode());
            }
        }, 0, 3000);
    }

    public void validate(String userName, String machineCode){
        this.core.validate(userName,machineCode);
    }

    public void update(){
        this.home.getResultLabel().setText(Boolean.toString(core.getValidationResult()));
    }

}