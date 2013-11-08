package ru.unn.agile.deque.view;

import ru.unn.agile.deque.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DequeEmulator {
    private JPanel mainPanel;
    private JButton createDequeButton;
    private JComboBox<ViewModel.Action> actionsComboBox;
    private JTextField pushTextField;
    private JButton actButton;
    private JTextArea dequeTextArea;
    private JLabel statusLabel;
    private JTextField maximumSizeTextField;
    private ViewModel viewModel;

    public DequeEmulator(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        createDequeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                DequeEmulator.this.viewModel.createDeque();
                backBind();
            }
        });

        actButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                DequeEmulator.this.viewModel.act();
                backBind();
            }
        });
    }

    private void loadListOfOperations() {
        ViewModel.Action[] operations = ViewModel.Action.values();
        actionsComboBox.setModel(new JComboBox<ViewModel.Action>(operations).getModel());
    }

    public void backBind() {
        maximumSizeTextField.setText(viewModel.maximumSize);
        pushTextField.setText(viewModel.pushedValue);
        dequeTextArea.setText(viewModel.dequeRepresentation);
        statusLabel.setText(viewModel.status);

        actionsComboBox.setEnabled(viewModel.isActionsComboBoxEnabled);
        pushTextField.setEnabled(viewModel.isPushTextFieldEnabled);
        actButton.setEnabled(viewModel.isActButtonEnabled);
    }

    public void bind(){
        viewModel.maximumSize = maximumSizeTextField.getText();
        viewModel.pushedValue = pushTextField.getText();
        viewModel.dequeRepresentation = dequeTextArea.getText();
        viewModel.status = statusLabel.getText();
        viewModel.action = (ViewModel.Action) actionsComboBox.getSelectedItem();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Deque");
        frame.setContentPane(new DequeEmulator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
