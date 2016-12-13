package cm331montyhall;

import javafx.application.Application;
import javafx.geometry.Pos;
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

    BorderPane root;
    Label welcomeLabel = new Label("Welcome to \"Let's Make a Deal\"!");
    ControlPanel controlPanel;
    GameInstance thegame = null;
    PlayerUI playerUI;
    
    
    @Override
    public void start(Stage mainStage) {
        root = new BorderPane();
        controlPanel = new ControlPanel();
        root.setBottom(controlPanel);
        
        controlPanel.playBtn.setOnAction(e -> play());
        controlPanel.resetBtn.setOnAction(e -> resetUI());
        controlPanel.nextBtn.setOnAction(e -> playNext());
        
        resetUI();
        
        
        
        
        Scene rootScene = new Scene(root,570,500);//,500,500);
        mainStage.setTitle("Welcome to \"Let's Make a Deal\"!");
        mainStage.setScene(rootScene);
        mainStage.show();
    }    
    
    public void play() {
        controlsEnabled(false);
        thegame = new GameInstance((int) controlPanel.doorOptions.getValue());
        playerUI = null;
        if (controlPanel.togGrp.getSelectedToggle() == controlPanel.aiOption) {
            
            
            
        } else {
            playerUI = new PlayerUI(thegame);
            playerUI.getDoorHandler().setNextBtn(controlPanel.nextBtn);
            
            root.setCenter(playerUI);
        }
        
    }
    
    public void resetUI() {
        controlsEnabled(true);
        controlPanel.nextBtn.setDisable(true);
        root.setCenter(welcomeLabel);
    }
    
    public void controlsEnabled(boolean active) {
        controlPanel.aiOption.setDisable(!active);
        controlPanel.doorOptions.setDisable(!active);
        controlPanel.playBtn.setDisable(!active);
        controlPanel.uiOption.setDisable(!active);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void playNext() {
        if(thegame.getOpenedEmptyDoor())
        {
            
        }
        else
        {
            int openedDoor = thegame.openEmptyDoor();
            playerUI.getDoorArray()[openedDoor].openDoor();
        }
    }
    
}
