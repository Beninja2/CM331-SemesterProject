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
public class PlayerUI extends TilePane{
    private final DoorImage[] doorArray;
    private final GameInstance currentGame;
    private final SelectionHandler doorHandler;
    
    public DoorImage[] getDoorArray()
    {
        return doorArray;
    }
    
    public PlayerUI(GameInstance currentGame) {
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
        
        doorArray = new DoorImage[currentGame.getNumDoors()];
        doorHandler = new SelectionHandler(doorArray, currentGame);
        
        for (int i = 0; i < doorArray.length; i++) {
            doorArray[i] = new DoorImage(currentGame.getDoorList().get(i));
            this.getChildren().add(doorArray[i]);
            doorArray[i].setOnMouseReleased(doorHandler);
        }
    }
    public SelectionHandler getDoorHandler() {
        return this.doorHandler;
    }
    
}
