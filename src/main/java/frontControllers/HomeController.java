package frontControllers;

import core.CoreFitech;
import interfaces.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.ScoreService;
import views.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class HomeController implements Observer {

    private final Logger log = LogManager.getLogger("HomeController");
    private final CoreFitech coreFitech;
    private final ScoreService scoreFitech;
    private final Home home;

    public HomeController(Home home, CoreFitech coreFitech, ScoreService scoreFitech){
        this.home = home;
        this.coreFitech = coreFitech;
        this.scoreFitech = scoreFitech;
        coreFitech.addObserver(this);
        setUpActions();
        updateScoreTable();
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JTextArea resultLabel = home.getResultLabel();
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

    public void updateScoreTable(){
        //Lleno tabla score
        DefaultTableModel scoreTableModel = home.getScoreTableModel();
        Map<String,Integer> scores = scoreFitech.getAllScores();

        scoreTableModel.setRowCount(0);
        scoreTableModel.setColumnCount(0);
        scoreTableModel.setColumnIdentifiers(new Object[]{"User","Score"});
        for(Map.Entry entry : scores.entrySet()){
            String user = entry.getKey().toString();
            String score = entry.getValue().toString();
            Object[] row = {user,score};
            scoreTableModel.addRow(row);
        }
    }

    @Override
    public void update() {
        JTextArea resultLabel = home.getResultLabel();
        Map<String,Boolean> validatorsResult = coreFitech.getResult();
        String labelText = "No puede usar la máquina";
        Boolean finalResult = true;
        for(Map.Entry<String,Boolean> entry : validatorsResult.entrySet()){
            if(!entry.getValue()){
                labelText += "\n" + entry.getKey() + " Falló";
                System.out.println(labelText);
                resultLabel.setText(labelText);
                finalResult = false;
            }
        }
        System.out.println("\u001B[33mHome Controller Update\u001B[0m");
        if(finalResult){
            resultLabel.setText("Puede utilizar la máquina");
            resultLabel.setForeground(Color.GREEN);
            scoreFitech.addScore(home.getUserNameTextField().getText(),50);
        }else{
            resultLabel.setForeground(Color.RED);
        }
        updateScoreTable();
    }
}