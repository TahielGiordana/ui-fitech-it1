import core.CoreFitech;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ValidationTask;
import services.ValidatorFactory;
import services.score.ScoreTask;
import views.Home;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStory5 {

    private final Logger log = LogManager.getLogger("UserStory3");
    private ScoreTask scoreTask;
    private ValidationTask validationTask;
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        String path = new File("validators").getAbsolutePath();
        String machineCode = "Bicicleta1";
        String scoreTask = new File("").getAbsolutePath()+File.separator+"files"+File.separator+"Score.json";
        log.info("Path: {}", path);
        log.info("MachineCode: {}", machineCode);
        this.scoreTask = new ScoreTask(scoreTask);
        this.validationTask = new ValidatorFactory(path,machineCode).buildValidationTask();
    }

    @Test
    public void CA1_AddScoreToStoredUser() {
        log.warn("Se ejecuta test de CA1");
        int prevScore = scoreTask.getScore("Tahiel");
        scoreTask.addScore("Tahiel", 50);
        int newScore = scoreTask.getScore("Tahiel");
        assertEquals(prevScore + 50, newScore);
    }

    @Test
    public void CA2_AddScoreToNewUser() {
        log.warn("Se ejecuta test de CA2");
        scoreTask.addScore("newUser", 50);
        assertEquals(scoreTask.getScore("newUser"), 50);
    }

}