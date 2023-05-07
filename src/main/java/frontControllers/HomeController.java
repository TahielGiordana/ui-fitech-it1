package frontControllers;

import interfaces.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import interfaces.Observer;
import services.ValidationEngine;
import views.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private Logger log = LogManager.getLogger("HomeController");
    private final ValidationEngine validationEngine;
    private final Home home;

    public HomeController(Home home, ValidationEngine validationEngine){
        log.info("se crea el componente {}", HomeController.class.getName());
        this.home = home;
        this.validationEngine = validationEngine;
    }

    public void startValidationTask(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                post(validationEngine.getActualUserName(), validationEngine.getActualMachineCode());
            }
        }, 0, 3000);
    }

    public void post(String userName, String machineCode){
        this.validationEngine.validate(userName,machineCode);
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JTextField machine = home.getMachineSerialCodeTextField();
        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                log.info("metodo insertUpdate: {} {}",user.getText(),machine.getText() );
                post(user.getText(),machine.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                log.info("metodo removeUpdate: {} {}",user.getText(),machine.getText() );
                post(user.getText(),machine.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                log.info("metodo changedUpdate: {} {}",user.getText(),machine.getText() );
                post(user.getText(),machine.getText());
            }
        });

        machine.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                post(user.getText(),machine.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                post(user.getText(),machine.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                post(user.getText(),machine.getText());
            }
        });
    }

    @Override
    public void update() {
        JLabel resultLabel = home.getResultLabel();
        Boolean result = home.getCore().getValidatorManager().getValidationResult();
        log.info("metodo update - result: {} ", result);
        if(result){
            resultLabel.setText(Boolean.toString(result));
            resultLabel.setForeground(Color.GREEN);
        }else{
            resultLabel.setText(Boolean.toString(result));
            resultLabel.setForeground(Color.RED);
        }
    }

}