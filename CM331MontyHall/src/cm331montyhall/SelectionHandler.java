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
    private DoorHolder doorHolder;
    private GameInstance currentGame;
    public DoorHandler(DoorHolder doorHolder, GameInstance currentGame) {
        super();
        this.doorHolder = doorHolder;
        this.currentGame = currentGame;
    }
    @Override
    public void handle (MouseEvent event) {
        Door source = (Door)event.getSource();
        if (!source.isOpened()) {
            Door[] doorArray = doorHolder.getDoorArray();
        for(int i = 0; i < doorArray.length; i++) {
            doorArray[i].setSelected(false);
        }
        source.setSelected(true);
        source.openDoor();
        doorHolder.setSelectedDoor(source.getDoorId());
        System.out.println("Currently selected door = " + source.getDoorId());
        }
    }
    
}
