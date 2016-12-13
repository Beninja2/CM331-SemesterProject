/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author rndmorris
 */
public class DoorImage extends StackPane {
    
    private static final String CLOSED = File.separator + "img" + File.separator + "door_closed.png";
    private static final String LOSER = File.separator + "img" + File.separator + "door_loser.png";
    private static final String WINNER = File.separator + "img" + File.separator + "door_winner.png";
    private ImageView doorLayer = new ImageView(CLOSED);
    private Door door;
    
    public DoorImage(Door door) {
        super();
        this.door = door;
        this.setHeight(doorLayer.getImage().getHeight()+10);
        this.setWidth(doorLayer.getImage().getWidth()+10);
        this.getChildren().add(doorLayer);
    }
    public void openDoor() {
        this.door.setOpened(true);
        if (this.door.isWinner()) {
            doorLayer.setImage(new Image(WINNER));
        } else {
            doorLayer.setImage(new Image(LOSER));
        }
    }
    public void setSelected(boolean selected) {
        if (selected) {
            this.setStyle("-fx-background-color: #00ff00");
        } else {
            this.setStyle("-fx-background-color: #ffffff");
        }
    }
    public Door getDoor() {
        return this.door;
    }
}
