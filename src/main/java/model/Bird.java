package model;

public class Bird {

    public final int WIDTH = 800, HEIGHT = 800;
    private int x;
    private int y;
    private int yMotion;

    public Bird() {
        x = WIDTH / 4 - 20;
        y = HEIGHT / 2;
    }

    public void jump() {
        yMotion -= 15;
    }

    public void gravitation() {
        if(yMotion < 15){
            yMotion++;
        }
        y += yMotion;
    }
    public int getY(){
        return y;
    }

    public int getX() {
        return x;
    }
}
