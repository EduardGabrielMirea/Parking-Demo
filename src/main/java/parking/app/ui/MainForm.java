package parking.app.ui;

import parking.app.controller.Controlador;
import parking.app.service.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;
    private JTextField textFieldMatricula;
    private JButton registrarEntradaButton;
    private JButton registrarSalidaButton;
    private JButton darDeAltaVehiculoOficialButton;
    private JButton darDeAltaVehiculoResidenteButton;
    private JButton comenzarMesButton;
    private JButton generarInformePagosButton;
    private JTextField textFieldTipoCoche;
    private Controlador controlador;
    private final JFrame frame = Window.frame;

    public MainForm() {
        controlador = new Controlador();


        registrarEntradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = textFieldMatricula.getText();
                controlador.registrarEntrada(matricula);
                JOptionPane.showMessageDialog(null, "Entrada registrada para: " + matricula);
            }
        });

        registrarSalidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = textFieldMatricula.getText();
                controlador.registrarSalida(matricula);
                JOptionPane.showMessageDialog(null, "Salida registrada para: " + matricula);
            }
        });

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

        comenzarMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.comenzarMes();
                JOptionPane.showMessageDialog(null, "Nuevo mes comenzado.");
            }
        });

        generarInformePagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Nombre del archivo:");
                controlador.generarInformePagosResidentes(nombreArchivo);
                JOptionPane.showMessageDialog(null, "Informe generado: " + nombreArchivo);
            }
        });
    }

    public void runMenu() {
        JFrame frame = new JFrame("Parking Management System");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
