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
    Label switchLabel1 = new Label(" number of doors, and change selection ");
    IntegerField percentToSwitch = new IntegerField(100);
    Label switchLabel2 = new Label("% of the time. Use ");
    IntegerField numberOfThreads = new IntegerField(1);
    Button startBtn = new Button("Start >>>");
    
    TextArea aiOutput = new TextArea();
    
    public AiUI() {
        super();
        numOfRuns.setMaxWidth(60);
        numOfDoors.setMaxWidth(60);
        percentToSwitch.setMaxWidth(60);
        numberOfThreads.setMaxWidth(60);
        aiControls.getChildren().addAll(runLabel,numOfRuns,numDoorLabel,numOfDoors,switchLabel1,percentToSwitch,switchLabel2,startBtn);
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
            return Integer.parseInt(super.getText());
        }
    }
    
    
}
