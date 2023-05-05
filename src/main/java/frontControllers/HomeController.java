package frontControllers;

import init.Core;
import interfaces.Observer;
import views.Home;

import java.io.File;

public class HomeController implements Observer {

    private Core core;
    private Home home;

    public HomeController(Home home){
        this.home = home;
        this.core = home.getCore();
        core.addObserver(this);
    }

    public void validate(String userName, String machineCode){
        core.validate(userName,machineCode);
    }

    public void update(){
        this.home.getResultLabel().setText(Boolean.toString(core.getValidationResult()));
    }

}