/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import javafx.scene.layout.TilePane;

/**
 *
 * @author rndmorris
 */
public class DoorHolder extends TilePane{
    private Door[] doorArray;
    private GameInstance currentGame;
    private int selectedDoor = -1;
    
    public DoorHolder(GameInstance currentGame) {
        this.currentGame = currentGame;
        if (52 < currentGame.getNumDoors()) {
            throw new java.lang.IllegalArgumentException();
        } else if (currentGame.getNumDoors() < 5) {
            this.setPrefColumns(currentGame.getNumDoors());
        } else if (currentGame.getNumDoors() >= 20) {
            this.setPrefColumns(13);
        }
        
        this.setVgap(5);
        this.setHgap(5);
        
        doorArray = new Door[currentGame.getNumDoors()];
        DoorHandler doorHandler = new DoorHandler(this);
        
        for (int i = 0; i < doorArray.length; i++) {
            doorArray[i] = new Door(currentGame.DoorArray.get(i),i);
            this.getChildren().add(doorArray[i]);
            doorArray[i].setOnMouseReleased(doorHandler);
        }
    }
    public int getSelectedDoor() {
        return this.selectedDoor;
    }
    public void setSelectedDoor(int selectedDoor) {
        if (selectedDoor < 0 || doorArray.length < selectedDoor) {
            throw new java.lang.IllegalArgumentException();
        } else {
            this.selectedDoor = selectedDoor;
        }
    }
    public Door[] getDoorArray() {
        return this.doorArray;
    }
    
}
