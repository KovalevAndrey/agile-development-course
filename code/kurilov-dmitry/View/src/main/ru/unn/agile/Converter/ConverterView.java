package ru.unn.agile.Converter;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Red_Shuhov
 * Date: 05.11.13
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class ConverterView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ConverterView");
        frame.setContentPane(new ConverterView().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel MainPanel;
    private JComboBox fromComboBox;
    private JComboBox toComboBox;
    private JButton calculateButton;
    private JTextField inputTextField;
    private JLabel statusLabel;
    private JTextField resultTextField;
}
