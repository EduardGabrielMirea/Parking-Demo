package parking.app;

import parking.app.controller.Controlador;
import parking.app.ui.MainForm;

import javax.swing.*;

public class ParkingApp {

    private Controlador controlador;

    public ParkingApp() {
        controlador = new Controlador();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainApp = new MainForm();
            mainApp.runMenu();
        });
    }
}
