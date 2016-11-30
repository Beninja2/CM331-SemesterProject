/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author rndmorris
 */
public class DoorHandler implements EventHandler<MouseEvent>{
    private Door[] doors;
    public DoorHandler(Door[] doors) {
        super();
        this.doors = doors;
    }
    @Override
    public void handle (MouseEvent event) {
        ((Door)event.getSource()).openDoor();
    }
    
}
