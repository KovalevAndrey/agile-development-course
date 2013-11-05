package ru.unn.agile.MathStatistic.view;

import ru.unn.agile.MathStatistic.viewModel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField inputDataTextField;
    private JComboBox<ViewModel.Statistic> listOfStatistic;
    private JButton calcItButton;
    private JTextField outputDataTextField;
    private JLabel status;
    private ViewModel viewModel;

    public Calculator(ViewModel viewModel) {
        this.viewModel = viewModel;

        backBind();
        loadListOfOperations();

        calcItButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Calculator.this.viewModel.calcIt();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                Calculator.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        inputDataTextField.addKeyListener(keyListener);
    }

    public void bind() {
        viewModel.inputData = inputDataTextField.getText();
        viewModel.operation = (ViewModel.Statistic) listOfStatistic.getSelectedItem();
    }

    public void backBind() {
        outputDataTextField.setText(viewModel.outputData);
        status.setText(viewModel.status);
        calcItButton.setEnabled(viewModel.isCalculateButtonEnabled);
    }

    private void loadListOfOperations() {
        ViewModel.Statistic[] operations = ViewModel.Statistic.values();
        listOfStatistic.setModel(new JComboBox<ViewModel.Statistic>(operations).getModel());
        status.setText(viewModel.status);
    }
}
