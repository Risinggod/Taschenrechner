package de.sbs.fswi2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;
    
    private double previousValue = 0.0;
    private String operator = "";
    private boolean startNewInput = true;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        
        if (startNewInput) {
            display.clear();
            startNewInput = false;
        }

        if (buttonText.matches("[0-9]")) {
            display.appendText(buttonText);
        } else if (buttonText.equals(".")) {
            if (!display.getText().contains(".")) {
                display.appendText(buttonText);
            }
        } else if (isOperator(buttonText)) {
            operator = buttonText;
            previousValue = Double.parseDouble(display.getText());
            startNewInput = true;
        } else if (buttonText.equals("=")) {
            double currentValue = Double.parseDouble(display.getText());
            double result = calculateResult(previousValue, currentValue, operator);
            display.setText(String.valueOf(result));
            startNewInput = true;
        } else if (buttonText.equals("C")) {
            display.clear();
            previousValue = 0.0;
            operator = "";
            startNewInput = true;
        }
    }

    private boolean isOperator(String text) {
        return text.matches("[-+*/]");
    }

    private double calculateResult(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    return 0; // Handle division by zero
                }
            default:
                return b;
        }
    }
}