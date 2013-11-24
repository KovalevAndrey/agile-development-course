package ru.unn.agile.deque.view;

import javax.swing.*;

public class DequeForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Deque");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField maximumSizeOfDequeTextField;
    private JButton createDequeButton;
    private JComboBox actionsComboBox;
    private JTextField pushTextField;
    private JButton actButton;
    private JTextArea dequeTextArea;
    private JLabel statusLabel;
}
