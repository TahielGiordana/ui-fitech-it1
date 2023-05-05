package frontControllers;

import init.Core;

public class HomeController {

    private Core Core;

    public HomeController(){
        this.Core = new Core("C:/Users/TALIGENT/IdeaProjects/ui-fitech");
    }

    public void validate(String userName, String machineCode){
        Core.validate(userName,machineCode);
    }

}