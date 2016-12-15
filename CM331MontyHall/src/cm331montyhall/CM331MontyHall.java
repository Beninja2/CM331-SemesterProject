package cm331montyhall;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CM331MontyHall extends Application {

    BorderPane root = new BorderPane();
    Label welcomeLabel = new Label("Welcome to \"Let's Make a Deal\"!\nSelect a game-mode below!");
    ControlPanel controlPanel = new ControlPanel();
    
    GameInstance thegame = null;
    
    PlayerUI playerUI = null;
    AiUI aiUI = null;
    
    //Info labels
    final Label MAINMENU = new Label("Select a game-mode below!");
    
    
    @Override
    public void start(Stage mainStage) {
        root.setBottom(controlPanel);
        
        controlPanel.playBtn.setOnAction(e -> play());
        controlPanel.resetBtn.setOnAction(e -> resetUI());
        controlPanel.nextBtn.setOnAction(e -> playNext());
        
        welcomeLabel.setStyle("-fx-text-alignment: center");
        
        resetUI();
        
        
        mainStage.setMaximized(true);
        mainStage.setTitle("Welcome to \"Let's Make a Deal\"!");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }    
    
    public void play() {
        controlsEnabled(false);
        
        playerUI = null;
        if (controlPanel.togGrp.getSelectedToggle() == controlPanel.aiOption) {
            if (aiUI == null) {
                aiUI = new AiUI();
            }
            aiUI.startBtn.setOnAction(e -> aiStart());
            root.setCenter(aiUI);
            
            
        } else {
            
            thegame = new GameInstance((int) controlPanel.doorOptions.getValue());
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
            controlPanel.nextBtn.setDisable(true);
            playerUI.getDoorArray()[thegame.getWinningDoor()].openDoor();
            Alert popup = new Alert(AlertType.INFORMATION);
            popup.initModality(Modality.APPLICATION_MODAL);
            if (thegame.getCurrentSelection() == thegame.getWinningDoor()) {
                popup.setHeaderText("Congratulations! You win!");
            } else {
                popup.setHeaderText("Sorry! You lose!");
            }
            popup.showAndWait();
            controlPanel.nextBtn.setDisable(true);
        }
        else
        {
            int openedDoor = thegame.openEmptyDoor();
            playerUI.getDoorArray()[openedDoor].openDoor();
        }
    }
    private void aiStart() {
        int cycles = -1;
        int percentToSwitch = -1;
        boolean error = false;
        try {
            cycles = Integer.parseInt(aiUI.numOfRuns.getText());
        } catch (NumberFormatException nfe) {
            error = true;
            displayError("The number of times to play must be a number.");
        }
        try {
            percentToSwitch = Integer.parseInt(aiUI.percentToSwitch.getText());
        } catch (NumberFormatException nfe) {
            error = true;
            displayError("The number of times to switch must be a number.");
        }
        
        if (!error && cycles <= 0) {
            error = true;
            displayError("Times to play must be greater than 0.");
        }
        if (!error && (percentToSwitch < 0 || 100 < percentToSwitch)) {
            error = true;
            displayError("Percent to switch must (inclusively) be\nbetween 0 and 100.");
        }
        
        if (!error) {
            //AiUI
        }
        
    }
    
    private void displayError(String message) {
        Alert popup = new Alert(AlertType.ERROR,message);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }
}
