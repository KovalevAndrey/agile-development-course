package ru.unn.agile.CreditCalculator.view;

import ru.unn.agile.CreditCalculator.viewmodel.CreditCalculatorViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreditCalculatorView {
    private JPanel mainPanel;
    private CreditCalculatorViewModel creditCalculatorViewModel;
    // Fields to bind
    private JTextField creditAmountTextField;
    private JTextField monthsCountTextField;
    private JTextField percentTextField;
    private JButton CalculateButton;
    private JComboBox<CreditCalculatorViewModel.PaymentType> paymentTypesComboBox;
    private JTextField resultTextField;
    private JLabel StatusLabel;

    public CreditCalculatorView(CreditCalculatorViewModel viewModel) {
        this.creditCalculatorViewModel = viewModel;
        backBind();

        loadListOfPaymentTypes();

        CalculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                CreditCalculatorView.this.creditCalculatorViewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                CreditCalculatorView.this.creditCalculatorViewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        creditAmountTextField.addKeyListener(keyListener);
        monthsCountTextField.addKeyListener(keyListener);
        percentTextField.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new CreditCalculatorView(new CreditCalculatorViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfPaymentTypes() {
        CreditCalculatorViewModel.PaymentType[] operations = CreditCalculatorViewModel.PaymentType.values();
        paymentTypesComboBox.setModel(new JComboBox<CreditCalculatorViewModel.PaymentType>(operations).getModel());
    }

    public void bind() {
        creditCalculatorViewModel.creditAmountString = creditAmountTextField.getText();
        creditCalculatorViewModel.monthsCountString = monthsCountTextField.getText();
        creditCalculatorViewModel.percentString = percentTextField.getText();

        creditCalculatorViewModel.paymentType = (CreditCalculatorViewModel.PaymentType) paymentTypesComboBox.getSelectedItem();

        creditCalculatorViewModel.result = resultTextField.getText();
        creditCalculatorViewModel.status = StatusLabel.getText();
    }

    public void backBind() {
        creditAmountTextField.setText(creditCalculatorViewModel.creditAmountString);
        monthsCountTextField.setText(creditCalculatorViewModel.monthsCountString);
        percentTextField.setText(creditCalculatorViewModel.percentString);

        resultTextField.setText(creditCalculatorViewModel.result);
        StatusLabel.setText(creditCalculatorViewModel.status);

        CalculateButton.setEnabled(creditCalculatorViewModel.isCalculateButtonEnabled);
    }
}
