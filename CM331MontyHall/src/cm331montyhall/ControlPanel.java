/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 *
 * @author rndmorris
 */
public class ControlPanel extends HBox{
    
    Button playBtn = new Button("Play");
    Button resetBtn = new Button("Reset");
    Button nextBtn = new Button("Next >>>");
    ToggleGroup togGrp = new ToggleGroup();
    RadioButton aiOption = new RadioButton("Play for me");
    RadioButton uiOption = new RadioButton("Let me play");
    ComboBox doorOptions = new ComboBox();
    
    public ControlPanel() {
        super();
        togGrp.getToggles().addAll(aiOption,uiOption);
        togGrp.selectToggle(uiOption);
        for (int i = 3; i <= 48; i++) {
            doorOptions.getItems().add(i);
        }
        doorOptions.setValue(3);
        this.getChildren().addAll(aiOption,uiOption,doorOptions,resetBtn,playBtn,nextBtn);
    }
    
}
