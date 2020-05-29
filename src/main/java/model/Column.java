package model;

import java.util.Random;

public class Column {
    
    private final Random rand;
    private int x;
    public static int width = 100;
    public static int space = 200;
    private final int speed = -10;
    private final int height;
    
    private boolean passed;
    private boolean checked;

    public Column(int WIDTH) {
        x = WIDTH;
        rand = new Random();
        passed = false;
        checked = false;
        height = 50 + rand.nextInt(600);
    }
    
    public boolean move(){
        x += speed;
        return x < -width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public boolean isPassed() {
        return passed;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
}
