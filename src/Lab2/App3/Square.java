package Lab2.App3;

import java.awt.*;

public class Square implements Runnable {

    private int x;
    private int y;
    private int size;
    private Color color;
    private int speed;

    public Square(int x, int y, int size, Color c) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = c;
        this.speed = (int) (Math.random() * (10 - 5 + 1) + 5); // Random speed between 5 and 10
    }

    public void setSpeed(int s) {
        this.speed = s;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public void move() {
        y += speed;
    }

    public void run() {
        while (y < 500) {
            move();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

