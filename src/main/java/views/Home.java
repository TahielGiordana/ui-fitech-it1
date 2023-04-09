package views;

import frontControllers.HomeController;
import pojos.Machine;
import pojos.Person;
import pojos.Routine;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Home extends JFrame{
    private JPanel panel1;
    private JTextField textfieldNombre;
    private JTextField textfieldApellido;
    private JLabel labelMaquina;
    private JLabel labelApellido;
    private JLabel labelNombre;
    private JTextField textfieldMaquinaCode;
    private JButton buttonValidar;
    private HomeController homeController;

    public Home(){
        super("Proyecto UI FITECH");
        initComponents();
        addActions();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500,200));
        setTitle("Sistema Galaxy Shoes");
        setBounds(10, 10, 1000, 1000);

    }

    private void initComponents() {
        homeController = new HomeController();
        setContentPane(panel1);
    }

    private void addActions(){
        buttonValidar.addActionListener(e -> {
            String nombre = textfieldNombre.getText();
            String codeMaquina = textfieldMaquinaCode.getText();
            Person person = hardCodearPersona(nombre,codeMaquina);
            Machine machine = new Machine(codeMaquina,"maquina_1");

            System.out.println("Se va validar persona con maquina: Persona ->" + person);
            System.out.println("Codigo Maquina -> " + codeMaquina);

            System.out.println("Maquina hardcodeada -> " + machine);
            homeController.validar(person, machine);

        });
    }

    private Person hardCodearPersona(String name, String codeMaquina){
        Person person = new Person(new Routine("rutina_a", Arrays.asList(codeMaquina,"A2")), "name", "persona_1");
        return person;
    }
}
