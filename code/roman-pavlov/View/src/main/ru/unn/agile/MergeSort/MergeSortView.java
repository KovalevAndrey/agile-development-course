package ru.unn.agile.MergeSort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MergeSortView {
    private JPanel sortForm;
    private JLabel lblArrayMark;
    private JTextField textFieldArray;
    private JButton buttonSort;
    private JLabel lblSortArray;
    private JTextField textFieldResult;
    private JLabel lblStatusMark;
    private JLabel lblStatus;
    private MergeSortViewModel viewModel;

    public MergeSortView(MergeSortViewModel viewModel) {
        MergeSortView.this.viewModel = viewModel;
        MergeSortView.this.buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                MergeSortView.this.viewModel.processSort();
                backBind();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MergeSortView");
        frame.setContentPane(new MergeSortView(new MergeSortViewModel()).sortForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 500, 250);
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.statusText = lblStatus.getText();
        viewModel.resultText = textFieldResult.getText();
        viewModel.arrayText = textFieldArray.getText();
    }

    private void backBind() {
        lblStatus.setText(viewModel.statusText);
        textFieldArray.setText(viewModel.arrayText);
        textFieldResult.setText(viewModel.resultText);
    }
}
