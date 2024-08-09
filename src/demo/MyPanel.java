package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener, ActionListener {
    // ImageIcon right = new ImageIcon("images/head.png");
    // ImageIcon body = new ImageIcon("images/body.png");
    ImageIcon right;
    ImageIcon body;
    ImageIcon top;
    ImageIcon bottom;
    ImageIcon left;

    int len = 3;
    int[] snakeX = new int[1008];
    int[] snakeY = new int[1008];

    Direction direction = Direction.right;

    // declare a variable: if play is start.
    boolean isStart = false;
    // Create a timer object
    Timer timer = new Timer(300, this);

    public MyPanel() {
        // Resize the images
        right = resizeImageIcon("images/headRight.png", 25, 25); // Resize head.png to 50x50
        body = resizeImageIcon("images/body.png", 25, 25); // Resize body.png to 50x50

        top = resizeImageIcon("images/headTop.png", 25, 25);
        bottom = resizeImageIcon("images/headBottom.png", 25, 25);
        left = resizeImageIcon("images/headLeft.png", 25, 25);

        // Setting the initial position of the snake's head and body
        snakeX[0] = 90;
        snakeY[0] = 90;

        snakeX[1] = 75;
        snakeY[1] = 110;
        snakeX[2] = 50;
        snakeY[2] = 110;

        // Getting keyboard events.
        this.setFocusable(true);
        // Adding a Listener
        this.addKeyListener(this);

        // Start Timer
        timer.start();
    }

    // Helper method to resize an image and return an ImageIcon
    private ImageIcon resizeImageIcon(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.CYAN);
        g.fillRect(0, 0, 700, 900);

        /*
         * 添加右侧蛇头
         * right.paintIcon(this, g, 90, 90);
         * right.paintIcon(this, g, snakeX[0], snakeY[0]);
         * /*
         * body.paintIcon(this, g, 75, 110);
         * body.paintIcon(this, g, 50, 110);
         */
        switch (direction) {
            case top:
                top.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case bottom:
                bottom.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case left:
                left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case right:
                right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        if (!isStart) {
            g.setColor(Color.ORANGE);
            g.setFont(new Font("songti", Font.BOLD, 50));
            g.drawString("Press space to start!", 80, 500);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 32) {
            isStart = !isStart;
            repaint();
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = Direction.top;

        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = Direction.bottom;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            direction = Direction.left;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = Direction.right;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // when isStart is true, then game start
        if (isStart) {
            // move body
            for (int i = len - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            switch (direction) {
                case top:
                snakeY[0] -= 25;
                if(snakeY[0] <= 0){
                    snakeY[0] = 900;
                }
                    break;
                case bottom:
                snakeY[0] +=25;
                if (snakeY[0] <= 0) {
                    snakeY[0] = 0;
                }
                    break;
                case left:
                snakeX[0] -=25;
                if(snakeX[0] <= 0){
                    snakeX[0] =700;
                }
                    break;
                case right:
                    // move head
                    snakeX[0] += 25;
                    // Determining the value of head， if more than 700, value x restart from 0
                    if (snakeX[0] >= 700) {
                        snakeX[0] = 0;
                    }
                    break;
            }
            repaint();
            timer.start();
        }

    }
}
