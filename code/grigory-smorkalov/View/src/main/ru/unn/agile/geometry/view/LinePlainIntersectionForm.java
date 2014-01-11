package ru.unn.agile.geometry.view;

import javax.swing.*;
import java.awt.event.KeyAdapter;

/**
 * Created by geser on 10.01.14.
 */
public class LinePlainIntersectionForm {
    private JButton calcButton;
    private JTextField linePx;
    private JTextField linePy;
    private JTextField lineDirX;
    private JTextField lineDirY;
    private JTextField plainPointX;
    private JTextField plainPointY;
    private JTextField plainOrtX;
    private JTextField plainOrtY;
    private JTextField resultX;
    private JTextField resultY;
    private JPanel mainPanel;
    private JTextField linePz;
    private JTextField lineDirZ;
    private JTextField plainPointZ;
    private JTextField plainOrtZ;
    private JTextField resultZ;

    public LinePlainIntersectionForm() {
        linePx.addKeyListener(new KeyAdapter() {
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LinePlainIntersectionForm");
        frame.setContentPane(new LinePlainIntersectionForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
