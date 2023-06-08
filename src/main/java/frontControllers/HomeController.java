package frontControllers;

import core.CoreFitech;
import interfaces.Observer;
import interfaces.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.ScoreService;
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
    //private final ScoreService scoreFitech;
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
        //coreFitech.addObserver(this);
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
        for(Validator v : validators){
            String checkText = v.getClass().getName();
            JCheckBox check = new JCheckBox(checkText);
            check.setBackground(null);
            check.setForeground(Color.BLACK);
            check.setFont(new Font("Poppins", Font.PLAIN, 28));
            check.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        //validationTask.checkValidator(checkText, true);
                    }else{
                        //validationTask.checkValidator(checkText,false);
                    }
                }
            });
            check.setSelected(true);
            validatorsPanel.add(check);
            checkBoxes.add(check);
        }
    }

    /*private void enableAllCheckBoxes(boolean bool){
        for(JCheckBox checkBox : checkBoxes){
            checkBox.setEnabled(bool);
        }
    }*/

    @Override
    public void update() {
        /*
        JTextArea resultLabel = home.getResultLabel();
        Map<String,Boolean> validatorsResult = coreFitech.getResult();
        String labelText = "No puede usar la m�quina";
        Boolean finalResult = true;
        for(Map.Entry<String,Boolean> entry : validatorsResult.entrySet()){
            if(!entry.getValue()){
                labelText += "\n" + entry.getKey() + " Fall�";
                System.out.println(labelText);
                resultLabel.setText(labelText);
                finalResult = false;
            }
        }
        System.out.println("\u001B[33mHome Controller Update\u001B[0m");
        if(finalResult){
            resultLabel.setText("Puede utilizar la m�quina");
            resultLabel.setForeground(Color.GREEN);
            scoreFitech.addScore(home.getUserNameTextField().getText(),50);
        }else{
            resultLabel.setForeground(Color.RED);
        }
        updateScoreTable();
         */
    }

    @Override
    public void update(Boolean result) {
        JTextArea resultLabel = home.getResultLabel();
        if (!isButtonEnabled) {
            System.out.println("\u001B[33mHome Controller Update\u001B[0m");
            log.info("metodo update - result: {} ", result);
            if (result != null) {
                resultLabel.setText(result ? "Puede utilizar la m?quina" : "No puede utilizar la m?quina");
                resultLabel.setForeground(result ? Color.GREEN : Color.RED);
            }
        }
    }
}