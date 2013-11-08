package ru.unn.agile.AreaConverter.view;

import ru.unn.agile.areaConverter.*;
import ru.unn.agile.AreaConverter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaConverter {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField txtAreaValue;
    private JComboBox<ScaleTable> cbInputScale;
    private JComboBox<ScaleTable> cbResultScale;
    private JButton btnConvert;
    private JTextField txtResult;
    private JLabel lbStatus;

    public AreaConverter(ViewModel viewModel) {
        this.viewModel = viewModel;
        loadScaleTable();
        backBind();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                AreaConverter.this.viewModel.convert();
                backBind();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Area converter");

        frame.setContentPane(new AreaConverter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void loadScaleTable() {
        ScaleTable[] scales = ScaleTable.values();

        ComboBoxModel<ScaleTable> inputScales = new DefaultComboBoxModel<ScaleTable>(scales);
        ComboBoxModel<ScaleTable> resultScales = new DefaultComboBoxModel<ScaleTable>(scales);

        cbInputScale.setModel(inputScales);
        cbResultScale.setModel(resultScales);
    }

    public void bind() {
        viewModel.input = txtAreaValue.getText();
        viewModel.inputScale = (ScaleTable) cbInputScale.getSelectedItem();

        viewModel.result = txtResult.getText();
        viewModel.resultScale = (ScaleTable) cbResultScale.getSelectedItem();

        viewModel.status = lbStatus.getText();
    }

    public void backBind() {
        txtAreaValue.setText(viewModel.input);
        cbInputScale.setSelectedItem(viewModel.inputScale);

        txtResult.setText(viewModel.result);
        cbResultScale.setSelectedItem(viewModel.resultScale);

        lbStatus.setText(viewModel.status);
    }
}
