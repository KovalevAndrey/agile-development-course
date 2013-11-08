package ru.unn.agile.determinant.view;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 08.11.13
 * Time: 1:46
 * To change this template use File | Settings | File Templates.
 */
public class DeterminantForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DeterminantForm");
        frame.setContentPane(new DeterminantForm().mainPanel);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
