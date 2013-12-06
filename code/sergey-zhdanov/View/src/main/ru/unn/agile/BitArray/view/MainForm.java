package ru.unn.agile.BitArray.view;

import javax.swing.*;

import ru.unn.agile.BitArray.Insfrastructure.TextLogger;
import ru.unn.agile.BitArray.viewmodel.ViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm {
    private JPanel mainPanel;
    private JTextArea curArrayTxtArea;
    private JTabbedPane tabbedPane1;
    private JTextField strInputTxt;
    private JButton strInputBtn;
    private JTextField intArrayInputTxt;
    private JButton intArrayInputBtn;
    private JTextField indForSetTxt;
    private JButton setValZeroBtn;
    private JButton setValOneBtn;
    private JButton notBtn;
    private JButton lsthBtn;
    private JButton rshtBtn;
    private JTextField startIndOutTxt;
    private JTextField countOutTxt;
    private JButton wholeOutBtn;
    private JButton outToStrBtn;
    private JButton outToIntsBtn;
    private JTextArea outArrayTxtArea;
    private JLabel curLenLbl;
    private JList logList;

    ViewModel viewModel;
    public static void main(String[] args) {

        JFrame frame = new JFrame("BitArray demo");

        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainForm() {
        this.viewModel = new ViewModel(new TextLogger("./log.log"));
        backBind();
        strInputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.inputArrayFromBitString(strInputTxt.getText());
                backBind();
            }
        });
        intArrayInputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.inputArrayFromStringOfInts(intArrayInputTxt.getText());
                backBind();
            }
        });
        setValZeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.setZeroToIndex(indForSetTxt.getText());
                backBind();
            }
        });
        setValOneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.setOneToIndex(indForSetTxt.getText());
                backBind();
            }
        });
        notBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.notAction();
                backBind();
            }
        });
        lsthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.leftShift();
                backBind();
            }
        });
        rshtBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.rightShift();
                backBind();
            }
        });
        wholeOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.markWholeArrayToOutput();
                backBind();
            }
        });
        outToStrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                viewModel.outputToBitString();
                backBind();
            }
        });
        outToIntsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                viewModel.outputToStringOfInts();
                backBind();
            }
        });

    }

    private void bind() {
        viewModel.setBeginIndexToOutput(startIndOutTxt.getText());
        viewModel.setBitsCountToOutput(countOutTxt.getText());
    }

    private void backBind() {
        //field to bind
        curArrayTxtArea.setText(viewModel.getBitStringOfCurrentArray());
        outArrayTxtArea.setText(viewModel.getArrayToOutput());
        startIndOutTxt.setText(viewModel.getBeginIndexToOutput());
        countOutTxt.setText(viewModel.getBitsCountToOutput());
        curLenLbl.setText(viewModel.getLengthOfCurrentArray());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
        showErrorIfNeed(viewModel.getError());
    }

    private void showErrorIfNeed(String error) {
        if(error != null && error.length() > 0) {
            JOptionPane.showMessageDialog(null, error, "Error", 1);
        }
    }
}
