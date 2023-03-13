package Lab2.App3;

import javax.swing.*;
import java.awt.*;

public class MovingSquares extends JFrame implements Runnable {

    private Square square1;
    private Square square2;
    private Square square3;
    private boolean running;

    public MovingSquares() {
        super("Moving Squares");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        square1 = new Square(50, 50, 50, Color.RED);
        square2 = new Square(150, 50, 50, Color.BLUE);
        square3 = new Square(250, 50, 50, Color.GREEN);

        running = true;
    }

    public void start() {
        Thread t1 = new Thread(this);
        Thread t2 = new Thread(square1);
        Thread t3 = new Thread(square2);
        Thread t4 = new Thread(square3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public void stop() {
        running = false;
    }

    public void paint(Graphics g) {
        super.paint(g);
        square1.draw(g);
        square2.draw(g);
        square3.draw(g);
    }

    public void run() {
        while (running) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MovingSquares game = new MovingSquares();
        game.start();
    }
}

