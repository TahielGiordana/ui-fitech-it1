import init.Core;
import views.Home;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        Core core = new Core(new File("").getAbsolutePath());
        Home home = new Home(core);
    }
}
