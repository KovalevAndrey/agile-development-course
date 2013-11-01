package ru.unn.agile.queue;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 01.11.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class QueueForm {
    private JButton pushButton;
    private JTextField textField1;
    private JButton popButton;
    private JTextField a0TextField;
    private JButton cleanButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("QueueForm");
        frame.setContentPane(new QueueForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
