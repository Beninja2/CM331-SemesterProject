package cm331montyhall;

import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 *
 * @author rndmorris
 */
public class GameInstance extends TilePane{
    ImageView[] doors;
    String doorClosed = File.separator + "img" + File.separator + "door_closed.png";
    String doorLoser = File.separator + "img" + File.separator + "door_loser";
    String doorWinner = ".." + File.separator + "img" + File.separator + "door_winner";
    public GameInstance(int doorCount) {
        super();
        if(52 < doorCount) throw new java.lang.IllegalArgumentException();
        if(doorCount < 5) {
            this.setPrefColumns(doorCount);
        } else if (doorCount > 20) {
            this.setPrefColumns(13);
        }
        this.setHgap(5);
        this.setVgap(5);
        
        doors = new ImageView[doorCount];
        for(int i = 0; i < doorCount; i++) {
            doors[i] = new ImageView(doorClosed);
            this.getChildren().add(doors[i]);
        }
    }
    public GameInstance() {
        this(3);
    }
}
