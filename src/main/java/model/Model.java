package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Model implements Observable {

    public final int WIDTH, HEIGHT;
    private int score;
    private Bird bird;
    private boolean gameEnd;
    private Queue columns = new LinkedList();
    private ArrayList<Observer> listeners;

    public Model() {
        WIDTH = 800;
        HEIGHT = 800;
        score = 0;
        bird = new Bird();
        gameEnd = false;
    }

    @Override
    public void addObserver(Observer observer) {
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listeners.remove(observer);
    }

    @Override
    public void update() {
        if (listeners != null) {
            for (Observer i : listeners) {
                if (i != null) {
                    i.handleEvent(this);
                }
            }
        }
    }

    public void jump() {
        if(!gameEnd) {
            bird.jump();
        } else {
            this.restart();
        }
    }

    public int getScore() {
        return score;
    }

    private class GameThread extends Thread {

        public GameThread() {
        }

        @Override
        public void run() {
            Random rand = new Random();
            int colTimer = 50;
            while (true) {
                if (!gameEnd) {
                    
                    bird.gravitation();
                    
                    if(colTimer < 0){
                        columns.add(new Column(WIDTH));
                        colTimer = 40 + rand.nextInt(20);
                    }
                    colTimer--;
                    
                    boolean flag = false;
                    for(Object i : columns){
                        Column col = (Column)i;
                        flag = col.move();
                        if(col.isPassed() && !col.isChecked()){
                            col.setChecked(true);
                            score++;
                        }
                    }
                    if(flag){
                        columns.poll();
                    }
                    
                    
                    if (Collider.collide(bird.getX(), bird.getY(), columns, WIDTH, HEIGHT)) {
                        gameEnd = true;
                    }

                    update();

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                    }
                } else {
                    break;
                }
            }
        }
    }

    public synchronized void start() {
        while (true) {
            GameThread gameThread = new GameThread();
            gameThread.start();
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }
    
    private synchronized void restart() {
        score = 0;
        bird = new Bird();
        gameEnd = false;
        columns.clear();
        notify();
    }

    public int getBirdY() {
        return bird.getY();
    }

    public int getBirdX() {
        return bird.getX();
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public Queue getColumns() {
        return columns;
    }
    
}