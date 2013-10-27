package ru.unn.agile.ComplexNumber.view;

import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends ViewModel
{
    private JPanel mainPanel;
    private JButton btnCalc;

    // Fields to bind
    private JTextField txtZ1Re;
    private JTextField txtZ1Im;
    private JTextField txtZ2Re;
    private JTextField txtZ2Im;
    private JComboBox cbOperation;
    private JTextField txtResult;
    private JLabel lbStatus;

    public Calculator() {
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                calcActionHandler.onClick();
            }
        });
    }

    @Override
    public void bind() {
        re1 = txtZ1Re.getText();
        im1 = txtZ1Im.getText();
        re2 = txtZ2Re.getText();
        im2 = txtZ2Im.getText();

        op = ViewModel.Operation.values()[cbOperation.getSelectedIndex()];

        result = txtResult.getText();
        message = lbStatus.getText();
    }

    @Override
    public void unbind() {
        txtZ1Re.setText(re1);
        txtZ1Im.setText(im1);
        txtZ2Re.setText(re2);
        txtZ2Im.setText(im2);

        txtResult.setText(result);
        lbStatus.setText(message);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
