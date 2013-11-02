package ru.unn.agile.ComplexNumber.view;

import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Calculator
{
    private JPanel mainPanel;
    private JButton btnCalc;
    private ViewModel viewModel;

    // Fields to bind
    private JTextField txtZ1Re;
    private JTextField txtZ1Im;
    private JTextField txtZ2Re;
    private JTextField txtZ2Im;
    private JComboBox cbOperation;
    private JTextField txtResult;
    private JLabel lbStatus;

    public Calculator(ViewModel viewModel) {
        this.viewModel = viewModel;

        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.calculate();
                backBind();
            }
        });

        txtZ1Re.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                bind();
                Calculator.this.viewModel.parseInputFields();
                backBind();
            }
        });
    }

    public void bind() {
        viewModel.re1 = txtZ1Re.getText();
        viewModel.im1 = txtZ1Im.getText();
        viewModel.re2 = txtZ2Re.getText();
        viewModel.im2 = txtZ2Im.getText();

        viewModel.setOperation(cbOperation.getSelectedItem().toString());

        viewModel.result = txtResult.getText();
        viewModel.status = lbStatus.getText();
    }

    public void backBind() {
        txtZ1Re.setText(viewModel.re1);
        txtZ1Im.setText(viewModel.im1);
        txtZ2Re.setText(viewModel.re2);
        txtZ2Im.setText(viewModel.im2);

        txtResult.setText(viewModel.result);
        lbStatus.setText(viewModel.status);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new Calculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
