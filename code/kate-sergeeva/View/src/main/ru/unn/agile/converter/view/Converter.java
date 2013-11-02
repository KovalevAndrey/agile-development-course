package ru.unn.agile.converter.view;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kate
 * Date: 02.11.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class Converter {
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton convertButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
