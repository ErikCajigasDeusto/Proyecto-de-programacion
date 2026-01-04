package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import gui.SplashScreen; 

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            SplashScreen splash = new SplashScreen();
            splash.setVisible(true);
        });
    }
}

