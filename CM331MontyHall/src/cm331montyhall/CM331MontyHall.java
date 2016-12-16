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
    
    GameAIManager gaim = null;
    
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
            aiUI.stopBtn.setOnAction(e -> aiStop());
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
        int numberOfDoors = -1;
        int threadCount = 1;
        boolean error = false;
        try {
            cycles = aiUI.numOfRuns.getInt();
        } catch (NumberFormatException nfe) {
            error = true;
            displayError("The number of times to play must be a number.");
        }
        try {
            numberOfDoors = aiUI.numOfDoors.getInt();
        } catch (NumberFormatException nfe) {
            displayError("The number of doors must be a number.");
        }
        try {
            percentToSwitch = aiUI.percentToSwitch.getInt();
        } catch (NumberFormatException nfe) {
            error = true;
            displayError("The number of times to switch must be a number.");
        }
        try {
            threadCount = aiUI.numberOfThreads.getInt();
        } catch (NumberFormatException nfe) {
            error = true;
            displayError("The number of threads must be a number.");
        }
        
        if (!error && cycles <= 0) {
            error = true;
            displayError("Times to play must be greater than 0.");
        }
        if (!error && numberOfDoors < 3) {
            error = true;
            displayError("Number of doors must be at least 3.");
        }
        if (!error && (percentToSwitch < 0 || 100 < percentToSwitch)) {
            error = true;
            displayError("Percent to switch must (inclusively) be\nbetween 0 and 100.");
        }
        if (!error && threadCount < 1) {
            error = true;
            displayError("Thread count must be at least 1.");
        }
        
        if (!error) {
            gaim = new GameAIManager(cycles,percentToSwitch,numberOfDoors,threadCount);
            gaim.setOutputArea(aiUI.aiOutput);
            gaim.setStartBtn(aiUI.startBtn);
            gaim.setStopBtn(aiUI.stopBtn);
            gaim.start();
        }
        
    }
    private void aiStop() {
        gaim.halt();
        aiUI.aiOutput.appendText("Execution halted prematurely!\n");
    }
    private void displayError(String message) {
        Alert popup = new Alert(AlertType.ERROR,message);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }
}
