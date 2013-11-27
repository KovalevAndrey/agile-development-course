package ru.unn.agile.colorConverter.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ru.unn.agile.colorConverter.viewmodel.ViewModel;

public class ConverterForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ConverterForm");
        frame.setContentPane(new ConverterForm(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private ViewModel viewModel;

    public ConverterForm(ViewModel viewModel) {
        this.viewModel = viewModel;
        loadListOfColorSpaces();
        backBind();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                ConverterForm.this.viewModel.convert();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                ConverterForm.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        firstColorFirstValueField.addKeyListener(keyListener);
        firstColorSecondValueField.addKeyListener(keyListener);
        firstColorThirdValueField.addKeyListener(keyListener);
    }

    private void loadListOfColorSpaces() {
        ViewModel.ColorSpace[] colorsSpaces = ViewModel.ColorSpace.values();
        firstColorSpaceCombo.setModel(new JComboBox<ViewModel.ColorSpace>(colorsSpaces).getModel());
        secondColorSpaceCombo.setModel(new JComboBox<ViewModel.ColorSpace>(colorsSpaces).getModel());
    }

    private void bind() {
        viewModel.firstColorFirstValue = firstColorFirstValueField.getText();
        viewModel.firstColorSecondValue = firstColorSecondValueField.getText();
        viewModel.firstColorThirdValue = firstColorThirdValueField.getText();

        viewModel.secondColorFirstValue = secondColorFirstValueField.getText();
        viewModel.secondColorSecondValue = secondColorSecondValueField.getText();
        viewModel.secondColorThirdValue = secondColorThirdValueField.getText();

        viewModel.firstColorSpace = (ViewModel.ColorSpace) firstColorSpaceCombo.getSelectedItem();
        viewModel.secondColorSpace = (ViewModel.ColorSpace) secondColorSpaceCombo.getSelectedItem();

        viewModel.status = statusLabel.getText();
    }

    private void backBind() {
        firstColorFirstValueField.setText(viewModel.firstColorFirstValue);
        firstColorSecondValueField.setText(viewModel.firstColorSecondValue);
        firstColorThirdValueField.setText(viewModel.firstColorThirdValue);

        secondColorFirstValueField.setText(viewModel.secondColorFirstValue);
        secondColorSecondValueField.setText(viewModel.secondColorSecondValue);
        secondColorThirdValueField.setText(viewModel.secondColorThirdValue);

        convertButton.setEnabled(viewModel.isConvertButtonEnabled);
        statusLabel.setText(viewModel.status);
    }

    private JPanel mainPanel;
    private JButton convertButton;
    private JTextField firstColorFirstValueField;
    private JTextField firstColorSecondValueField;
    private JTextField firstColorThirdValueField;
    private JTextField secondColorThirdValueField;
    private JTextField secondColorSecondValueField;
    private JTextField secondColorFirstValueField;
    private JLabel statusLabel;
    private JComboBox<ViewModel.ColorSpace> secondColorSpaceCombo;
    private JComboBox<ViewModel.ColorSpace> firstColorSpaceCombo;
}