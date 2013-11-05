package ru.unn.agile.TemperatureConverter.view;

import ru.unn.agile.TemperatureConverter.AvailableScales;
import ru.unn.agile.TemperatureConverter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField txtTempValue;
    private JComboBox<AvailableScales> cbInputScale;
    private JComboBox<AvailableScales> cbResultScale;
    private JButton btnConvert;
    private JTextField txtResult;
    private JLabel lbStatus;

    public TemperatureConverter(ViewModel viewModel) {
        this.viewModel = viewModel;
        loadAvailableScales();
        backBind();

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

    public void loadAvailableScales() {
        AvailableScales[] scales = AvailableScales.values();

        ComboBoxModel<AvailableScales> inputScales = new DefaultComboBoxModel<AvailableScales>(scales);
        ComboBoxModel<AvailableScales> resultScales = new DefaultComboBoxModel<AvailableScales>(scales);

        cbInputScale.setModel(inputScales);
        cbResultScale.setModel(resultScales);
    }

    public void bind() {
        viewModel.input = txtTempValue.getText();
        viewModel.inputScale = (AvailableScales) cbInputScale.getSelectedItem();

        viewModel.result = txtResult.getText();
        viewModel.resultScale = (AvailableScales) cbResultScale.getSelectedItem();

        viewModel.status = lbStatus.getText();
    }

    public void backBind() {
        txtTempValue.setText(viewModel.input);
        cbInputScale.setSelectedItem(viewModel.inputScale);

        txtResult.setText(viewModel.result);
        cbResultScale.setSelectedItem(viewModel.resultScale);

        lbStatus.setText(viewModel.status);
    }
}
