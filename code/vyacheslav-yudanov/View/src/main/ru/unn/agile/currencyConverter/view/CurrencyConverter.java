package ru.unn.agile.currencyConverter.view;

import ru.unn.agile.currencyConverter.viewmodel.CurrencyConverterViewModel;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CurrencyConverter");

        frame.setContentPane(new CurrencyConverter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private CurrencyConverterViewModel viewModel;

    public CurrencyConverter(){
        viewModel = new CurrencyConverterViewModel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                viewModel.convertBtnClick();
                bindBack();
            }
        });
        bindBack();
    }

    public void bindBack(){
        cmbFromCurrency.setModel(new DefaultComboBoxModel<String>(viewModel.comboBoxData));
        cmbToCurrency.setModel(new DefaultComboBoxModel<String>(viewModel.comboBoxData));
        cmbFromCurrency.setSelectedIndex(viewModel.fromCurrencyIndex);
        cmbToCurrency.setSelectedIndex(viewModel.toCurrencyIndex);

        txtConvertResult.setText(viewModel.toCurrencyMoneyAmount);
        txtFromCurrencyAmount.setText(viewModel.moneyAmount);
    }

    public void bind(){
        viewModel.fromCurrencyIndex = cmbFromCurrency.getSelectedIndex();
        viewModel.toCurrencyIndex = cmbToCurrency.getSelectedIndex();
        viewModel.moneyAmount = txtFromCurrencyAmount.getText();
    }

    private JPanel mainPanel;
    private JTextField txtFromCurrencyAmount;
    private JButton convertButton;
    private JComboBox<String> cmbFromCurrency;
    private JComboBox<String> cmbToCurrency;
    private JTextField txtConvertResult;

}
