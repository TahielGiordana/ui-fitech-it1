package views;

//java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//custom imports
import core.CoreFitech;
import frontControllers.HomeController;
import services.ScoreService;


public class Home extends JFrame {
    private static final int MIN_WIDTH = 1200;
    private static final int MIN_HEIGHT = 600;
    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final Font SCORE_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final String TITLE = "Fitech";

    // Componentes de la interfaz gr?fica
    private JPanel contentPanel;

    private JPanel checkValidatorsPanel;
    private JTextField userNameTextField;
    private JTextArea resultLabel;

    private JButton validatorBtn;
    private DefaultTableModel scoreModel;

    public Home(CoreFitech coreFitech, ScoreService scoreFitech) {
        super(TITLE);
        createUIComponents();
        new HomeController(this, coreFitech, scoreFitech);
        setVisible(true);
    }

    private void createUIComponents() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(SECONDARY_COLOR);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(null);
        JLabel title = new JLabel(TITLE);
        title.setForeground(ACCENT_COLOR);
        title.setFont(TITLE_FONT);
        titlePanel.add(title);

        JPanel bodyPanel = new JPanel();
        GridLayout bodyLayout = new GridLayout(1,3);
        bodyLayout.setHgap(10);
        bodyPanel.setLayout(bodyLayout);
        bodyPanel.setBackground(null);

        //TODO Panel de Puntuaciones

        JScrollPane scorePanel = new JScrollPane();

        scoreModel = new DefaultTableModel(null, new String[] {"User", "Score"}){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        JTable scoreTable = new JTable(scoreModel);
        scoreTable.setFont(SCORE_FONT);
        scoreTable.setRowHeight(SCORE_FONT.getSize() + 4);
        scoreTable.getTableHeader().setFont(SCORE_FONT);

        scorePanel.setViewportView(scoreTable);

        bodyPanel.add(scorePanel);

        //TODO Panel Formulario

        JPanel formPanel = new JPanel();
        GridLayout formLayout = new GridLayout(2, 1);
        formLayout.setVgap(10);
        formPanel.setLayout(formLayout);
        formPanel.setBackground(null);

        JPanel inputPanel = new JPanel();
        GridLayout inputLayout = new GridLayout(2,1);
        inputPanel.setLayout(inputLayout);
        inputPanel.setBackground(Color.ORANGE);

        JLabel nameLabel = new JLabel("Nombre de usuario:");
        nameLabel.setForeground(PRIMARY_COLOR);
        nameLabel.setFont(FORM_FONT);

        userNameTextField = new JTextField();
        userNameTextField.setForeground(SECONDARY_COLOR);
        userNameTextField.setBackground(PRIMARY_COLOR);
        userNameTextField.setFont(FORM_FONT);
        userNameTextField.setColumns(14);
        userNameTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel nameWrapper = new JPanel();
        nameWrapper.setBackground(null);
        nameWrapper.setLayout(new FlowLayout());
        nameWrapper.add(nameLabel);
        nameWrapper.add(userNameTextField);
        inputPanel.add(nameWrapper);

        validatorBtn = new JButton("Ingresar");
        validatorBtn.setForeground(ACCENT_COLOR);
        validatorBtn.setBackground(PRIMARY_COLOR);
        validatorBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        validatorBtn.setFont(FORM_FONT);

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setBackground(null);
        buttonWrapper.setLayout(new FlowLayout());
        buttonWrapper.add(validatorBtn);
        inputPanel.add(buttonWrapper);

        formPanel.add(inputPanel);

        JPanel resultPanel = new JPanel();
        GridLayout resultLayout = new GridLayout(1, 1);
        resultPanel.setBackground(Color.YELLOW);
        resultPanel.setLayout(resultLayout);

        resultLabel = new JTextArea();
        resultLabel.setBackground(SECONDARY_COLOR);
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setFont(SCORE_FONT);
        resultLabel.setBorder(null);
        resultLabel.setColumns(15);
        resultLabel.setEditable(false);

        JScrollPane scrollResultPanel = new JScrollPane(resultLabel);
        scrollResultPanel.setBorder(null);
        resultPanel.setBackground(null);
        resultPanel.setLayout(resultLayout);
        resultPanel.add(scrollResultPanel);

        formPanel.add(resultPanel);

        bodyPanel.add(formPanel);

        //TODO Validators Panel
        JScrollPane checkValidatorsScrollPanel = new JScrollPane();
        checkValidatorsScrollPanel.setBackground(null);
        checkValidatorsPanel = new JPanel();
        BoxLayout checkValidatorsLayout = new BoxLayout(checkValidatorsPanel,BoxLayout.Y_AXIS);

        checkValidatorsPanel.setLayout(checkValidatorsLayout);
        checkValidatorsPanel.setBackground(Color.PINK);

        checkValidatorsScrollPanel.setViewportView(checkValidatorsPanel);
        bodyPanel.add(checkValidatorsScrollPanel);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(bodyPanel, BorderLayout.CENTER);

        setContentPane(contentPanel);
    }

    public JTextArea getResultLabel(){
        return this.resultLabel;
    }

    public JTextField getUserNameTextField(){
        return this.userNameTextField;
    }

    public JButton getValidatorBtn(){
        return this.validatorBtn;
    }

    public DefaultTableModel getScoreTableModel(){
        return this.scoreModel;
    }

    public JPanel getValidatorsPanel(){
        return this.checkValidatorsPanel;
    }
}