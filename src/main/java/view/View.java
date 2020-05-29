package view;

import model.Observer;
import model.Model;
import model.Observable;
import control.Control;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import model.Column;

public class View extends JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    private final GameFrame gameFrame;

    private final Control controller;

    private final JLabel scoreLabel;
    private final JLabel gameOverLabel;

    public View(Control control) {

        WINDOW_WIDTH = 800;
        WINDOW_HEIGHT = 800;

        controller = control;

        super.setTitle("Game");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        super.setUndecorated(true);
        super.setResizable(false);

        super.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                controller.Action(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        gameFrame = new GameFrame();
        gameFrame.setLayout(new FlowLayout());

        Font font = new Font(null, Font.BOLD, 20);

        scoreLabel = new JLabel();
        scoreLabel.setFont(font);
        scoreLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.TOP);

        gameOverLabel = new JLabel("<html>Game over!<br>Press space to restart<br>Press ESC to exit.</html>");
        gameOverLabel.setFont(font);
        gameOverLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);

        gameFrame.add(scoreLabel);
        gameFrame.add(gameOverLabel);

        super.add(gameFrame);

        super.setVisible(true);

    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private class GameFrame extends JPanel implements ActionListener, Observer {

        Timer timer = new Timer(1, this);

        private int birdX;
        private int birdY;

        private boolean gameEnd;

        private int score;

        private Queue columns;

        public GameFrame() {
            timer.start();
        }

        @Override
        public void paint(Graphics g) {
            if (!gameEnd) {
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                g.setColor(Color.MAGENTA);
                g.fillRect(birdX, birdY, 20, 20);
                if(columns != null) {
                    for(Object i : columns) {
                        Column col = (Column) i;
                        g.setColor(Color.BLACK);
                        g.fillRect(col.getX(), 0, Column.width, col.getHeight());
                        g.fillRect(col.getX(), col.getHeight() + Column.space, Column.width, WINDOW_HEIGHT - (col.getHeight() - Column.space));
                    }
                }
                scoreLabel.setText(Integer.toString(score));
                scoreLabel.paint(g);
            } else {
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                gameOverLabel.paint(g);
                scoreLabel.paint(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void handleEvent(Observable o) {
            Model obj = (Model) o;

            birdY = obj.getBirdY();
            birdX = obj.getBirdX();

            gameEnd = obj.isGameEnd();

            score = obj.getScore();

            columns = obj.getColumns();
        }

    }

}

/*package com.company;

import java.awt.*;
import java.util.ArrayList;

public class View implements Observer{
    public final int WIDTH = 800, HEIGHT = 800;
    private int birdX, birdY;

    @Override
    public void handleEvent(Observable o) {
        Model obj = (Model)o;
        birdX = obj.bird.getX();
        birdY = obj.bird.getY();
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void repaint(Graphics g, ArrayList<Rectangle> columns, Bird bird, boolean gameOver, boolean started){
        g.setColor(Color.cyan);
        g.fillRect(0,0, WIDTH,HEIGHT);

        for(Rectangle column: columns){
            paintColumn(g, column);
        }

        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT - 120, WIDTH , HEIGHT -120);

        g.setColor(Color.green);
        g.fillRect(0,HEIGHT - 120, WIDTH , 20);

        g.setColor(Color.magenta);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));

        if(!started){
            g.drawString("Click to start", 75, HEIGHT/2 - 50);
        }
        if(gameOver){
            g.drawString("Game Over!", 100, HEIGHT/2 - 50);
        }
        if(!gameOver && started){
            g.drawString(String.valueOf(120), WIDTH/2 - 25, 100);
        }
    }

}*/
