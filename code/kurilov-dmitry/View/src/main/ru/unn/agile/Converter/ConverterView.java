package ru.unn.agile.Converter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static ru.unn.agile.Converter.ViewModel.*;

public class ConverterView
{
    private JPanel MainPanel;
    private ViewModel viewModel;
    private JComboBox<ViewModel.Systems> fromComboBox;
    private JComboBox<ViewModel.Systems> toComboBox;
    private JButton calculateButton;
    private JTextField inputTextField;
    private JLabel statusLabel;
    private JTextField resultTextField;

    public ConverterView(ViewModel viewModel)
    {
        this.viewModel = viewModel;
        backBind();

        loadListOfSystems();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                ConverterView.this.viewModel.calculate();
                backBind();
            }
        });


        KeyAdapter keyListener = new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                bind();
                ConverterView.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        inputTextField.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConverterView");
        frame.setContentPane(new ConverterView(new ViewModel()).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfSystems()
    {
        Systems[] syst = Systems.values();
        fromComboBox.setModel(new JComboBox<ViewModel.Systems>(syst).getModel());
        toComboBox.setModel(new JComboBox<ViewModel.Systems>(syst).getModel());
    }

    public void bind()
    {
        viewModel.inputNumber = inputTextField.getText();

        viewModel.inputSys = (ViewModel.Systems) fromComboBox.getSelectedItem();
        System.out.println(viewModel.inputSys);

        viewModel.outputSys = (ViewModel.Systems) toComboBox.getSelectedItem();
        System.out.println(viewModel.outputSys);

        viewModel.result = resultTextField.getText();
        viewModel.status = statusLabel.getText();
    }

    public void backBind()
    {
        inputTextField.setText(viewModel.inputNumber);

        resultTextField.setText(viewModel.result);
        statusLabel.setText(viewModel.status);

        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled);
    }


}
