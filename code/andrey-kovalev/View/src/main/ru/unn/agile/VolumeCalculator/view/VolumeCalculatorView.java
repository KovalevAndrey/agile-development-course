package ru.unn.agile.VolumeCalculator.view;

import ru.unn.agile.VolumeCalculator.viewmodel.VolumeCalculatorViewModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeCalculatorView {
    private JPanel mainPanel;
    private JButton btnCalculate;
    private JTextField textFieldArg1;
    private JTextField textFieldArg2;
    private JTextField textFieldResult;
    private JComboBox<VolumeCalculatorViewModel.TypeFigure> cbTypeFigure;
    private JTextArea txtArea;
    private JLabel lblResult;
    private JLabel lblArg1;
    private JLabel lblArg2;

    VolumeCalculatorViewModel volumeCalculatorViewModel;

    public void bind() {
        volumeCalculatorViewModel.arg1= textFieldArg1.getText();
        volumeCalculatorViewModel.arg2 = textFieldArg2.getText();
        volumeCalculatorViewModel.result = textFieldResult.getText();
        volumeCalculatorViewModel.typeFigure = (VolumeCalculatorViewModel.TypeFigure) cbTypeFigure.getSelectedItem();
        volumeCalculatorViewModel.isVisibleTextFieldArg2 =textFieldArg2.isVisible();
        volumeCalculatorViewModel.nameArg1=lblArg1.getText();
        volumeCalculatorViewModel.nameArg2=lblArg2.getText();
        volumeCalculatorViewModel.status = txtArea.getText();
    }

    public void backBind() {
        textFieldArg1.setText(volumeCalculatorViewModel.arg1);
        textFieldArg2.setText(volumeCalculatorViewModel.arg2);
        textFieldResult.setText(volumeCalculatorViewModel.result);
        textFieldArg2.setVisible(volumeCalculatorViewModel.isVisibleTextFieldArg2);
        lblArg1.setText(volumeCalculatorViewModel.nameArg1);
        lblArg2.setText(volumeCalculatorViewModel.nameArg2);
        txtArea.setText(volumeCalculatorViewModel.status);
    }

    private void loadListOfOperations() {
        VolumeCalculatorViewModel.TypeFigure[] typesFigures = VolumeCalculatorViewModel.TypeFigure.values();
        cbTypeFigure.setModel(new JComboBox<VolumeCalculatorViewModel.TypeFigure>(typesFigures).getModel());
    }
    public VolumeCalculatorView() {
        txtArea.setEditable(false);
        textFieldResult.setEditable(false);
        this.volumeCalculatorViewModel=  new VolumeCalculatorViewModel();
        backBind();

        loadListOfOperations();
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                VolumeCalculatorView.this.volumeCalculatorViewModel.calculate(textFieldArg1.getText(), textFieldArg2.getText());
                backBind();
            }
        });

        cbTypeFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                VolumeCalculatorView.this.volumeCalculatorViewModel.setNameAndVisibleForArguments(cbTypeFigure.getItemAt(cbTypeFigure.getSelectedIndex()));
                backBind();
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("VolumeCalculatorView");
        frame.setContentPane(new VolumeCalculatorView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
