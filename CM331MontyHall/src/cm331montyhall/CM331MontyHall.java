package cm331montyhall;

import java.io.File;
import java.util.HashSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CM331MontyHall extends Application {
    
    private Label headerRibbon;
    private GridPane gameZone;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        gameZone = new GridPane();
        headerRibbon = new Label();
        headerRibbon.setTextAlignment(TextAlignment.CENTER);
        
        
        root.setTop(headerRibbon);
        root.setCenter(gameZone);
        
        clearGame();
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Lets Make a Deal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void clearGame() {
        headerRibbon.setText("Welcome to \"Let\'s Make a Deal!\"");
        gameZone.setAlignment(Pos.CENTER);
        //gameZone = new Grid
        gameZone.add(new ImageView("img"+java.io.File.separatorChar+"door_closed.png"), 0, 0);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Doors test = new Doors();
    }
    
}
