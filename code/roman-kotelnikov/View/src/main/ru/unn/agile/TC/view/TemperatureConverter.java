package ru.unn.agile.TC.view;

import ru.unn.agile.TC.AvailableScales;
import ru.unn.agile.TC.infrastructure.TxtLogger;
import ru.unn.agile.TC.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

public class TemperatureConverter {
    private ViewModel viewModel;
    private static final String logFilename = "TemperatureConverterLog.txt";

    private JPanel mainPanel;
    private JTextField txtTempValue;
    private JComboBox<AvailableScales> cbInputScale;
    private JComboBox<AvailableScales> cbResultScale;
    private JButton btnConvert;
    private JTextField txtResult;
    private JLabel lbStatus;

    public TemperatureConverter(final ViewModel viewModel) {
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

        cbInputScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (viewModel.inputScale != cbInputScale.getSelectedItem())
                {
                    bind();
                    TemperatureConverter.this.viewModel.inputParametersChanged();
                    backBind();
                }
            }
        });

        cbResultScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (viewModel.resultScale != cbResultScale.getSelectedItem())
                {
                    bind();
                    TemperatureConverter.this.viewModel.inputParametersChanged();
                    backBind();
                }
            }
        });

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (viewModel.input != e.toString())
                {
                    bind();
                    TemperatureConverter.this.viewModel.inputParametersChanged();
                    backBind();
                }
            }
        };

        txtTempValue.addFocusListener(focusLostListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature converter");

        ViewModel tempViewModel;

        try {
            tempViewModel = new ViewModel(new TxtLogger(logFilename));
        }
        catch (IOException e) {
            tempViewModel = new ViewModel();
        }

        frame.setContentPane((new TemperatureConverter(tempViewModel)).mainPanel);
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
