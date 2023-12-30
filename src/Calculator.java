import Common.Constants;
import Common.Utilities;

import javax.swing.*;
import javax.swing.text.BadLocationException;
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
    private final String[] operators = {"+", "-", "*", "/"};
    private final JComboBox<String> operatorComboBox = new JComboBox<>(operators);
    private final Random random = new Random();
    private JLabel proximityLabel = new JLabel();
    private String lastShownMemePath;
    private final ImageIcon shameIcon = new ImageIcon(Constants.PROJECT_DIRECTORY + "/Images/shame.png");
    private final ImageIcon goodJobIcon = new ImageIcon(Constants.PROJECT_DIRECTORY + "/Images/goodjob.png");
    private JLabel shameImageLabel = new JLabel();
    private JLabel goodJobLabel = new JLabel();
    private JTextArea historyPanel;
    private int consecutiveCorrectPredictions = 0;
    private JLabel correctPredictionsLabel = new JLabel();
    private JLabel wrongPredictionsLabel = new JLabel();
    private JProgressBar predictionPercentageBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    private int correctPredictionsCounter = 0;
    private int wrongPredictionsCounter = 0;


    Calculator() {
        frame = Utilities.SetupFrame(frame);
        
        setupBasicFunctionalitiesAndComponents();

        setupShameBar();
        setupRandomCalculationGeneratorButton();
        setupProximityLabel();
        setupHistoryPanel();
        setupPredictionLabels();

        shameImageLabel = Utilities.addShameImageLabel(frame, shameImageLabel);

        frame.setVisible(true);
    }

    private void setupBasicFunctionalitiesAndComponents() {
        Utilities.showMessageDialog(frame);

        Utilities.createMenuBar(frame, this::showHelp, this::showAbout);

        Utilities.addTextField(frame, firstInputField,50, 20, 50, 30);
        Utilities.addOperatorComboBox(frame, 115, 20, 50, 30, operatorComboBox);
        Utilities.addTextField(frame, secondInputField,175, 20, 50, 30);
        Utilities.addButton(frame, 235, 20, 50, 30, "=", this::calculate);
        Utilities.addTextField(frame, resultField, 295, 20, 50, 30);
        resultField.setEditable(false);
        Utilities.addButton(frame, 50, 180, 300, 30, "Show me a meme", this::showMeme);
    }

    private void setupShameBar() {
        shameBar = Utilities.addShameBar(frame, shameBar,50, 80, 300, 20);
        Utilities.addPredictionTextField(frame, predictionField, 50, 110, 180, 50);
    }

    private void setupRandomCalculationGeneratorButton() {
        Utilities.addButton(frame, 15, 20, 30, 30, "", this::generateRandomCalculation)
                .setIcon(new ImageIcon(Constants.PROJECT_DIRECTORY + "/Images/dice.png"));
    }

    private void setupProximityLabel() {
        proximityLabel.setBounds(250, 110, 150, 50);
        frame.add(proximityLabel);
    }

    private void setupHistoryPanel() {
        historyPanel = Utilities.addHistoryPanel(frame, 360, 20, 275, 185);
        historyPanel.setEditable(false);
    }

    private void setupPredictionLabels() {
        correctPredictionsLabel.setBounds(50, 230, 150, 20);
        frame.add(correctPredictionsLabel);
        wrongPredictionsLabel.setBounds(400, 230, 150, 20);
        frame.add(wrongPredictionsLabel);
        predictionPercentageBar.setBounds(185, 230, 200, 20);
        frame.add(predictionPercentageBar);
        predictionPercentageBar.setString("Prediction Percentage");
        correctPredictionsLabel.setText(Constants.CORRECT_PREDICTIONS_LABEL + correctPredictionsCounter);
        wrongPredictionsLabel.setText(Constants.WRONG_PREDICTIONS_LABEL + wrongPredictionsCounter);
        predictionPercentageBar.setStringPainted(true);
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
                predictionField.setBackground(Color.WHITE);
                consecutiveCorrectPredictions = 0;
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

            double roundedResult = Double.parseDouble(String.format("%.2f", result));
            double absoluteDifference = Math.abs(userPrediction - roundedResult);
            boolean isPredictionAlmostCorrect = false;

            if (absoluteDifference <= 5) {
                isPredictionAlmostCorrect = true;
                predictionField.setBackground(Color.YELLOW);
            }
            else {
                isPredictionAlmostCorrect = false;
                if (userPrediction == roundedResult) {
                    correctPredictionsCounter++;
                    correctPredictionsLabel.setText(Constants.CORRECT_PREDICTIONS_LABEL + correctPredictionsCounter);
                    consecutiveCorrectPredictions++;
                    if (consecutiveCorrectPredictions == Constants.STREAK_THRESHOLD) {
                        displayGoodJobImage();
                        consecutiveCorrectPredictions = 0;
                    }
                    predictionField.setBackground(Color.GREEN);
                } else if (userPrediction != result && userPrediction != Constants.DEFAULT_PREDICTION) {
                    predictionField.setBackground(Color.RED);
                    consecutiveCorrectPredictions = 0;
                    wrongPredictionsCounter++;
                    wrongPredictionsLabel.setText(Constants.WRONG_PREDICTIONS_LABEL + wrongPredictionsCounter);
                }
            }

            if (userPrediction != result && userPrediction != Constants.DEFAULT_PREDICTION && !isPredictionAlmostCorrect)
            {
                increaseShameBar();
                consecutiveCorrectPredictions = 0;
            }

            updateHistoryPanel(num1, num2, operator, result, userPrediction);
            updateCounters();


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
            return String.format("%.2f", result);
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

    private void updateHistoryPanel(double num1, double num2, String operator, double result, double userPrediction) {
        StringBuilder historyEntry = new StringBuilder();
        historyEntry.append(num1)
                .append(" ")
                .append(operator)
                .append(" ")
                .append(num2)
                .append(" = ")
                .append(result);

        if (userPrediction != Constants.DEFAULT_PREDICTION) {
            historyEntry.append(" | Prediction: ").append(userPrediction);
        }

        historyPanel.append(historyEntry + "\n");

        int lineCount = historyPanel.getLineCount();
        if (lineCount > 10) {
            try {
                int start = historyPanel.getLineStartOffset(lineCount - 10);
                int end = historyPanel.getLineEndOffset(lineCount - 1);
                historyPanel.replaceRange("", start, end);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void updateCounters() {
        correctPredictionsLabel.setText(Constants.CORRECT_PREDICTIONS_LABEL + correctPredictionsCounter);
        wrongPredictionsLabel.setText(Constants.WRONG_PREDICTIONS_LABEL + wrongPredictionsCounter);

        int totalPredictions = correctPredictionsCounter + wrongPredictionsCounter;
        int percentage = totalPredictions > 0 ?
                (correctPredictionsCounter * 100) / totalPredictions : 0;

        predictionPercentageBar.setValue(percentage);
        predictionPercentageBar.setString(percentage + "%");

        predictionPercentageBar.setStringPainted(true);
    }

    private void generateRandomCalculation(ActionEvent e) {
        firstInputField.setText(String.valueOf(random.nextInt(100) + 1));
        secondInputField.setText(String.valueOf(random.nextInt(100) + 1));
        String randomOperator = operators[random.nextInt(operators.length)];
        operatorComboBox.setSelectedItem(randomOperator);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
