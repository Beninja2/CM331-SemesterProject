/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author rndmorris
 */
public class AiUI extends VBox{
    
    HBox aiControls = new HBox();
    Label runLabel = new Label("Play ");
    IntegerField numOfRuns = new IntegerField(500);
    Label numDoorLabel = new Label(" times with ");
    IntegerField numOfDoors = new IntegerField(3);
    Label switchLabel1 = new Label(" doors, and change selection ");
    IntegerField percentToSwitch = new IntegerField(100);
    Label switchLabel2 = new Label("% of the time. Use ");
    IntegerField numberOfThreads = new IntegerField(1);
    Label threadLabel = new Label(" thread(s). ");
    Button startBtn = new Button("Start >>>");
    Button stopBtn = new Button("STOP");
    
    TextArea aiOutput = new TextArea();
    
    public AiUI() {
        super();
        aiOutput.setEditable(false);
        stopBtn.setDisable(true);
        aiControls.getChildren()
                .addAll(runLabel,
                        numOfRuns,
                        numDoorLabel,
                        numOfDoors,
                        switchLabel1,
                        percentToSwitch,
                        switchLabel2,
                        numberOfThreads,
                        threadLabel,
                        startBtn,
                        stopBtn);
        this.getChildren().addAll(aiControls,aiOutput);
    }
    
    class IntegerField extends TextField {
        String fieldName = "IntegerField";
        
        public IntegerField (int initValue) {
            super(Integer.toString(initValue));
        }
        public IntegerField (int initValue, String fieldName) {
            this(initValue);
            this.fieldName = fieldName;
        }
        
        public int getInt() throws NumberFormatException{
            if (super.getText().trim().isEmpty()) {
                throw new NumberFormatException();
            }
            return Integer.parseInt(super.getText());
        }
    }
    
    
}
