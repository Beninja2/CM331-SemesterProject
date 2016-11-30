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
    public DoorHandler(DoorHolder doorHolder) {
        super();
        this.doorHolder = doorHolder;
    }
    @Override
    public void handle (MouseEvent event) {
        Door[] doorArray = doorHolder.getDoorArray();
        for(int i = 0; i < doorArray.length; i++) {
            doorArray[i].setSelected(false);
        }
        Door source = (Door)event.getSource();
        source.setSelected(true);
        doorHolder.setSelectedDoor(source.getDoorId());
        System.out.println("Currently selected door = " + source.getDoorId());
    }
    
}
