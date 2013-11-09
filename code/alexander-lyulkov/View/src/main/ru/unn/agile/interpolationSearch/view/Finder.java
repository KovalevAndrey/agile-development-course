package ru.unn.agile.interpolationSearch.view;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 11/8/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Finder {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Finder");
        frame.setContentPane(new Finder().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JButton searchButton;
    private JTextField textField4;
}
