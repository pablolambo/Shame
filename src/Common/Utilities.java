package Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Utilities {
    public static void addOperatorComboBox(JFrame frame, int x, int y, int width, int height, JComboBox<String> operatorComboBox) {
        operatorComboBox.setBounds(x, y, width, height);
        frame.add(operatorComboBox);
    }

    public static void addTextField(JFrame frame, JTextField textField, int x, int y, int width, int height) {
        textField.setBounds(x, y, width, height);
        frame.add(textField);
    }

    public static JButton addButton(JFrame frame, int x, int y, int width, int height, String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFocusable(false);

        if (actionListener != null) {
            button.addActionListener(actionListener);
        }

        frame.add(button);
        return button;
    }

    public static JFrame SetupFrame(JFrame frame) {
        frame = new JFrame("Shame Calculator");
        frame.setSize(1000, 500);
        frame.getContentPane().setBackground(new Color(Color.lightGray.getRGB()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        return frame;
    }

    public static JProgressBar addShameBar(JFrame frame, JProgressBar shameBar, int x, int y, int width, int height) {
        shameBar = new JProgressBar(0, 100);
        shameBar.setBounds(x, y, width, height);
        shameBar.setString("Shame progress");
        shameBar.setStringPainted(true);
        frame.add(shameBar);
        return shameBar;
    }

    public static void addPredictionTextField(JFrame frame, JTextField predictionField, int x, int y, int width, int height) {
        predictionField.setBounds(x, y, width, height);
        frame.add(predictionField);
        predictionField.setBorder(BorderFactory.createTitledBorder("Make a prediction of a result"));
    }

    public static JLabel addShameImageLabel(JFrame frame, JLabel shameImageLabel){
        shameImageLabel = new JLabel();
        shameImageLabel.setBounds(375, 50, 350, 350);
        frame.add(shameImageLabel);
        return shameImageLabel;
    }

    public static void showMessageDialog(JFrame frame) {
        String randomMessage = Constants.WELCOME_MESSAGES[(int) (Math.random() * Constants.WELCOME_MESSAGES.length)];
        JOptionPane.showMessageDialog(frame, randomMessage, "Welcome", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createMenuBar(JFrame frame, ActionListener helpListener, ActionListener aboutListener) {
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(helpListener);
        helpMenu.add(helpItem);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(aboutListener);
        aboutMenu.add(aboutItem);

        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        frame.setJMenuBar(menuBar);
    }

    public static JTextArea addHistoryPanel(JFrame frame, int x, int y, int width, int height) {
        JTextArea historyPanel = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setBounds(x, y, width, height);
        frame.add(scrollPane);
        return historyPanel;
    }
}
