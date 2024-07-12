package parking.app.service;

import javax.swing.*;

public class Window extends JFrame {
    public static JFrame frame = new JFrame();
    public Window(){
        frame.setSize(800, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
