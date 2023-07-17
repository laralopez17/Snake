package snake_multiplayer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> body;
    private Direction direction;

    public Snake(Point startingPosition, Direction startingDirection) {
        body = new ArrayList<>();
        body.add(startingPosition);
        direction = startingDirection;
    }

    public void move(Apple apple, Board board) {
        Point head = body.get(0);
        Point newHead;

        switch (direction) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
            default:
                throw new IllegalStateException("Unexpected direction: " + direction);
        }

        if (newHead.equals(apple.getPosition())) {
            body.add(0, newHead);
            apple.moveToNewLocation(board);
        } else {
            body.add(0, newHead);
            body.remove(body.size() - 1);
        }

        /*if (body.size() > 1 && otherSnake.containsPoint(newHead)) {
            // Colisión con la otra serpiente
            // Aquí puedes llamar al método gameOver o realizar cualquier otra acción necesaria
        }*/
    }



    public void changeDirection(Direction newDirection) {
    // La serpiente no puede dar vuelta en 180 grados instantáneamente
    if (newDirection == Direction.UP && direction != Direction.DOWN ||
        newDirection == Direction.DOWN && direction != Direction.UP ||
        newDirection == Direction.LEFT && direction != Direction.RIGHT ||
        newDirection == Direction.RIGHT && direction != Direction.LEFT) {
        
        direction = newDirection;
        System.out.println("La dirección de la serpiente ha cambiado a: " + newDirection);
    }
}


    public List<Point> getBody() {
        return body;
    }
    
    public boolean checkCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    
    public Direction getCurrentDirection() {
        System.out.println("La dirección actual de la serpiente es: " + this.direction);
        return direction;
    }
    
    public boolean containsPoint(Point point) {
        return body.contains(point);
    }
    
    public boolean checkCollisionWithOtherSnake(Snake other) {
        for (int i = 1; i < body.size(); i++) {
            if (other.getBody().contains(body.get(i))) {
                return true;
            }
        }
        return false;
}
}

