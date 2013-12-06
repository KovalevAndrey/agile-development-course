package ru.unn.agile.colorConverter.view;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import ru.unn.agile.colorConverter.infrastructure.XmlLogger;

import ru.unn.agile.colorConverter.viewmodel.ViewModel;

public class ConverterForm {
    public static void main(String[] args) {
        XmlLogger logger = null;
        try {
            logger = new XmlLogger("./log.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("ConverterForm");
        frame.setContentPane(new ConverterForm(new ViewModel(logger)).mainPanel);
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

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                backBind();
            }
        };

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

        firstColorSpaceCombo.addActionListener(actionListener);
        secondColorSpaceCombo.addActionListener(actionListener);
    }

    private void loadListOfColorSpaces() {
        ViewModel.ColorSpace[] colorsSpaces = ViewModel.ColorSpace.values();
        firstColorSpaceCombo.setModel(new JComboBox<ViewModel.ColorSpace>(colorsSpaces).getModel());
        secondColorSpaceCombo.setModel(new JComboBox<ViewModel.ColorSpace>(colorsSpaces).getModel());
    }

    private void bind() {
        viewModel.setFirstColorFirstValue(firstColorFirstValueField.getText());
        viewModel.setFirstColorSecondValue(firstColorSecondValueField.getText());
        viewModel.setFirstColorThirdValue(firstColorThirdValueField.getText());

        viewModel.setFirstColorSpace((ViewModel.ColorSpace) firstColorSpaceCombo.getSelectedItem());
        viewModel.setSecondColorSpace((ViewModel.ColorSpace) secondColorSpaceCombo.getSelectedItem());

        viewModel.setStatus(statusLabel.getText());
    }

    private void backBind() {
        firstColorFirstValueField.setText(viewModel.getFirstColorFirstValue());
        firstColorSecondValueField.setText(viewModel.getFirstColorSecondValue());
        firstColorThirdValueField.setText(viewModel.getFirstColorThirdValue());

        secondColorFirstValueField.setText(viewModel.getSecondColorFirstValue());
        secondColorSecondValueField.setText(viewModel.getSecondColorSecondValue());
        secondColorThirdValueField.setText(viewModel.getSecondColorThirdValue());

        convertButton.setEnabled(viewModel.isConvertButtonEnabled());
        statusLabel.setText(viewModel.getStatus());

        List<String> logContent = viewModel.getLog();
        String[] items = logContent.toArray(new String[logContent.size()]);
        logList.setListData(items);
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
    private JList<String> logList;
}