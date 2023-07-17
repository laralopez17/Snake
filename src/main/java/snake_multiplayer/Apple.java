package snake_multiplayer;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Apple {
    private Point position;
    private int boardWidth;
    private int boardHeight;
    private Random random = new Random();
    private boolean isFirstMove = true;

    private Board board;

    public Apple(Point position, Board board) {
        this.position = position;
        this.board = board;
        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();
    }

    public void moveToNewLocation(Board board) {
        int newX, newY;
        boolean validPosition;

        do {
            newX = random.nextInt(boardWidth);
            newY = random.nextInt(boardHeight);
            validPosition = true;

            // Verifica si la nueva posición colisiona con alguna serpiente
            for (Snake snake : board.getSnakes()) {
                if (snake.containsPoint(new Point(newX, newY))) {
                    validPosition = false;
                    break;
                }
            }

            // Verifica si la nueva posición colisiona con el borde del tablero
            if (!board.checkBounds(new Point(newX, newY))) {
                validPosition = false;
            }
        } while (!validPosition);

        position = new Point(newX, newY);
        isFirstMove = false;
    }


    // ...otros métodos...

    public Point getPosition() {
        return position;
    }
}

