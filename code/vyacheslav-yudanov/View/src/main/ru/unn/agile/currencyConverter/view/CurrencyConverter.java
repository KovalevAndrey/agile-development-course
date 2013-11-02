package ru.unn.agile.currencyConverter.view;

import javax.swing.*;

public class CurrencyConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CurrencyConverter");

        frame.setContentPane(new CurrencyConverter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField txtFromCurrencyAmount;
    private JButton convertButton;
    private JComboBox cmbFromCurrency;
    private JComboBox cmbToCurrency;
    private JTextField txtConvertResult;
}
