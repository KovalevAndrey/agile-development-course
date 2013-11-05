package ru.unn.agile.Polynomial.view;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 * Date: 11/5/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {
    private JPanel CalculatorPanel;
    private JTextField polynomial2TextField;
    private JTextField polynomial1TextField;
    private JTextField resultTextField;
    private JComboBox operationComboBox;
    private JButton CalculateButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().CalculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
