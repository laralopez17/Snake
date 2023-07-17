package snake_multiplayer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Board board;

    public GamePanel(Board board) {
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    // Define tus colores personalizados aquí
    private final Color snakeColor = new Color(0, 128, 0);  // Un verde más oscuro
    private final Color boardColor = new Color(224, 224, 224);  // Un gris claro
    private final Color boardLineColor = new Color(192, 192, 192);  // Un gris medio

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;  // Casteamos a Graphics2D

        int cellSize = Math.min(getWidth() / board.getWidth(), getHeight() / board.getHeight());

        // Dibuja el tablero
        g2d.setColor(boardLineColor);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                g2d.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        // Rellena el tablero
        g2d.setColor(boardColor);
        g2d.fillRect(0, 0, cellSize * board.getWidth(), cellSize * board.getHeight());

        // Dibuja las serpientes con gradientes y sombras
        for (Snake snake : board.getSnakes()) {
            for (Point point : snake.getBody()) {
                // Crea un gradiente de color para cada segmento de la serpiente
                GradientPaint gp = new GradientPaint(
                    point.x * cellSize, point.y * cellSize, snakeColor.darker(),  // Color más oscuro en la esquina superior izquierda
                    (point.x + 1) * cellSize, (point.y + 1) * cellSize, snakeColor.brighter());  // Color más claro en la esquina inferior derecha

                g2d.setPaint(gp);
                g2d.fillRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);

                // Agrega una sombra alrededor de cada segmento de la serpiente
                g2d.setColor(Color.BLACK);
                g2d.drawRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
            }
        }
        g2d.setColor(Color.RED);
        Point applePosition = board.getApple().getPosition();
        g2d.fillRect(applePosition.x * cellSize, applePosition.y * cellSize, cellSize, cellSize);
    }   
    
}
