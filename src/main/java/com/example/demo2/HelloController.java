package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HelloController {

        @FXML
        private Text resultat;

        @FXML
        private Button negation;


        private String operand = "";
        private String operator = "";
        private double tempValue = 0.0; // Nouvelle variable pour stocker les chiffres précédents
        private boolean isNewCalculation = true;

        @FXML
        private void handleNumberClick(ActionEvent event) {
                if (isNewCalculation) {
                        resultat.setText("");
                        isNewCalculation = false;
                }

                Button clickedButton = (Button) event.getSource();
                String buttonText = clickedButton.getText();
                operand += buttonText;
                resultat.setText(operand);
        }

        @FXML
        private void handleOperatorClick(ActionEvent event) {
                if (!operand.isEmpty()) {
                        tempValue = Double.parseDouble(operand); // Stocker les chiffres précédents
                        isNewCalculation = true;
                        Button clickedButton = (Button) event.getSource();
                        operator = clickedButton.getText();
                        operand = "";
                }
        }

        @FXML
        private void handleEqualsClick() {
                if (!operand.isEmpty() && !operator.isEmpty()) {
                        double num1 = tempValue; // Utiliser les chiffres précédents
                        double num2 = Double.parseDouble(operand);
                        double result = calculateResult(num1, num2, operator);
                        resultat.setText(String.valueOf(result));
                        tempValue = result; // Mettre à jour tempValue pour les futures opérations
                        operand = "";
                        operator = "";
                        isNewCalculation = true;
                }
        }

        @FXML
        private void handleClearClick() {
                operand = "";
                operator = "";
                resultat.setText("");
        }

        @FXML
        private void handleNegationClick(ActionEvent event) {
                if (!operand.isEmpty()) {
                        double num = Double.parseDouble(operand);
                        num = -num; // Inverser le signe de l'opérande
                        operand = String.valueOf(num);
                        resultat.setText(operand);
                } else if (!resultat.getText().isEmpty()) {
                        // Inverser le signe du résultat
                        double num = Double.parseDouble(resultat.getText());
                        num = -num;
                        operand = String.valueOf(num);
                        resultat.setText(operand);
                }
        }


        private double calculateResult(double num1, double num2, String operator) {
                switch (operator) {
                        case "+":
                                return num1 + num2;
                        case "-":
                                return num1 - num2;
                        case "x":
                                return num1 * num2;
                        case "/":
                                if (num2 != 0) {
                                        return num1 / num2;
                                } else {
                                        return Double.NaN; // Gérer la division par zéro
                                }
                        default:
                                return num2;
                }
        }
}





