package ru.unn.agile.Polynomial.view;

import ru.unn.agile.Polynomial.viewmodel.Operation;
import ru.unn.agile.Polynomial.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator {
    private static JFrame frame;
    private ViewModel viewModel;
    private JPanel calculatorPanel;
    private JTextField polynomial2TextField;
    private JTextField polynomial1TextField;
    private JTextField resultTextField;
    private JComboBox operationComboBox;
    private JButton calculateButton;
    private JTextField statusTextField;


    public Calculator(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                try {
                    Calculator.this.viewModel.calculate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage());
                }
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                bind();
                try {
                    Calculator.this.viewModel.processKeyInTextField(e.getKeyCode());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                }
                backBind();
            }
        };

        polynomial1TextField.addKeyListener(keyListener);
        polynomial2TextField.addKeyListener(keyListener);
    }

    public static void main(String[] args) throws Exception {
        frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator(new ViewModel()).calculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void bind() {
        viewModel.polynomial1 = polynomial1TextField.getText();
        viewModel.polynomial2 = polynomial2TextField.getText();

        viewModel.operation = (Operation)operationComboBox.getSelectedItem();

        viewModel.result = resultTextField.getText();
        viewModel.status = statusTextField.getText();
    }

    public void backBind() {
        polynomial1TextField.setText(viewModel.polynomial1);
        polynomial2TextField.setText(viewModel.polynomial2);
        resultTextField.setText(viewModel.result);
        statusTextField.setText(viewModel.status);
        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled);

    }

    private void loadListOfOperations() {
        Operation[] operations = Operation.values();
        operationComboBox.setModel(new JComboBox<Operation>(operations).getModel());
    }

}
