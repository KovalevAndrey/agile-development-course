package ru.unn.agile.leftistHeap.view;

import javax.swing.*;

import ru.unn.agile.leftistHeap.viewModel.ViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeapManager {
    private JPanel mainPanel;
    private JTextField addKeyTextField;
    private JTextField addValueTextField;
    private JButton addButton;
    private JButton getButton;
    private JButton deleteButton;
    private JLabel statusLabel;
    private JLabel minKeyLabel;
    private JLabel minValueLabel;

    private ViewModel viewModel;

    public HeapManager() {
        viewModel = new ViewModel();
    }

    public void bind() {
        viewModel.keyAdd = addKeyTextField.getText();
        viewModel.valueAdd = addValueTextField.getText();
        viewModel.keyGetDel = minKeyLabel.getText();
        viewModel.valueGetDel = minValueLabel.getText();
        viewModel.status = statusLabel.getText();
    }

    public void unbind() {
        addKeyTextField.setText(viewModel.keyAdd);
        addValueTextField.setText(viewModel.valueAdd);
        minKeyLabel.setText(viewModel.keyGetDel);
        minValueLabel.setText(viewModel.valueGetDel);
        statusLabel.setText(viewModel.status);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HeapManager");
        frame.setContentPane(new HeapManager().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
