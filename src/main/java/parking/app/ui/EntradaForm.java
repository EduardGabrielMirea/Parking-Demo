package parking.app.ui;

import parking.app.controller.Controlador;
import parking.app.entity.Estancia;
import parking.app.entity.Vehiculo;
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
                try {
                    String matricula = textFieldMatricula.getText();
                    if (matricula.isEmpty()) {
                        throw new IllegalArgumentException("La matrícula no puede estar vacía");
                    }
                    controlador.registrarEntrada(matricula);
                    JOptionPane.showMessageDialog(null, "Entrada registrada para: " + matricula);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        registrarSalidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String matricula = textFieldMatricula.getText();
                    if (matricula.isEmpty()){
                        throw new IllegalArgumentException("La matricula no puede estar vacía");
                    }else {
                        controlador.registrarSalida(matricula);
                        JOptionPane.showMessageDialog(null, "Salida registrada para: " + matricula);
                        Vehiculo vehiculo = controlador.obtenerVehiculo(matricula);
                        Estancia estancia = controlador.estanciasActivas.get(matricula);
                        vehiculo.mostrarPago(estancia, vehiculo); // Llama a la función para mostrar el pago
                        JOptionPane.showMessageDialog(null, "Salida registrada para: " + matricula);
                    }
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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

    public void runEntrada() {
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);

    }
}
