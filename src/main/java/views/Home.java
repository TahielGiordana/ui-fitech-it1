package views;

import frontControllers.HomeController;
import pojos.Machine;
import pojos.Person;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private JPanel contentPanel;
    private JTextField nameTextField;

    private JTextField lastnameTextField;

    private JTextField machineTextField;

    private JButton validatorBtn;

    private HomeController homeController;

    public Home(){
        super("Fitech");
        createUIComponents();
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600,600));
        setResizable(false);

        addActions();
        homeController = new HomeController();
        setVisible(true);

    }

    private void addActions(){
        validatorBtn.addActionListener(e -> {
            String name = nameTextField.getText();
            String lastname = lastnameTextField.getText();
            String machineCode = machineTextField.getText();
            Person person = new Person(name,lastname);
            Machine machine =new Machine(machineCode, "Stationary Bike");

            System.out.println("Se va validar persona con maquina: Persona ->" + person);
            System.out.println("Codigo Maquina -> " + machineCode);
            System.out.println("Probando");

            System.out.println("Maquina hardcodeada -> " + machine);
            homeController.validar(person, machine);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Color primaryColor = new Color(230, 230, 230);
        Color secondaryColor = new Color(50,50,50);
        Color accentColor = new Color(200, 50, 50);

        Font titleFont = new Font("Poppins", Font.BOLD,48);
        Font formFont = new Font("Poppins", Font.PLAIN, 24);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(secondaryColor);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(null);
        JLabel title = new JLabel("Fitech");
        title.setForeground(accentColor);
        title.setFont(titleFont);
        titlePanel.add(title);

        JPanel formPanel = new JPanel();

        GridLayout formLayout = new GridLayout(3,1);
        formPanel.setLayout(formLayout);
        formPanel.setBackground(null);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setForeground(primaryColor);
        nameLabel.setFont(formFont);

        nameTextField = new JTextField();
        nameTextField.setForeground(secondaryColor);
        nameTextField.setBackground(primaryColor);
        nameTextField.setFont(formFont);
        nameTextField.setColumns(20);
        nameTextField.setMargin(new Insets(2,5,2,5));

        JPanel nameWrapper = new JPanel();
        nameWrapper.setBackground(null);
        nameWrapper.setLayout(new FlowLayout());
        nameWrapper.add(nameLabel);
        nameWrapper.add(nameTextField);
        formPanel.add(nameWrapper);

        JLabel lastnameLabel = new JLabel("Apellido:");
        lastnameLabel.setForeground(primaryColor);
        lastnameLabel.setFont(formFont);
        lastnameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        lastnameTextField = new JTextField();
        lastnameTextField.setForeground(secondaryColor);
        lastnameTextField.setBackground(primaryColor);
        lastnameTextField.setFont(formFont);
        lastnameTextField.setColumns(20);
        lastnameTextField.setMargin(new Insets(2,5,2,5));

        JPanel lastnameWrapper = new JPanel();
        lastnameWrapper.setBackground(null);
        lastnameWrapper.setLayout(new FlowLayout());
        lastnameWrapper.add(lastnameLabel);
        lastnameWrapper.add(lastnameTextField);
        formPanel.add(lastnameWrapper);

        JLabel machineLabel = new JLabel("Máquina:");
        machineLabel.setForeground(primaryColor);
        machineLabel.setFont(formFont);
        machineLabel.setHorizontalAlignment(SwingConstants.LEFT);

        machineTextField = new JTextField();
        machineTextField.setForeground(secondaryColor);
        machineTextField.setBackground(primaryColor);
        machineTextField.setFont(formFont);
        machineTextField.setColumns(20);
        machineTextField.setMargin(new Insets(2,5,2,5));

        JPanel machineWrapper = new JPanel();
        machineWrapper.setBackground(null);
        machineWrapper.setLayout(new FlowLayout());
        machineWrapper.add(machineLabel);
        machineWrapper.add(machineTextField);
        formPanel.add(machineWrapper);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(null);
        validatorBtn = new JButton("Validar");
        validatorBtn.setForeground(accentColor);
        validatorBtn.setBackground(primaryColor);
        validatorBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor,2),
                BorderFactory.createEmptyBorder(5,20,5,20)
        ));
        validatorBtn.setFont(formFont);
        resultPanel.add(validatorBtn);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(resultPanel, BorderLayout.SOUTH);
    }
}
