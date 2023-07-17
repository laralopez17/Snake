package snake_multiplayer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int width;
    private int height;
    private List<Snake> snakes;
    private Apple apple;
    
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.snakes = new ArrayList<>();
        
    }
    public boolean containsPoint(Point point) {
        for (Snake snake : snakes) {
            if (snake.getBody().contains(point)) {
                return true;
            }
        }
        return false;
    }
    
  public void initGame() {
        this.apple = new Apple(new Point(0, 0), this);  // Pasamos el tablero a la manzana
        apple.moveToNewLocation(this);  // La manzana se mueve a una nueva ubicación válida
    }

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public boolean checkBounds(Point point) {
        return point.x >= 0 && point.x < width && point.y >= 0 && point.y < height;
    }

    public boolean checkSnakeCollision(Snake snake) {
        for (Snake s : snakes) {
            if (s != snake && s.getBody().contains(snake.getBody().get(0))) {
                return true;
            }
        }
        return false;
    }

    public boolean isCellEmpty(Point point) {
        for (Snake snake : snakes) {
            if (snake.getBody().contains(point)) {
                return false;
            }
        }
        return true;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public Apple getApple() {
        return this.apple;
    }
    
}
