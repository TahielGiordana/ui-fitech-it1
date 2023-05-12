import core.ValidationService;
import views.Home;

import java.io.File;

public class Application {

    public static void main(String[] args) {
       ValidationService validationService = new ValidationService(new File("").getAbsolutePath());
       Home home = new Home(validationService);
    }
}
