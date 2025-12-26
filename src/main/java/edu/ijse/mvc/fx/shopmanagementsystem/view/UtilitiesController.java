package edu.ijse.mvc.fx.shopmanagementsystem.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UtilitiesController {

    @FXML
    private Button calculateBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private ComboBox<?> fromCombo;

    @FXML
    private AnchorPane fromTxt;

    @FXML
    private Label lblValue;

    @FXML
    private ComboBox<?> toCombo;

    @FXML
    private TextField txtDisplay;

    @FXML
    private TextField valueTxt;

    @FXML
    void clear(ActionEvent event) {
        valueTxt.setText("");
    }

    @FXML
    void handleBackspace(ActionEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDecimal(ActionEvent event) {

    }

    @FXML
    void handleEquals(ActionEvent event) {

    }

    @FXML
    void handleNumber(ActionEvent event) {

    }

    @FXML
    void handleOperator(ActionEvent event) {

    }

    @FXML
    void valueCalculate(ActionEvent event) {

    }

}
