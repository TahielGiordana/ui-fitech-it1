package views;

import frontControllers.HomeController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class HomeView extends JFrame {

    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final String TITLE = "Fitech";

    // Componentes de la interfaz gráfica
    private JPanel contentPanel;
    private JTextField userNameTextField;
    private JTextField machineSerialCodeTextField;
    private JLabel resultLabel;
    private HomeController homeController;

    public HomeView(HomeController homeController) {
        this.homeController = homeController;
        createUIComponents();
        setUpActions();
        setContentPane(contentPanel);
    }

    private void createUIComponents() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(SECONDARY_COLOR);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(null);
        JLabel title = new JLabel(TITLE);
        title.setForeground(ACCENT_COLOR);
        title.setFont(TITLE_FONT);
        titlePanel.add(title);

        JPanel formPanel = new JPanel();
        GridLayout formLayout = new GridLayout(3, 1);
        formPanel.setLayout(formLayout);
        formPanel.setBackground(null);

        JLabel nameLabel = new JLabel("Nombre de usuario:");
        nameLabel.setForeground(PRIMARY_COLOR);
        nameLabel.setFont(FORM_FONT);

        userNameTextField = new JTextField("");
        userNameTextField.setForeground(SECONDARY_COLOR);
        userNameTextField.setBackground(PRIMARY_COLOR);
        userNameTextField.setFont(FORM_FONT);
        userNameTextField.setColumns(20);
        userNameTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel nameWrapper = new JPanel();
        nameWrapper.setBackground(null);
        nameWrapper.setLayout(new FlowLayout());
        nameWrapper.add(nameLabel);
        nameWrapper.add(userNameTextField);
        formPanel.add(nameWrapper);

        JLabel machineLabel = new JLabel("Codigo de maquina:");
        machineLabel.setForeground(PRIMARY_COLOR);
        machineLabel.setFont(FORM_FONT);
        machineLabel.setHorizontalAlignment(SwingConstants.LEFT);

        machineSerialCodeTextField = new JTextField("");
        machineSerialCodeTextField.setForeground(SECONDARY_COLOR);
        machineSerialCodeTextField.setBackground(PRIMARY_COLOR);
        machineSerialCodeTextField.setFont(FORM_FONT);
        machineSerialCodeTextField.setColumns(20);
        machineSerialCodeTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel machineWrapper = new JPanel();
        machineWrapper.setBackground(null);
        machineWrapper.setLayout(new FlowLayout());
        machineWrapper.add(machineLabel);
        machineWrapper.add(machineSerialCodeTextField);
        formPanel.add(machineWrapper);

        resultLabel = new JLabel("");
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setFont(FORM_FONT);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel resultPanel = new JPanel();
        GridLayout resultLayout = new GridLayout(3, 1);
        resultPanel.setBackground(null);
        resultPanel.setLayout(resultLayout);
        resultPanel.add(resultLabel);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(resultPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private void setUpActions() {
        userNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }
        });

        machineSerialCodeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                homeController.post(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }
        });
    }

    public JLabel getResultLabel(){
        return this.resultLabel;
    }
}
