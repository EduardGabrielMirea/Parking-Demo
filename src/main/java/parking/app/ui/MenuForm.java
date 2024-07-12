package parking.app.ui;

import parking.app.controller.Controlador;
import parking.app.service.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//[1440,700] main
//[350, 900] asideLeft
public class MenuForm {

    private Controlador controlador;
    private final JFrame frame = Window.frame;


    private JPanel mainPanel;
    private JLabel carPhotoJLabel;
    private JPanel asideRightJPanel;
    private JButton gestionarAltaButton;
    private JButton registrarMovimientosButton;
    private JPanel headerLeftJPanel;
    private JPanel asideLeftJPanel;
    private JButton generarInformePagosButton;
    private JButton comenzarMesButton;
    private JButton workerDataButton;

    public MenuForm() {
        controlador = new Controlador();

        gestionarAltaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AltasFrom altasFrom = new AltasFrom();
                altasFrom.runAltas();
            }
        });
        registrarMovimientosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EntradaForm entradaForm = new EntradaForm();
                entradaForm.runEntrada();
            }
        });
        generarInformePagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Nombre del archivo:");
                controlador.generarInformePagosResidentes(nombreArchivo);
                JOptionPane.showMessageDialog(null, "Informe generado: " + nombreArchivo);
                try {
                    String reportContent = new String(Files.readAllBytes(Paths.get(nombreArchivo)));

                    // Mostrar el contenido en un showMessageDialog
                    JOptionPane.showMessageDialog(null, reportContent, "Informe generado", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        comenzarMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.comenzarMes();
                JOptionPane.showMessageDialog(null, "Nuevo mes comenzado.");
            }
        });

    }


    public void runMenuA() {
        frame.setSize(800, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }



}

