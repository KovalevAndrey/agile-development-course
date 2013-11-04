package ru.unn.agile.MathStatistic.view;

import javax.swing.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField inputDataTextField;
    private JList list1;
    private JButton calcItButton;
    private JTextField textField1;
}
