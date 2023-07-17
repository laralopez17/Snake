package snake_multiplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame {
    private final JButton restartButton;

    public GameOverScreen(int score) {
        setTitle("Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla

        // Crea un panel con un layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crea un mensaje de Game Over
        JLabel gameOverMessage = new JLabel("Game Over!");
        gameOverMessage.setFont(new Font("Serif", Font.BOLD, 24));
        gameOverMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(gameOverMessage);

        // Muestra la puntuación
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(scoreLabel);

        // Crea un botón de reinicio
        restartButton = new JButton("Play Again");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(restartButton);

        add(panel);
    }

    public void setRestartButtonAction(ActionListener action) {
        restartButton.addActionListener(action);
    }
}
