/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author rndmorris
 */
public class AIface extends VBox{
    
    HBox aiControls = new HBox();
    TextField numOfRuns = new TextField();
    CheckBox chooseSecond = new CheckBox("Always change selection");
    
    TextArea aiOutput = new TextArea();
    
    public AIface() {
        super();
        chooseSecond.setSelected(true);
        aiControls.getChildren().addAll(numOfRuns,chooseSecond);
        this.getChildren().addAll(aiControls,aiOutput);
    }
    
    
    
    
}
