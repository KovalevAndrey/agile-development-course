package ru.unn.agile.deque.view;

import ru.unn.agile.deque.viewmodel.ViewModel;
import ru.unn.agile.deque.infrastructure.TxtLogger;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class DequeEmulator {
    private JPanel mainPanel;
    private JButton createDequeButton;
    private JComboBox<ViewModel.Action> actionsComboBox;
    private JTextField pushTextField;
    private JButton actButton;
    private JTextArea dequeTextArea;
    private JLabel statusLabel;
    private JTextField maximumSizeTextField;
    private JList<String> logList;
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
        maximumSizeTextField.setText(viewModel.getMaximumSize());
        pushTextField.setText(viewModel.getPushedValue());
        dequeTextArea.setText(viewModel.getDequeRepresentation());
        statusLabel.setText(viewModel.getStatus());

        actionsComboBox.setEnabled(viewModel.getActionsComboBoxEnabled());
        pushTextField.setEnabled(viewModel.getPushTextFieldEnabled());
        actButton.setEnabled(viewModel.getActButtonEnabled());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }

    public void bind(){
        viewModel.setMaximumSize(maximumSizeTextField.getText());
        viewModel.setPushedValue(pushTextField.getText());
        viewModel.setDequeRepresentation(dequeTextArea.getText());
        viewModel.setStatus(statusLabel.getText());
        viewModel.setAction ((ViewModel.Action)actionsComboBox.getSelectedItem());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Deque");
        frame.setContentPane(new DequeEmulator(new ViewModel(new TxtLogger("log.txt"))).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
