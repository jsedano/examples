package dev.jsedano.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UIController {

    @FXML
    private Label myLabel;

    @FXML
    private void buttonWasPressed(){
        myLabel.setText("button was pressed");
    }
}
