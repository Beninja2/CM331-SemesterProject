package cm331montyhall;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class CM331MontyHall extends Application {

    @Override
    public void start(Stage mainStage) {
        BorderPane root = new BorderPane();
        
        Label header = new Label("Welcome to \"Let's Make a Deal\"!");
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-font-weight : bold;");
        root.setTop(header);
        
        GameInstance instance = new GameInstance(5);
        
        DoorHolder holder = new DoorHolder(instance);
        
        root.setCenter(holder);
        
        
        Scene rootScene = new Scene(root);//,500,500);
        mainStage.setScene(rootScene);
        mainStage.show();
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
