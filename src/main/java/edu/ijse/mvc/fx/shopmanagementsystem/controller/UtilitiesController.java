package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UtilitiesController {

    @FXML
    private TextField txtDisplay;

    private double firstValue = 0;
    private String operator = "";
    private boolean startNew = true;

    @FXML
    private ComboBox<String> fromCombo;

    @FXML
    private ComboBox<String> toCombo;

    @FXML
    private TextField valueTxt;

    @FXML
    private Label lblValue;

    @FXML
    public void initialize() {
        txtDisplay.setText("0");
        txtDisplay.setEditable(false);

        fromCombo.setItems(FXCollections.observableArrayList(
                "LKR", "USD", "EUR", "GBP", "INR"
        ));
        toCombo.setItems(FXCollections.observableArrayList(
                "LKR", "USD", "EUR", "GBP", "INR"
        ));

        fromCombo.setValue("LKR");
        toCombo.setValue("USD");
        lblValue.setText("0.00");
    }

    @FXML
    void handleNumber(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (startNew) {
            txtDisplay.setText(value);
            startNew = false;
        } else {
            txtDisplay.setText(
                    txtDisplay.getText().equals("0")
                            ? value
                            : txtDisplay.getText() + value
            );
        }
    }

    @FXML
    void handleDecimal(ActionEvent event) {
        if (!txtDisplay.getText().contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + ".");
            startNew = false;
        }
    }

    @FXML
    void handleOperator(ActionEvent event) {
        String op = ((Button) event.getSource()).getText();
        firstValue = Double.parseDouble(txtDisplay.getText());
        operator = normalizeOperator(op);
        startNew = true;
    }

    @FXML
    void handleEquals(ActionEvent event) {
        double secondValue = Double.parseDouble(txtDisplay.getText());
        double result;

        switch (operator) {
            case "+" -> result = firstValue + secondValue;
            case "-" -> result = firstValue - secondValue;
            case "*" -> result = firstValue * secondValue;
            case "/" -> result = secondValue == 0 ? 0 : firstValue / secondValue;
            default -> result = secondValue;
        }

        txtDisplay.setText(removeDecimal(result));
        startNew = true;
        operator = "";
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtDisplay.setText("0");
        firstValue = 0;
        operator = "";
        startNew = true;
    }

    @FXML
    void handleBackspace(ActionEvent event) {
        String text = txtDisplay.getText();

        if (text.length() > 1) {
            txtDisplay.setText(text.substring(0, text.length() - 1));
        } else {
            txtDisplay.setText("0");
            startNew = true;
        }
    }

    @FXML
    void valueCalculate(ActionEvent event) {
        try {
            double amount = Double.parseDouble(valueTxt.getText().trim());
            double result = convertCurrency(
                    fromCombo.getValue(),
                    toCombo.getValue(),
                    amount
            );
            lblValue.setText(String.format("%.2f", result));
        } catch (Exception e) {
            lblValue.setText("Error");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        valueTxt.clear();
        lblValue.setText("0.00");
    }

    private double convertCurrency(String from, String to, double amount) {
        double fromRate = getRate(from);
        double toRate = getRate(to);
        double baseUSD = amount / fromRate;
        return baseUSD * toRate;
    }

    private double getRate(String currency) {
        return switch (currency) {
            case "USD" -> 1.0;
            case "LKR" -> 305.00;
            case "EUR" -> 0.92;
            case "GBP" -> 0.79;
            case "INR" -> 83.00;
            default -> 1.0;
        };
    }

    private String normalizeOperator(String op) {
        return switch (op) {
            case "÷" -> "/";
            case "×" -> "*";
            case "−" -> "-";
            default -> op;
        };
    }

    private String removeDecimal(double value) {
        return value % 1 == 0
                ? String.valueOf((int) value)
                : String.valueOf(value);
    }
}
