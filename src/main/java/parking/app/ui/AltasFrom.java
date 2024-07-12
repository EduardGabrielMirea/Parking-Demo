package parking.app.ui;

import parking.app.controller.Controlador;
import parking.app.service.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AltasFrom {
    private JPanel mainPanel;
    private JButton darDeAltaVehiculoOficialButton;
    private JButton darDeAltaVehiculoResidenteButton;
    private JTextField textFieldMatricula;
    private JLabel matriculaJLabel;
    private JButton volverAPrincipalButton;


    private final JFrame frame = Window.frame;
    private Controlador controlador;
    public AltasFrom() {

        controlador = new Controlador();
        darDeAltaVehiculoOficialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = textFieldMatricula.getText();
                controlador.darDeAltaVehiculoOficial(matricula);
                JOptionPane.showMessageDialog(null, "Vehículo oficial dado de alta: " + matricula);
            }
        });
        darDeAltaVehiculoResidenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = textFieldMatricula.getText();
                controlador.darDeAltaVehiculoResidente(matricula);
                JOptionPane.showMessageDialog(null, "Vehículo residente dado de alta: " + matricula);
            }
        });
        volverAPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuForm menuForm = new MenuForm();
                menuForm.runMenuA();
            }
        });
    }

    public void runAltas() {
        frame.setSize(800, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}
