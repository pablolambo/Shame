import Common.Constants;
import Common.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Calculator {
    private JFrame frame;
    private final JTextField firstInputField = new JTextField();
    private final JTextField secondInputField = new JTextField();
    private final JTextField resultField = new JTextField();
    private final JTextField predictionField = new JTextField();
    private JProgressBar shameBar;
    private final JComboBox<String> operatorComboBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
    Calculator() {
        frame = Utilities.SetupFrame(frame);

        Utilities.addTextField(frame, firstInputField,50, 20, 50, 30);
        Utilities.addOperatorComboBox(frame, 115, 20, 50, 30, operatorComboBox);
        Utilities.addTextField(frame, secondInputField,175, 20, 50, 30);
        Utilities.addButton(frame, 235, 20, 50, 30, "=", this::calculate);
        Utilities.addTextField(frame, resultField, 295, 20, 50, 30);

        shameBar = Utilities.addShameBar(frame, shameBar,50, 80, 300, 20);
        Utilities.addPredictionTextField(frame, predictionField, 50, 110, 100, 30);


        frame.setVisible(true);
    }

    private void calculate(ActionEvent e) {
        try {
            double num1 = validateInput(firstInputField.getText());
            double num2 = validateInput(secondInputField.getText());
            String operator = (String) operatorComboBox.getSelectedItem();
            double result = 0;

            switch (operator) {
                case Constants.ADD:
                    result = num1 + num2;
                    break;
                case Constants.SUBTRACT:
                    result = num1 - num2;
                    break;
                case Constants.MULTIPLY:
                    result = num1 * num2;
                    break;
                case Constants.DIVIDE:
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        throw new ArithmeticException("Cannot divide by zero.");
                    }
                    break;
            }

            resultField.setText(formatResult(result));

            double userPrediction = validateInput(predictionField.getText());
            if (userPrediction != result) {
                increaseShameBar();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatResult(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf(result);
        }
    }

    private double validateInput(String input) {
        if (input.isEmpty()) {
            throw new NumberFormatException("Input is empty.");
        }
        input = input.replace(',', '.');
        return Double.parseDouble(input);
    }

    private void increaseShameBar() {
        int currentValue = shameBar.getValue();
        if (currentValue < 100) {
            shameBar.setValue(currentValue + 10);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
