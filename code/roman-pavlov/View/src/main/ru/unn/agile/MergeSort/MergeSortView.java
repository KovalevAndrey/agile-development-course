package ru.unn.agile.MergeSort;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: TROLOLO
 * Date: 08.11.13
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class MergeSortView {
    private JPanel sortForm;
    private JLabel lblArrayMark;
    private JTextField textFieldArray;
    private JButton buttonSort;
    private JLabel lblSortArray;
    private JTextField textFieldResult;
    private JLabel lblStatusMark;
    private JLabel lblStatus;

    public MergeSortView() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MergeSortView");
        frame.setContentPane(new MergeSortView().sortForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100,100,500,250);
        frame.setVisible(true);
    }
}
