package parking.app.ui;

import parking.app.controller.Controlador;
import parking.app.service.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EntradaForm {
    private JPanel mainPanel;
    private JButton registrarEntradaButton;
    private JButton registrarSalidaButton;
    private JTextField textFieldMatricula;
    private JLabel matriculaJLabel;
    private JButton volverAPrincipalButton;

    private final JFrame frame = Window.frame;
    private Controlador controlador;
    public EntradaForm() {
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
        volverAPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuForm menuForm = new MenuForm();
                menuForm.runMenuA();
            }
        });
    }

    public void runEntrada() {
        frame.setSize(800, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);

    }
}
