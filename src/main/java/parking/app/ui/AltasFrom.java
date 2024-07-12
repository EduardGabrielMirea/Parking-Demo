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
                try {
                    String matricula = textFieldMatricula.getText();
                    if (matricula.isEmpty()){
                        throw new IllegalArgumentException("La matrícula no puede estar vacía");
                    }else {
                        controlador.darDeAltaVehiculoOficial(matricula);
                        JOptionPane.showMessageDialog(null, "Vehículo oficial dado de alta: " + matricula);
                    }
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        darDeAltaVehiculoResidenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String matricula = textFieldMatricula.getText();
                    if (matricula.isEmpty()){
                        throw new IllegalArgumentException("La matrícula no puede estar vacía");
                    }else {
                        controlador.darDeAltaVehiculoResidente(matricula);
                        JOptionPane.showMessageDialog(null, "Vehículo residente dado de alta: " + matricula);
                    }
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
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
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}
