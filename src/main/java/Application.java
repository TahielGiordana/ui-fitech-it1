import core.CoreFitech;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ScoreService;
import views.Home;

import java.io.File;
import java.io.FileNotFoundException;

public class Application {
    private static final Logger log = LogManager.getLogger("Application");

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 3) {
            log.info("Debe proporcionar el path al directorio de validators, el código de la máquina y el path al archivo de puntajes");
            System.exit(0);
        }
        String validatorPath = args[0];
        String machineCode = args[1];
        String scorePath = args[2];
        CoreFitech coreFitech = new CoreFitech(validatorPath, machineCode, scorePath);
        Home home = new Home(coreFitech);
    }
}
