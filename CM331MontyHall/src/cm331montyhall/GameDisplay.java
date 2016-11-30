package cm331montyhall;

import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

/**
 *
 * @author rndmorris
 */
public class GameDisplay extends TilePane{
    Door[] doors;
    GameInstance currentGame;
    DoorHandler doorHandler;
    
    public GameDisplay(GameInstance currentGame) {
        super();
        int doorCount = currentGame.getNumDoors();
        if(52 < doorCount) throw new java.lang.IllegalArgumentException();
        if(doorCount < 5) {
            this.setPrefColumns(doorCount);
        } else if (doorCount > 20) {
            this.setPrefColumns(13);
        }
        
        this.currentGame = currentGame;
        
        this.setHgap(5);
        this.setVgap(5);
        
        doors = new Door[doorCount];
        for(int i = 0; i < doorCount; i++) {
            doors[i] = new Door(currentGame.DoorArray.get(i));
            doors[i].setOnMouseReleased(doorHandler);
            this.getChildren().add(doors[i]);
        }
        doorHandler = new DoorHandler(doors);
        
    }
}
