package frontControllers;

import core.CoreFitech;
import interfaces.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import views.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private final Logger log = LogManager.getLogger("HomeController");
    private final CoreFitech coreFitech;
    private final Home home;

    private Timer validationTaskTimer;

    public HomeController(Home home, CoreFitech coreFitech){
        this.home = home;
        this.coreFitech = coreFitech;
        coreFitech.addObserver(this);
        this.validationTaskTimer = new Timer();
        setUpActions();
    }

    public void startValidationTask(){
        validationTaskTimer = new Timer();
        String userName = home.getUserNameTextField().getText();
        validationTaskTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                coreFitech.postValidationRequest(userName);
            }
        }, 0, 3000);
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JLabel resultLabel = home.getResultLabel();
        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validationTaskTimer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validationTaskTimer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validationTaskTimer.cancel();
                validatorBtn.setEnabled(true);
            }
        });

        validatorBtn.addActionListener(e->{
            startValidationTask();
            validatorBtn.setEnabled(false);
        });
    }

    @Override
    public void update() {
        JLabel resultLabel = home.getResultLabel();
        Boolean result = coreFitech.getResult();
        log.info("metodo update - result: {} ", result);
        if(result){
            resultLabel.setText("Puede utilizar la m�quina");
            resultLabel.setForeground(Color.GREEN);
        }else{
            resultLabel.setText("No puede utilizar la m�quina");
            resultLabel.setForeground(Color.RED);
        }
    }
}