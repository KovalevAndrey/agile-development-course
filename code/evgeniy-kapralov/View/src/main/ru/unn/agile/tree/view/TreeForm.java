package ru.unn.agile.tree.model;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: the_b_000
 * Date: 01.11.13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class TreeForm {
    private JTextField textField1;
    private JPanel mainPanel;
    private JTextField textField2;
    private JButton findButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TreeForm");
        frame.setContentPane(new TreeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
