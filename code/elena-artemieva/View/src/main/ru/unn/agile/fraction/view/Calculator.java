package ru.unn.agile.fraction.view;

import ru.unn.agile.fraction.infrastructure.TextLogger;
import ru.unn.agile.fraction.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Calculator {

    private ViewModel viewModel;

    private JPanel mainPanel;
    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JTextField txtInput1;
    private JTextField txtInput2;
    private JTextField txtResult;
    private JList<String> listLog;

    public Calculator(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.add();
                backBind();
            }
        });

        btnSubtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.subtract();
                backBind();
            }
        });

        btnMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.multiply();
                backBind();
            }
        });

        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.divide();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                Calculator.this.viewModel.processKeyInTextField();
                backBind();
            }
        };

        txtInput1.addKeyListener(keyListener);
        txtInput2.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fraction Calculator");

        TextLogger logger = new TextLogger("./Fraction Calculator.log");

        frame.setContentPane(new Calculator(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void bind() {
        viewModel.input1 = txtInput1.getText();
        viewModel.input2 = txtInput2.getText();
        viewModel.result = txtResult.getText();
    }

    public void backBind() {
        txtInput1.setText(viewModel.input1);
        txtInput2.setText(viewModel.input2);
        txtResult.setText(viewModel.result);

        btnAdd.setEnabled(viewModel.isAddButtonEnabled);
        btnSubtract.setEnabled(viewModel.isSubtractButtonEnabled);
        btnMultiply.setEnabled(viewModel.isMultiplyButtonEnabled);
        btnDivide.setEnabled(viewModel.isDivideButtonEnabled);

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        listLog.setListData(items);
    }
}
