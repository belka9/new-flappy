package control;

import java.awt.event.KeyEvent;
import model.Model;

public class Control {
    private final Model mod;

    public Control(Model mod) {
        this.mod = mod;
    }
    public void Action(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            mod.jump();
        }

    }
}
