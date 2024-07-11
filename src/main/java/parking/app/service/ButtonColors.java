package parking.app.service;

import javax.swing.*;
import java.awt.*;

public class ButtonColors {

    public static void setHoverBackgroundColor(JButton button) {
        button.setBackground(new Color(255, 200, 0));
    }

    public static void setExitedBackgroundColor(JButton button) {
        button.setBackground(new Color(255, 255, 150));
    }

    public static void setColors(JButton button) {
        button.setBackground(new Color(255, 255, 204));
        button.setForeground(new Color(0, 102, 204));
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
    }
}
