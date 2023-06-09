import core.CoreFitech;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ValidationTask;
import services.ValidatorFactory;
import views.Home;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.FieldPosition;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStory4 {

    private final Logger log = LogManager.getLogger("UserStory3");
    private CoreFitech coreFitech;
    private Home home;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        String path = new File("validators").getAbsolutePath();
        String machineCode = "Bicicleta1";
        String scoreTask = new File("").getAbsolutePath()+File.separator+"files"+File.separator+"Score.json";
        log.info("Path: {}", path);
        log.info("MachineCode: {}", machineCode);
        this.coreFitech = new CoreFitech(path,machineCode,scoreTask);
        this.home = new Home(coreFitech);
    }

    @Test
    public void CA1_WithoutMessage() {
        log.warn("Se ejecuta test de CA1");
        coreFitech.getValidationTask().processRequest("Tahiel");

        String resultMessage = home.getResultLabel().getText();
        log.warn(resultMessage);
        assertEquals(resultMessage, "Puede utilizar la máquina");
    }

    @Test
    public void CA2_RoutineValidatorFailed() {
        log.warn("Se ejecuta test de CA2");
        coreFitech.getValidationTask().processRequest("Joan");

        String resultMessage = home.getResultLabel().getText();
        log.warn(resultMessage);
        assertEquals(resultMessage, "No puede usar la máquina\nRoutineValidator Falló");
    }

    @Test
    public void CA3_PaymentValidatorFailed() {
        log.warn("Se ejecuta test de CA3");
        coreFitech.getValidationTask().processRequest("Evelyn");

        String resultMessage = home.getResultLabel().getText();
        log.warn(resultMessage);
        assertEquals(resultMessage, "No puede usar la máquina\nPaymentValidator Falló");
    }

    @Test
    public void CA4_PaymentValidatorFailed() {
        log.warn("Se ejecuta test de CA4");
        coreFitech.getValidationTask().processRequest("Tony");

        String resultMessage = home.getResultLabel().getText();
        log.warn(resultMessage);
        assertEquals(resultMessage, "No puede usar la máquina\nRoutineValidator Falló\nPaymentValidator Falló");
    }

}