package views;

import core.Core;
import frontControllers.HomeController;
import interfaces.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


import frontControllers.HomeController;
import interfaces.Observer;
import services.ValidatorManager;

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
        ValidatorManager validatorManager = core.getValidatorManager();
        homeController = new HomeController(this, validatorManager);
        validatorManager.addObserver(homeController);

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