import core.CoreFitech;
import views.Home;

import java.io.File;

public class Application {

    public static void main(String[] args) {
       CoreFitech coreFitech = new CoreFitech(new File("").getAbsolutePath());
       Home home = new Home(coreFitech);
    }
}
