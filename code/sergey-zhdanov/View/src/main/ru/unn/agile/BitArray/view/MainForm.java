package ru.unn.agile.BitArray.view;

import javax.swing.*;
import ru.unn.agile.BitArray.viewmodel.ViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    ViewModel viewModel;
    public static void main(String[] args) {

        JFrame frame = new JFrame("BitArray demo");

        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainForm() {
        this.viewModel = new ViewModel();
        backBind();
        strInputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.strInputAction(strInputTxt.getText());
                backBind();
            }
        });
        intArrayInputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.intArrayInputAction(intArrayInputTxt.getText());
                backBind();
            }
        });
        setValZeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.setZeroToIndexAction(indForSetTxt.getText());
                backBind();
            }
        });
        setValOneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.setOneToIndexAction(indForSetTxt.getText());
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
                viewModel.lshtAction();
                backBind();
            }
        });
        rshtBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.rshtAction();
                backBind();
            }
        });
        wholeOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.wholeToOutAction();
                backBind();
            }
        });
        outToStrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                viewModel.outToStrAction();
                backBind();
            }
        });
        outToIntsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                viewModel.outToIntsAction();
                backBind();
            }
        });

    }

    private void bind() {
        viewModel.setStartOutIndStr(startIndOutTxt.getText());
        viewModel.setCountOutStr(countOutTxt.getText());
    }

    private void backBind() {
        //field to bind
        curArrayTxtArea.setText(viewModel.getCurArrayStr());
        outArrayTxtArea.setText(viewModel.getOutArrayStr());
        startIndOutTxt.setText(viewModel.getStartOutIndStr());
        countOutTxt.setText(viewModel.getCountOutStr());
        curLenLbl.setText(viewModel.getCurLenStr());
        showErrorIfNeed(viewModel.getError());
    }

    private void showErrorIfNeed(String error) {
        if(error != null && error.length() > 0) {
            JOptionPane.showMessageDialog(null, error, "Error", 1);
        }
    }
}
