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
    private final ImageIcon shameIcon = new ImageIcon("D:\\Studies\\5sem\\Shame\\Images\\shame.png");
    private final ImageIcon goodJobIcon = new ImageIcon("D:\\Studies\\5sem\\Shame\\Images\\goodjob.png");
    private JLabel shameImageLabel = new JLabel();
    private JLabel goodJobLabel = new JLabel();
    private int consecutiveCorrectPredictions = 0;


    Calculator() {
        frame = Utilities.SetupFrame(frame);

        Utilities.addTextField(frame, firstInputField,50, 20, 50, 30);
        Utilities.addOperatorComboBox(frame, 115, 20, 50, 30, operatorComboBox);
        Utilities.addTextField(frame, secondInputField,175, 20, 50, 30);
        Utilities.addButton(frame, 235, 20, 50, 30, "=", this::calculate);
        Utilities.addTextField(frame, resultField, 295, 20, 50, 30);

        shameBar = Utilities.addShameBar(frame, shameBar,50, 80, 300, 20);
        Utilities.addPredictionTextField(frame, predictionField, 50, 110, 180, 50);

        shameImageLabel = Utilities.addLabel(frame, shameImageLabel);

        frame.setVisible(true);
    }

    private void calculate(ActionEvent e) {
        try
        {
            double num1 = validateInput(firstInputField.getText(), false);
            double num2 = validateInput(secondInputField.getText(), false);
            String operator = (String)operatorComboBox.getSelectedItem();
            double result = performCalculation(num1, num2, operator);

            resultField.setText(formatResult(result));

            double userPrediction = validateInput(predictionField.getText(), true);

            if (userPrediction == Constants.DEFAULT_PREDICTION)
            {
                 consecutiveCorrectPredictions = 0;
            }

            if (userPrediction != result && userPrediction != Constants.DEFAULT_PREDICTION)
            {
                increaseShameBar();
                consecutiveCorrectPredictions = 0;
            }

            if (userPrediction == result)
            {
                consecutiveCorrectPredictions++;
                if (consecutiveCorrectPredictions == Constants.STREAK_THRESHOLD) {
                    displayGoodJobImage();
                    consecutiveCorrectPredictions = 0;
                }
            }

            if(shameBar.getValue() == 100){
                showShameImage();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double performCalculation(double num1, double num2, String operator) {
        switch (operator) {
            case Constants.ADD:
                return num1 + num2;
            case Constants.SUBTRACT:
                return num1 - num2;
            case Constants.MULTIPLY:
                return num1 * num2;
            case Constants.DIVIDE:
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private String formatResult(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf(result);
        }
    }

    private double validateInput(String input, boolean isPrediction) {
        if(isPrediction && input.isEmpty())
            return Constants.DEFAULT_PREDICTION;
        if (input.isEmpty()) {
            throw new NumberFormatException("Input is empty.");
        }
        input = input.replace(',', '.');
        return Double.parseDouble(input);
    }

    private void increaseShameBar() {
        int currentValue = shameBar.getValue();
        if (currentValue <= 75) {
            shameBar.setValue(currentValue + Constants.SHAME_PROGRESS_VALUE);
            shameBar.setString(currentValue + 25 + "%");
        } else if (currentValue == 100) {
            showShameImage();
        }
//        else {
//            shameBar.setValue(0);
//            shameBar.setString("Shame progress");
//        }
    }

    private void showShameImage() {
        shameImageLabel.setIcon(shameIcon);
        int option = JOptionPane.showOptionDialog(frame, shameImageLabel, "Shame on you!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"OK"}, "OK");

        if (option == JOptionPane.OK_OPTION) {
            resetShameBar();
        }
    }

    private void resetShameBar() {
        shameBar.setValue(0);
        shameBar.setString("Shame progress");
        shameImageLabel.setIcon(null);
    }

    private void displayGoodJobImage() {
        goodJobLabel.setIcon(goodJobIcon);
        JOptionPane.showMessageDialog(frame, goodJobLabel, "Good Job! 3 correct answers in a row!", JOptionPane.PLAIN_MESSAGE);
        resetShameBar();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
