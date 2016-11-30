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
public class Door extends StackPane {
    
    private static final String CLOSED = File.separator + "img" + File.separator + "door_closed.png";
    private static final String LOSER = File.separator + "img" + File.separator + "door_loser";
    private static final String WINNER = File.separator + "img" + File.separator + "door_winner";
    private boolean prize = false;
    private boolean selected = false;
    private int doorId = -1;
    private ImageView doorLayer = new ImageView(CLOSED);
    
    
    public Door(boolean prize,int doorId) {
        super();
        setPrize(prize);
        this.doorId = doorId;
        this.getChildren().add(doorLayer);
    }
    public boolean isPrize() {
        return prize;
    }
    public void setPrize(boolean prize) {
        this.prize = prize;
    }
    public void openDoor() {
        if (isPrize()) {
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
        this.selected = selected;
    }
    public boolean getSelected() {
        return selected;
    }
    public int getDoorId() {
        return this.doorId;
    }
}
