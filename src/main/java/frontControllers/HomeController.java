package frontControllers;

import core.CoreFitech;
import interfaces.Observer;
import interfaces.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.ValidationTask;
import services.score.ScoreTask;
import views.Home;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HomeController implements Observer {

    private final Logger log = LogManager.getLogger("HomeController");
    private final ValidationTask validationTask;
    private final ScoreTask scoreTask;
    private final Home home;

    private  boolean isButtonEnabled;

    private Set<JCheckBox> checkBoxes;


    public HomeController(Home home, CoreFitech coreFitech){
        this.home = home;
        this.validationTask = coreFitech.getValidationTask();
        this.validationTask.addObserver(this);
        this.scoreTask = coreFitech.getScoreTask();
        this.checkBoxes = new HashSet<>();
        setUpActions();
        this.isButtonEnabled = true;
        updateValidatorList();
        updateScoreTable();
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JTextArea resultLabel = home.getResultLabel();
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableButton();
            }

            private void enableButton() {
                validationTask.stopValidationTask();
                isButtonEnabled = true;
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }
        };

        user.getDocument().addDocumentListener(documentListener);

        validatorBtn.addActionListener(e -> {
            isButtonEnabled = false;
            validationTask.processRequest(user.getText());
            validatorBtn.setEnabled(false);
        });
    }

    public void updateScoreTable(){
        //Lleno tabla score
        DefaultTableModel scoreTableModel = home.getScoreTableModel();
        Map<String,Integer> scores = scoreTask.getAllScores();

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


    private void updateValidatorList(){
        JPanel validatorsPanel = home.getValidatorsPanel();
        Set<Validator> validators = validationTask.getValidators();
        JButton validatorBtn = home.getValidatorBtn();
        JTextArea resultLabel = home.getResultLabel();
        for(Validator v : validators){
            String checkText = v.getClass().getName();
            JCheckBox check = new JCheckBox(checkText);
            check.setBackground(null);
            check.setForeground(Color.BLACK);
            check.setFont(new Font("Poppins", Font.PLAIN, 28));
            check.setSelected(true);
            check.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    validationTask.stopValidationTask();
                    isButtonEnabled = true;
                    resultLabel.setText("");
                    validatorBtn.setEnabled(true);
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        validationTask.toggleValidatorCheck(checkText);
                    }else{
                        validationTask.toggleValidatorCheck(checkText);
                    }
                }
            });
            validatorsPanel.add(check);
            checkBoxes.add(check);
        }
    }

    @Override
    public void update() {
        JTextArea resultLabel = home.getResultLabel();
        Map<String,Boolean> validatorsResult = validationTask.getResult();
        String labelText = "No puede usar la maquina";
        Boolean finalResult = true;
        for(Map.Entry<String,Boolean> entry : validatorsResult.entrySet()){
            if(!entry.getValue()){
                labelText += "\n" + entry.getKey() + " Fallo";
                System.out.println(labelText);
                resultLabel.setText(labelText);
                finalResult = false;
            }
        }
        System.out.println("\u001B[33mHome Controller Update\u001B[0m");
        if(finalResult){
            resultLabel.setText("Puede utilizar la maquina");
            resultLabel.setForeground(Color.GREEN);
            scoreTask.addScore(home.getUserNameTextField().getText(),10);
        }else{
            resultLabel.setForeground(Color.RED);
        }
        updateScoreTable();
    }

    @Override
    public void update(Boolean result) {
        JTextArea resultLabel = home.getResultLabel();
        if (!isButtonEnabled) {
            System.out.println("\u001B[33mHome Controller Update\u001B[0m");
            log.info("metodo update - result: {} ", result);
            if (result != null) {
                resultLabel.setText(result ? "Puede utilizar la maquina" : "No puede utilizar la maquina");
                resultLabel.setForeground(result ? Color.GREEN : Color.RED);
                if (result){
                    scoreTask.addScore(home.getUserNameTextField().getText(), 10);
                    updateScoreTable();
                }
            }
        }
    }
}