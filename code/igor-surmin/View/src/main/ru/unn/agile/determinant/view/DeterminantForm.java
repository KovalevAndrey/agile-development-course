package ru.unn.agile.determinant.view;

import ru.unn.agile.determinant.viewmodel.DeterminantViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DeterminantForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DeterminantForm");
        frame.setContentPane(new DeterminantForm(new DeterminantViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField tfMatrixSize;
    private JTextArea taMatrix;
    private JButton btnCalculate;
    private JLabel lblMatrixSize;
    private JLabel lblMatrix;
    private JLabel lblResult;
    private JTextField tfResult;

    private DeterminantViewModel viewModel;

    public DeterminantForm(DeterminantViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                DeterminantForm.this.viewModel.Calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                DeterminantForm.this.viewModel.handleKey();
                backBind();
            }
        };

        tfMatrixSize.addKeyListener(keyListener);
        taMatrix.addKeyListener(keyListener);
    }

    public void bind() {
        viewModel.matrixSize = tfMatrixSize.getText();
        viewModel.matrix = taMatrix.getText();
        viewModel.isCalculateButtonEnabled = btnCalculate.isEnabled();
        viewModel.status = tfResult.getText();
    }

    public void backBind() {
        tfMatrixSize.setText(viewModel.matrixSize);
        taMatrix.setText(viewModel.matrix);
        btnCalculate.setEnabled(viewModel.isCalculateButtonEnabled);
        tfResult.setText(viewModel.status);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
