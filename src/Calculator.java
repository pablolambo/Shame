import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;

    Calculator() 
    {
        SetupFrame();

        // add button
        JButton button = new JButton("Click me");
        button.setBounds(130, 100, 100, 40);
        button.setFocusable(false);
        frame.add(button);

        frame.setVisible(true);
    }

    private void SetupFrame() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame = new JFrame("Calculator component project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);
    }
}

