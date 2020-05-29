package model;

import java.util.Queue;

public class Collider {

    static boolean collide(int birdX, int birdY, Queue columns, int width, int height) {
        if (birdY < 0 || birdY > height) {
            return true;
        }
        for (Object i : columns) {
            Column col = (Column) i;
            if (((birdX + 20) > col.getX()) && (birdX < col.getX() + Column.width)) {
                if(!col.isPassed()){
                    col.setPassed(true);
                }
                if (birdY < col.getHeight()) {
                    return true;
                }
                if ((birdY + 20) > (col.getHeight() + Column.space)) {
                    return true;
                }
            }
        }
        return false;
    }

}
