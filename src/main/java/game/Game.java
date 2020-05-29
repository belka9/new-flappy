package game;

import control.Control;
import model.Model;
import view.View;

public class Game {
    private final Model model;
    private final Control control;
    private final View view; 

    public Game() {
        model = new Model();
        control = new Control(model);
        view = new View(control); 
        
        model.addObserver(view.getGameFrame());
    }

    
    
    public void start(){ 
        model.start();
    }
    
    
}
