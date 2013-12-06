package ru.unn.agile.determinant.view;

import ru.unn.agile.determinant.viewmodel.DeterminantViewModel;
import ru.unn.agile.determinant.infrastructure.TxtLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class DeterminantForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DeterminantForm");
        TxtLogger logger = new TxtLogger("./Determinant.log");
        frame.setContentPane(new DeterminantForm(new DeterminantViewModel(logger)).mainPanel);
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
    private JList lstLog;

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
        viewModel.setMatrixSize(tfMatrixSize.getText());
        viewModel.setMatrix(taMatrix.getText());
        viewModel.isCalculateButtonEnabled = btnCalculate.isEnabled();
        viewModel.status = tfResult.getText();
    }

    public void backBind() {
        tfMatrixSize.setText(viewModel.getMatrixSize());
        taMatrix.setText(viewModel.getMatrix());
        btnCalculate.setEnabled(viewModel.isCalculateButtonEnabled);
        tfResult.setText(viewModel.status);

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        lstLog.setListData(items);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
