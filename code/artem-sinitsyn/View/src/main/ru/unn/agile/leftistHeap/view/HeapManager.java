package ru.unn.agile.leftistHeap.view;

import javax.swing.*;

public class HeapManager {
    private JPanel mainPanel;
    private JTextField addKeyTextField;
    private JTextField addValueTextField;
    private JButton addButton;
    private JButton getButton;
    private JButton deleteButton;
    private JLabel statusLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("HeapManager");
        frame.setContentPane(new HeapManager().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
