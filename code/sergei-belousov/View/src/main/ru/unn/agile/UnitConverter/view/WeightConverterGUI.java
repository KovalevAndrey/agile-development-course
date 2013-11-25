package ru.unn.agile.UnitConverter.view;

import ru.unn.agile.UnitConverter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeightConverterGUI {
    private ViewModel viewModel;
    private JTextField fromValue;
    private JTextField toValue;
    private JButton actionButton;
    private JCheckBox addCheckBox;
    private JPanel mainPanel;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JLabel errorMsg;

    WeightConverterGUI(ViewModel viewModel) {
        this.viewModel = viewModel;
        fromUnit.addItem("");
        toUnit.addItem("");
        backBind();

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                WeightConverterGUI.this.viewModel.processKey();
                backBind();
            }
        });

        addCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WeightConverterGUI.this.viewModel.setAddMode(addCheckBox.isSelected());
                backBind();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WeightConverterGUI");

        frame.setContentPane(new WeightConverterGUI(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void bind() {
        viewModel.setAddMode(addCheckBox.isSelected());
        viewModel.fromUnitText = fromUnit.getSelectedItem().toString();
        viewModel.toUnitText = toUnit.getSelectedItem().toString();
        viewModel.fromValueText = fromValue.getText();
        viewModel.toValueText = toValue.getText();
    }

    public void loadTable() {
        fromUnit.removeAllItems();
        toUnit.removeAllItems();
        fromUnit.setModel(new DefaultComboBoxModel<String>(viewModel.unitList.toArray(new String[viewModel.unitList.size()])));
        toUnit.setModel(new DefaultComboBoxModel<String>(viewModel.unitList.toArray(new String[viewModel.unitList.size()])));
    }

    public void backBind() {
        actionButton.setText(viewModel.actionButtonText);
        fromValue.setText(viewModel.fromValueText);
        toValue.setEnabled(viewModel.toValueEnabled);
        toValue.setText(viewModel.toValueText);
        toUnit.setEditable(viewModel.toUnitEditable);
        fromUnit.setEditable(viewModel.fromUnitEditable);
        errorMsg.setText(viewModel.errorMsg);
        loadTable();
    }
}
