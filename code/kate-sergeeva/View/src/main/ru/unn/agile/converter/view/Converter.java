package ru.unn.agile.converter.view;

import ru.unn.agile.converter.viewmodel.ViewModel;
import ru.unn.agile.converter.model.Unit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Converter {

    private ViewModel viewModel;
    private JPanel mainPanel;
    private JComboBox<Unit> comboBoxInput;
    private JComboBox<Unit> comboBoxOutput;
    private JTextField txtInput;
    private JTextField txtOutput;
    private JButton convertButton;
    private JLabel JlabelStatus;

    public Converter(ViewModel viewModel)
    {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

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
        txtInput.addKeyListener(keyListener);
    }

    public void bind() {
        viewModel.inputValue = txtInput.getText();

        viewModel.inputUnit = (Unit) comboBoxInput.getSelectedItem();
        viewModel.outputUnit = (Unit) comboBoxOutput.getSelectedItem();

        viewModel.outputValue = txtOutput.getText();
        viewModel.status = JlabelStatus.getText();
    }

    public void backBind() {
        txtInput.setText(viewModel.inputValue);

        txtOutput.setText(viewModel.outputValue);
        JlabelStatus.setText(viewModel.status);

        convertButton.setEnabled(viewModel.isConvertButtonEnabled);
    }

    private void loadListOfOperations() {
        Unit[] operations = Unit.values();
        comboBoxInput.setModel(new JComboBox<Unit>(operations).getModel());
        comboBoxOutput.setModel(new JComboBox<Unit>(operations).getModel());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
