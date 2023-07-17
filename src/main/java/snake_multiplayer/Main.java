package snake_multiplayer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            GameFrame frame = new GameFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
            frame.setFocusable(true);
            frame.requestFocusInWindow();

        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
}

