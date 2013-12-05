package ru.unn.agile.converter.view;

import ru.unn.agile.converter.infrastructure.TxtLogger;
import ru.unn.agile.converter.viewmodel.LogStatus;
import ru.unn.agile.converter.viewmodel.ViewModel;
import ru.unn.agile.converter.model.Unit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class Converter {

    private ViewModel viewModel;
    private JPanel mainPanel;
    private JComboBox<Unit> comboBoxInput;
    private JComboBox<Unit> comboBoxOutput;
    private JTextField txtInput;
    private JTextField txtOutput;
    private JButton convertButton;
    private JLabel JlabelStatus;
    private JList<String> lstLog;
    private JComboBox comboBoxTypeLog;
    private JLabel JlabelLogStatus;

    public Converter(ViewModel viewModel)
    {
        this.viewModel = viewModel;
        backBind();

        loadListOfUnits();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Converter.this.viewModel.convert();
                backBind();
            }
        });
        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                Converter.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        comboBoxInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        comboBoxOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
        comboBoxTypeLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
        txtInput.addKeyListener(keyListener);
    }

    public void bind() {
        viewModel.setInputValue(txtInput.getText());

        viewModel.setInputUnit((Unit) comboBoxInput.getSelectedItem());
        viewModel.setOutputUnit((Unit) comboBoxOutput.getSelectedItem());

        viewModel.setOutputValue(txtOutput.getText());
        viewModel.setStatus(JlabelStatus.getText());
    }

    public void backBind() {
        txtInput.setText(viewModel.getInputValue());

        txtOutput.setText(viewModel.getOutputValue());
        JlabelStatus.setText(viewModel.getStatus());

        convertButton.setEnabled(viewModel.getIsConvertButtonEnabled());

        List<String> log = viewModel.getLog((LogStatus)comboBoxTypeLog.getSelectedItem());
        String[] items = log.toArray(new String[log.size()]);
        lstLog.setListData(items);
    }

    private void loadListOfUnits() {
        Unit[] units = Unit.values();
        comboBoxInput.setModel(new JComboBox<Unit>(units).getModel());
        comboBoxOutput.setModel(new JComboBox<Unit>(units).getModel());
        LogStatus[] logStatuses = LogStatus.values();
        comboBoxTypeLog.setModel(new JComboBox<LogStatus>(logStatuses).getModel());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        TxtLogger logger = new TxtLogger("./Calculator.log");
        frame.setContentPane(new Converter(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
