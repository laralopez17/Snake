package snake_multiplayer;

import java.awt.Point;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;


public class GameFrame extends JFrame {
    private Board board;
    private Snake snake1;
    private Snake snake2;
    private Timer timer1;
    private Timer timer2;
    private GamePanel panel;

    public GameFrame() {
        initGame();
        panel = new GamePanel(board);
        add(panel);
        // Configura los KeyBindings para las teclas relevantes
        configureKeyBindings();
        
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    private void initGame() {
        // Crea el tablero y la serpiente
        board = new Board(20, 20);
        snake1 = new Snake(new Point(10, 10), Direction.DOWN);
        snake2 = new Snake(new Point(5, 5), Direction.UP);  // Cambia la posición inicial para snake2
        board.addSnake(snake1);
        board.addSnake(snake2);
        board.initGame();
        
        // Crea los temporizadores para mover las serpientes
        timer1 = createTimer(snake1, snake2);
        timer2 = createTimer(snake2, snake1);

        // Inicia los temporizadores
        timer1.start();
        timer2.start();
    }

    private void configureKeyBindings() {
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        ActionMap actionMap = panel.getActionMap();

        // Aquí irían todas tus KeyBindings para snake1 y snake2
        
        // KeyBindings para la serpiente 1
        // KeyBindings para la tecla UP
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upPressed1");
        actionMap.put("upPressed1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("UP key pressed");
                if (snake1.getCurrentDirection() != Direction.DOWN) {
                    snake1.changeDirection(Direction.UP);
                } else {
                    gameOver(2);
                }
            }
        });

        // KeyBindings para la tecla DOWN
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downPressed1");
        actionMap.put("downPressed1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DOWN key pressed");
                if (snake1.getCurrentDirection() != Direction.UP) {
                    snake1.changeDirection(Direction.DOWN);
                } else {
                    gameOver(2);
                }
            }
        });

        // KeyBindings para la tecla LEFT
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftPressed1");
        actionMap.put("leftPressed1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LEFT key pressed");
                if (snake1.getCurrentDirection() != Direction.RIGHT) {
                    snake1.changeDirection(Direction.LEFT);
                } else {
                    gameOver(2);
                }
            }
        });

        // KeyBindings para la tecla RIGHT
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightPressed1");
        actionMap.put("rightPressed1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RIGHT key pressed");
                if (snake1.getCurrentDirection() != Direction.LEFT) {
                    snake1.changeDirection(Direction.RIGHT);
                } else {
                    gameOver(2);
                }
            }
        });
        
// KeyBindings para la serpiente 2
        
        // KeyBindings para la tecla W para la serpiente 2
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "upPressed2");
        actionMap.put("upPressed2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (snake2.getCurrentDirection() != Direction.DOWN) {
                    snake2.changeDirection(Direction.UP);
                } else {
                    gameOver(1);
                }
            }
        });
        
        // KeyBindings para la tecla DOWN
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "downPressed2");
        actionMap.put("downPressed2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DOWN key pressed");
                if (snake2.getCurrentDirection() != Direction.UP) {
                    snake2.changeDirection(Direction.DOWN);
                } else {
                    gameOver(1);
                }
            }
        });

        // KeyBindings para la tecla LEFT
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "leftPressed2");
        actionMap.put("leftPressed2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LEFT key pressed");
                if (snake2.getCurrentDirection() != Direction.RIGHT) {
                    snake2.changeDirection(Direction.LEFT);
                } else {
                    gameOver(1);
                }
            }
        });

        // KeyBindings para la tecla RIGHT
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "rightPressed2");
        actionMap.put("rightPressed2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RIGHT key pressed");
                if (snake2.getCurrentDirection() != Direction.LEFT) {
                    snake2.changeDirection(Direction.RIGHT);
                } else {
                    gameOver(1);
                }
            }
        });
    }
    
    
    private Timer createTimer(Snake snake, Snake otherSnake) {
    return new Timer(1000, (ActionEvent e) -> {
        snake.move(board.getApple(), board);
        if (board.checkBounds(snake.getBody().get(0)) && !snake.checkCollision() && !board.checkSnakeCollision(snake)) {
            panel.repaint();
        } else {
            gameOver(snake == snake1 ? 2 : 1);
        }
    });
}
 

    private void gameOver(int winningSnake) {
        timer1.stop();
        timer2.stop();
        GameOverScreen gameOverScreen = new GameOverScreen(winningSnake);
    
        gameOverScreen.setRestartButtonAction(e -> {
            // Reinicia el juego cuando se presiona el botón de reinicio
            initGame();
            panel.setBoard(board);  // Actualiza el tablero en GamePanel
            gameOverScreen.dispose();  // Cierra la ventana de Game Over
        });
        gameOverScreen.setVisible(true);
    }
}
