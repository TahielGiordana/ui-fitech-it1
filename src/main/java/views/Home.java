package views;

import frontControllers.HomeController;
import init.Core;
import interfaces.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

public class Home extends JFrame implements Observer {
    // Constantes
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 600;
    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final String TITLE = "Fitech";
    private static final String VALID_MACHINE_MESSAGE = "Puede usar la maquina";
    private static final String INVALID_MACHINE_MESSAGE = "No puede usar la maquina";

    // Componentes de la interfaz gráfica
    private JPanel contentPanel;
    private JTextField userNameTextField;
    private JTextField machineSerialCodeTextField;
    private JButton validatorBtn;
    private JLabel resultLabel;

    // Controlador
    private HomeController homeController;
    private Core core;


    public Home(Core core) {
        super(TITLE);
        this.core = core;
        core.addObserver(this);
        this.homeController = new HomeController(this);


        createUIComponents();
        setUpActions();
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        homeController.startValidationTask();
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

        userNameTextField = new JTextField();
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

        machineSerialCodeTextField = new JTextField();
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
        resultPanel.setBackground(null);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS));
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(resultLabel);

        validatorBtn = new JButton("Validar");
        validatorBtn.setForeground(ACCENT_COLOR);
        validatorBtn.setBackground(PRIMARY_COLOR);
        validatorBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        validatorBtn.setFont(FORM_FONT);
        //resultPanel.add(validatorBtn);

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setBackground(null);
        buttonWrapper.setLayout(new FlowLayout());
        buttonWrapper.add(validatorBtn);
        resultPanel.add(buttonWrapper);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(resultPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private void setUpActions() {
        userNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }
        });

        machineSerialCodeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                homeController.validate(userNameTextField.getText(),machineSerialCodeTextField.getText());
            }
        });

        validatorBtn.addActionListener(e -> {
            String userName = userNameTextField.getText();
            String machineCode = machineSerialCodeTextField.getText();

            homeController.validate(userName,machineCode);
            /*
            boolean machineAvailable = homeController.validate(userName,machineCode);
            if (machineAvailable) {
                resultLabel.setText(VALID_MACHINE_MESSAGE);
            } else {
                resultLabel.setText(INVALID_MACHINE_MESSAGE);
            }*/
        });
    }

    public JLabel getResultLabel(){
        return this.resultLabel;
    }

    public void update() {

    }

    public Core getCore(){
        return this.core;
    }
}
