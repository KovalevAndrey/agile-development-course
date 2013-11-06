package ru.unn.agile.BitArray.view;

import javax.swing.*;

public class MainForm {
    private JPanel mainPanel;
    private JTextArea curArrayTxtArea;
    private JTabbedPane tabbedPane1;
    private JTextField strInputTxt;
    private JButton strInputBtn;
    private JTextField intArrayInputTxt;
    private JButton intArrayInputBtn;
    private JTextField indForSetTxt;
    private JButton setValZeroBtn;
    private JButton setValOneBtn;
    private JButton notBtn;
    private JButton lsthBtn;
    private JButton rshtBtn;
    private JTextField startIndOutTxt;
    private JTextField countOutTxt;
    private JButton wholeOutBtn;
    private JButton outToStrBtn;
    private JButton outToIntsBtn;
    private JTextArea outArrayTxtArea;
    private JLabel curLenLbl;
    public static void main(String[] args) {
        JFrame frame = new JFrame("BitArray demo");

        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bindMainInfo() {

    }

    private void backBindMainInfo() {

    }

    private void bindOutInfo() {

    }

    private void backBindOutInfo() {

    }
}
