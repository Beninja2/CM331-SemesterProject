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
    TextField numOfRuns = new TextField();
    Label switchLabel1 = new Label(" times and change selection ");
    Label switchLabel2 = new Label("% of the time.");
    TextField percentToSwitch = new TextField();
    Button startBtn = new Button("Start >>>");
    
    TextArea aiOutput = new TextArea();
    
    public AiUI() {
        super();
        numOfRuns.setText("500");
        numOfRuns.setMaxWidth(60);
        percentToSwitch.setText("100");
        percentToSwitch.setMaxWidth(60);
        aiControls.getChildren().addAll(runLabel,numOfRuns,switchLabel1,percentToSwitch,switchLabel2,startBtn);
        this.getChildren().addAll(aiControls,aiOutput);
    }
    
    
    
    
}
