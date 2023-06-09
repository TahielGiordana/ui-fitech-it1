import interfaces.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ValidationTask;
import services.ValidatorFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStory3 {

    private final Logger log = LogManager.getLogger("UserStory3");
    private ValidationTask validationTask;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        String path = new File("validators").getAbsolutePath();
        String machineCode = "Bicicleta1";
        log.info("Path: {}", path);
        log.info("MachineCode: {}", machineCode);
        this.validationTask = new ValidatorFactory(path,machineCode).buildValidationTask();
    }

    @Test
    public void CA1_NoneValidator() {
        log.warn("Se ejecuta test de CA1");
        validationTask.toggleValidatorCheck("RoutineValidator");
        validationTask.toggleValidatorCheck("PaymentValidator");
        validationTask.processRequest("Evelyn");
        assertTrue(validationTask.getResult().isEmpty());
    }

    @Test
    public void CA2_RoutineValidator() {
        log.warn("Se ejecuta test de CA2");
        validationTask.toggleValidatorCheck("PaymentValidator");
        validationTask.processRequest("Joan");
        Map<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("RoutineValidator", false);
        assertEquals(validationTask.getResult(), expectedResult);
    }

    @Test
    public void CA3_PaymentValidator() {
        log.warn("Se ejecuta test de CA3");
        validationTask.toggleValidatorCheck("RoutineValidator");
        validationTask.processRequest("Evelyn");
        Map<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("PaymentValidator", false);
        assertEquals(validationTask.getResult(), expectedResult);
    }

    @Test
    public void CA4_BothValidators() {
        log.warn("Se ejecuta test de CA4");
        validationTask.processRequest("Tahiel");
        Map<String, Boolean> expectedResult = new HashMap<>();
        expectedResult.put("PaymentValidator", true);
        expectedResult.put("RoutineValidator", true);
        assertEquals(validationTask.getResult(), expectedResult);
    }

}