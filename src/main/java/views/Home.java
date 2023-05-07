package views;

//java
import javax.swing.*;
import java.awt.*;

//custom imports
import core.Core;
import frontControllers.HomeController;
import services.ValidationEngine;


public class Home {
    // Constantes
    private static final String TITLE = "Fitech";
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 500;

    // Componentes de la interfaz gráfica
    private HomeView homeView;

    // Controlador
    private HomeController homeController;

    public Home(Core core) {
        ValidationEngine validationEngine = core.getValidatorManager();
        homeController = new HomeController(this, validationEngine);
        validationEngine.addObserver(homeController);

        createUIComponents();


        homeController.startValidationTask();
    }

    private void createUIComponents() {
        homeView = new HomeView(homeController);
        homeView.setTitle(TITLE);
        homeView.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        homeView.setResizable(false);
        homeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeView.setVisible(true);
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public void updateResult(boolean validationResult) {
        homeView.getResultLabel().setText(Boolean.toString(validationResult));
    }
}