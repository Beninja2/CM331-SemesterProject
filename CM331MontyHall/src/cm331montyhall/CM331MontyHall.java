package cm331montyhall;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class CM331MontyHall extends Application {
    
    private GameInstance currentGame;
    private Label headerRibbon;
    private GameDisplay gameZone;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        headerRibbon = new Label();
        headerRibbon.setTextAlignment(TextAlignment.CENTER);
        
        VBox controls = new VBox();
        
        ComboBox numberSelection = new ComboBox();
        numberSelection.setValue(3);
        for (int i = 3; i <= 52; i++) {
            numberSelection.getItems().add(i);
        }
        Button startGame = new Button("Start Game");
        numberSelection.resize(startGame.getWidth(), numberSelection.getHeight());
        startGame.setOnAction(e -> {
            currentGame = new GameInstance((int)(numberSelection.getValue()));
            gameZone = new GameDisplay(currentGame);
            root.setCenter(gameZone);
        });
        
        
        controls.getChildren().addAll(new Label("Number of Doors:"),numberSelection,startGame);
        
        root.setLeft(controls);
        root.setCenter(new VBox());
        root.setTop(headerRibbon);
        
        
        
        Scene scene = new Scene(root,200,200);

        primaryStage.setTitle("Lets Make a Deal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
