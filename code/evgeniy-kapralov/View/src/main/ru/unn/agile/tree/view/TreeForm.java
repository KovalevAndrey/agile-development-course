package ru.unn.agile.tree.view;

import javax.swing.*;

import ru.unn.agile.tree.viewmodel.ILogger;
import ru.unn.agile.tree.viewmodel.TreeViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeForm extends TreeViewModel {
    private JTextField treeValuesTextField;
    private JPanel mainPanel;
    private JTextField findValueTextField;
    private JButton findButton;
    private JLabel resultLabel;

    public TreeForm(ILogger logger) {
        super(logger);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findActionHandler.onClick();
            }
        });
    }

    @Override
    public void bind() {
        treeValues = treeValuesTextField.getText();
        findValue = findValueTextField.getText();
        result = resultLabel.getText();
    }

    @Override
    public void unbind() {
        treeValuesTextField.setText(treeValues);
        findValueTextField.setText(findValue);
        resultLabel.setText(result);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TreeForm");
        ILogger logger = null;
        frame.setContentPane(new TreeForm(logger).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
