package ru.unn.agile.CreditCalculator.view;

import ru.unn.agile.CreditCalculator.infrastructure.RealLogger;
import ru.unn.agile.CreditCalculator.viewmodel.CreditCalculatorViewModel;
import ru.unn.agile.CreditCalculator.viewmodel.ILogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class CreditCalculatorView {
    private JPanel mainPanel;
    private CreditCalculatorViewModel creditCalculatorViewModel;
    private JTextField creditAmountTextField;
    private JTextField monthsCountTextField;
    private JTextField percentTextField;
    private JButton calculateButton;
    private JComboBox<CreditCalculatorViewModel.PaymentType> paymentTypesComboBox;
    private JTextField resultTextField;
    private JLabel statusLabel;
    private JList<String> logList;

    public CreditCalculatorView(CreditCalculatorViewModel viewModel) {
        this.creditCalculatorViewModel = viewModel;
        backBind();

        loadListOfPaymentTypes();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                CreditCalculatorView.this.creditCalculatorViewModel.calculate();
                backBind();
            }
        });

        paymentTypesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
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

        ILogger realLogger = new RealLogger("./log.txt");
        frame.setContentPane(new CreditCalculatorView(new CreditCalculatorViewModel(realLogger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfPaymentTypes() {
        CreditCalculatorViewModel.PaymentType[] operations = CreditCalculatorViewModel.PaymentType.values();
        paymentTypesComboBox.setModel(new JComboBox<CreditCalculatorViewModel.PaymentType>(operations).getModel());
    }

    public void bind() {
        creditCalculatorViewModel.setCreditAmountString(creditAmountTextField.getText());
        creditCalculatorViewModel.setMonthsCountString(monthsCountTextField.getText());
        creditCalculatorViewModel.setPercentString(percentTextField.getText());

        creditCalculatorViewModel.setPaymentType((CreditCalculatorViewModel.PaymentType) paymentTypesComboBox.getSelectedItem());
    }

    public void backBind() {
        creditAmountTextField.setText(creditCalculatorViewModel.getCreditAmountString());
        monthsCountTextField.setText(creditCalculatorViewModel.getMonthsCountString());
        percentTextField.setText(creditCalculatorViewModel.getPercentString());

        resultTextField.setText(creditCalculatorViewModel.getResult());
        statusLabel.setText(creditCalculatorViewModel.getStatus());

        calculateButton.setEnabled(creditCalculatorViewModel.getIsCalculateButtonEnabled());

        List<String> logMessages = creditCalculatorViewModel.getLog();
        String[] items = logMessages.toArray(new String[logMessages.size()]);
        logList.setListData(items);
    }
}
