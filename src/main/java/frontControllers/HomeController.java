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

public class HomeController implements Observer {

    private final Logger log = LogManager.getLogger("HomeController");
    private final CoreFitech coreFitech;
    private final Home home;

    public HomeController(Home home, CoreFitech coreFitech){
        this.home = home;
        this.coreFitech = coreFitech;
        coreFitech.addObserver(this);
        setUpActions();
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JLabel resultLabel = home.getResultLabel();
        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }
        });

        validatorBtn.addActionListener(e->{
            coreFitech.processRequest(user.getText());
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