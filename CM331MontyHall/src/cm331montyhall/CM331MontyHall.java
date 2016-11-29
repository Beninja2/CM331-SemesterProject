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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CM331MontyHall extends Application {
    
    private Label headerRibbon;
    private GameInstance gameZone;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        gameZone = new GameInstance(1);
        headerRibbon = new Label();
        headerRibbon.setTextAlignment(TextAlignment.CENTER);
        
        
        root.setTop(headerRibbon);
        root.setCenter(gameZone);
        
        
        Scene scene = new Scene(root);//,200,200);

        primaryStage.setTitle("Lets Make a Deal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Doors test = new Doors();
    }   
}