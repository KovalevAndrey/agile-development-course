package ru.unn.agile.MathStatistic.view;

import ru.unn.agile.MathStatistic.infrastructure.TextFileLogger;
import ru.unn.agile.MathStatistic.viewModel.ViewModel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        try {
            frame.setContentPane(new Calculator(new ViewModel(new TextFileLogger("./MathStatistic.log"))).mainPanel);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JButton calcItButton;
    private JTextField inputData;
    private JTextField outputData;
    private JComboBox<ViewModel.Statistic> listOfStatistic;
    private JLabel status;
    private JList logList;


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

        inputData.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                bind();
                Calculator.this.viewModel.convertToArrayOfDoubles();
                backBind();
            }
        };
        inputData.addFocusListener(focusLostListener);

    }

    public void bind() {
        viewModel.setInputData(inputData.getText());
        viewModel.setOperation((ViewModel.Statistic) listOfStatistic.getSelectedItem());
    }

    public void backBind() {
        outputData.setText(viewModel.getOutputData());
        status.setText(viewModel.getStatus());
        calcItButton.setEnabled(viewModel.getCalculateButtonState());
        List<String> entireLog = viewModel.getEntireLog();
        String[] items = entireLog.toArray(new String[entireLog.size()]);
        logList.setListData(items);
    }

    private void loadListOfOperations() {
        ViewModel.Statistic[] operations = ViewModel.Statistic.values();
        listOfStatistic.setModel(new JComboBox<ViewModel.Statistic>(operations).getModel());
        status.setText(viewModel.getStatus());
    }
}
