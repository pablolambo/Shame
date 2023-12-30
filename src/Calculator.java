import Common.Constants;
import Common.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    private JFrame frame;
    private final JTextField firstInputField = new JTextField();
    private final JTextField secondInputField = new JTextField();
    private final JTextField resultField = new JTextField();
    private final JTextField predictionField = new JTextField();
    private JProgressBar shameBar;
    private final JComboBox<String> operatorComboBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
    private JLabel proximityLabel = new JLabel();
    private String lastShownMemePath;
    private final ImageIcon shameIcon = new ImageIcon(Constants.PROJECT_DIRECTORY + "/Images/shame.png");
    private final ImageIcon goodJobIcon = new ImageIcon(Constants.PROJECT_DIRECTORY + "/Images/goodjob.png");
    private JLabel shameImageLabel = new JLabel();
    private JLabel goodJobLabel = new JLabel();
    private int consecutiveCorrectPredictions = 0;

    Calculator() {
        frame = Utilities.SetupFrame(frame);
        Utilities.showMessageDialog(frame);

        Utilities.createMenuBar(frame, this::showHelp, this::showAbout);

        Utilities.addTextField(frame, firstInputField,50, 20, 50, 30);
        Utilities.addOperatorComboBox(frame, 115, 20, 50, 30, operatorComboBox);
        Utilities.addTextField(frame, secondInputField,175, 20, 50, 30);
        Utilities.addButton(frame, 235, 20, 50, 30, "=", this::calculate);
        Utilities.addTextField(frame, resultField, 295, 20, 50, 30);
        resultField.setEditable(false);
        Utilities.addButton(frame, 50, 180, 300, 30, "Show me a meme", this::showMeme);


        shameBar = Utilities.addShameBar(frame, shameBar,50, 80, 300, 20);
        Utilities.addPredictionTextField(frame, predictionField, 50, 110, 180, 50);

        proximityLabel.setBounds(250, 110, 150, 50);
        frame.add(proximityLabel);

        shameImageLabel = Utilities.addShameImageLabel(frame, shameImageLabel);

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

            double proximity;
            if(userPrediction == Constants.DEFAULT_PREDICTION)
            {
                proximity = 0;
            }else
            {
                proximity = Math.abs(userPrediction - result);
            }

            if (proximity != 0) {
                proximity = Math.round(proximity * 100.0) / 100.0;
                proximityLabel.setText("Proximity: " + proximity);
            } else {
                proximityLabel.setText("");
            }

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
                predictionField.setBackground(Color.GREEN);
            }else if(userPrediction != result && userPrediction != Constants.DEFAULT_PREDICTION){
                predictionField.setBackground(Color.RED);
                consecutiveCorrectPredictions = 0;
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

    private void showMeme(ActionEvent e) {
        List<String> memePaths = Arrays.asList(Constants.MEME_PATHS);

        if (memePaths.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No memes available.", "No Memes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String randomMemePath = getRandomMeme(memePaths);
        lastShownMemePath = randomMemePath;

        ImageIcon memeIcon = new ImageIcon(randomMemePath);
        JOptionPane.showMessageDialog(frame, new JLabel(memeIcon), "Meme Time!", JOptionPane.PLAIN_MESSAGE);
    }

    private String getRandomMeme(List<String> memePaths) {
        Random random = new Random();
        String randomMemePath;

        do {
            randomMemePath = memePaths.get(random.nextInt(memePaths.size()));
        } while (randomMemePath.equals(lastShownMemePath));

        return randomMemePath;
    }

    private void showHelp(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, Constants.HELP_MESSAGE, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAbout(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, Constants.ABOUT_MESSAGE, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
