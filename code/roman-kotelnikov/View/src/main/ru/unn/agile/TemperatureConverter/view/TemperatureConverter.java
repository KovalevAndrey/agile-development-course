package ru.unn.agile.TemperatureConverter.view;

import ru.unn.agile.TemperatureConverter.AvailableScales;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TemperatureConverter {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField txtTempValue;
    private JComboBox cbFromScale;
    private JComboBox cbToScale;
    private JButton btnConvert;
    private JTextField txtResult;
    private JLabel lbStatus;

    public TemperatureConverter(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        loadAvaliableScales();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                TemperatureConverter.this.viewModel.convert();
                backBind();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature converter");

        frame.setContentPane(new TemperatureConverter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void loadAvaliableScales() {
        AvailableScales[] scales = AvailableScales.values();
        cbToScale.setModel(new JComboBox<AvailableScales>(scales).getModel());
        cbFromScale.setModel(new JComboBox<AvailableScales>(scales).getModel());
    }

    public void bind() {
        viewModel.input = txtTempValue.getText();
        viewModel.inputScale = (AvailableScales) cbFromScale.getSelectedItem();

        viewModel.result = txtResult.getText();
        viewModel.resultScale = (AvailableScales) cbToScale.getSelectedItem();

        viewModel.status = lbStatus.getText();
    }

    public void backBind() {
        txtTempValue.setText(viewModel.input);
        cbFromScale.setSelectedItem(viewModel.inputScale);

        txtResult.setText(viewModel.result);
        cbToScale.setSelectedItem(viewModel.resultScale);

        lbStatus.setText(viewModel.status);
    }
}
