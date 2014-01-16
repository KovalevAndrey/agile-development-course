package ru.unn.agile.Polynomial.view;

import ru.unn.agile.Polynomial.infrastucture.TxtLogger;
import ru.unn.agile.Polynomial.viewmodel.Operation;
import ru.unn.agile.Polynomial.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Calculator {
    private static JFrame frame;
    private ViewModel viewModel;
    private JPanel calculatorPanel;
    private JTextField polynomial2TextField;
    private JTextField polynomial1TextField;
    private JTextField resultTextField;
    private JComboBox<Operation> operationComboBox;
    private JButton calculateButton;
    private JTextField statusTextField;
    private JList<String> logList;


    public Calculator(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                bind();
                Calculator.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        polynomial1TextField.addKeyListener(keyListener);
        polynomial2TextField.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                bind();
                Calculator.this.viewModel.focusLost();
                backBind();
            }
        };

        polynomial1TextField.addFocusListener(focusLostListener);
        polynomial2TextField.addFocusListener(focusLostListener);
    }

    public static void main(String[] args) throws Exception {
        frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator(new ViewModel(new TxtLogger("./log.txt"))).calculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void bind() {
        viewModel.setPolynomial1(polynomial1TextField.getText());
        viewModel.setPolynomial2(polynomial2TextField.getText());

        viewModel.setOperation((Operation) operationComboBox.getSelectedItem());

        viewModel.setResult(resultTextField.getText());
        viewModel.setStatus(statusTextField.getText());
    }

    public void backBind() {
        polynomial1TextField.setText(viewModel.getPolynomial1());
        polynomial2TextField.setText(viewModel.getPolynomial2());
        resultTextField.setText(viewModel.getResult());
        statusTextField.setText(viewModel.getStatus());
        calculateButton.setEnabled(viewModel.getIsCalculateButtonEnabled());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }

    private void loadListOfOperations() {
        Operation[] operations = Operation.values();
        operationComboBox.setModel(new JComboBox<Operation>(operations).getModel());
    }

}
