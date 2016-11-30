/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rndmorris
 */
public class Door extends ImageView {
    private boolean prize = false;
    private final String doorClosed = File.separator + "img" + File.separator + "door_closed.png";
    private final String doorLoser = File.separator + "img" + File.separator + "door_loser";
    private final String doorWinner = File.separator + "img" + File.separator + "door_winner";
    
    public Door(boolean prize) {
        super();
        setPrize(prize);
    }
    public boolean isPrize() {
        return prize;
    }
    public void setPrize(boolean prize) {
        this.prize = prize;
    }
    public void openDoor() {
        if (isPrize()) {
            this.setImage(new Image(doorWinner));
        } else {
            this.setImage(new Image(doorLoser));
        }
    }
}
