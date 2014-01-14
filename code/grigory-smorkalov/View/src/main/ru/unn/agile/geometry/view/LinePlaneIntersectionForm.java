package ru.unn.agile.geometry.view;

import ru.unn.agile.geometry.viewModel.LinePlainIntersection;

import javax.swing.*;
import java.awt.event.*;

public class LinePlaneIntersectionForm {
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

    private LinePlainIntersection viewModel;

    public LinePlaneIntersectionForm(LinePlainIntersection formVewModel) {
        this.viewModel = formVewModel;

        linePx.addKeyListener(new KeyAdapter() {
        });
        calcButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LinePlaneIntersectionForm.this.getData();
                LinePlaneIntersectionForm.this.viewModel.calc();
                LinePlaneIntersectionForm.this.setData();
            }
        });
        JTextField textFields[] = {linePx, linePy, lineDirX, lineDirY, plainPointX, plainPointY, plainOrtX, plainOrtY, linePz, lineDirZ, plainPointZ, plainOrtZ};
        for(JTextField textField : textFields) {
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyTyped(e);
                    LinePlaneIntersectionForm.this.getData();
                    LinePlaneIntersectionForm.this.viewModel.inputSomething();
                    calcButton.setEnabled(LinePlaneIntersectionForm.this.viewModel.isCalcButtonEnabled());
                }
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LinePlaneIntersectionForm");
        frame.setContentPane(new LinePlaneIntersectionForm(new LinePlainIntersection()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setData() {
        linePx.setText(viewModel.getLinePx());
        linePy.setText(viewModel.getLinePy());
        lineDirX.setText(viewModel.getLineDirX());
        lineDirY.setText(viewModel.getLineDirY());
        plainPointX.setText(viewModel.getPlainPointX());
        plainPointY.setText(viewModel.getPlainPointY());
        plainOrtX.setText(viewModel.getPlainOrtX());
        plainOrtY.setText(viewModel.getPlainOrtY());
        resultX.setText(viewModel.getResultX());
        resultY.setText(viewModel.getResultY());
        linePz.setText(viewModel.getLinePz());
        lineDirZ.setText(viewModel.getLineDirZ());
        plainPointZ.setText(viewModel.getPlainPointZ());
        plainOrtZ.setText(viewModel.getPlainOrtZ());
        resultZ.setText(viewModel.getResultZ());
        calcButton.setEnabled(viewModel.isCalcButtonEnabled());
    }

    public void getData() {
        viewModel.setLinePx(linePx.getText());
        viewModel.setLinePy(linePy.getText());
        viewModel.setLineDirX(lineDirX.getText());
        viewModel.setLineDirY(lineDirY.getText());
        viewModel.setPlainPointX(plainPointX.getText());
        viewModel.setPlainPointY(plainPointY.getText());
        viewModel.setPlainOrtX(plainOrtX.getText());
        viewModel.setPlainOrtY(plainOrtY.getText());
        viewModel.setResultX(resultX.getText());
        viewModel.setResultY(resultY.getText());
        viewModel.setLinePz(linePz.getText());
        viewModel.setLineDirZ(lineDirZ.getText());
        viewModel.setPlainPointZ(plainPointZ.getText());
        viewModel.setPlainOrtZ(plainOrtZ.getText());
        viewModel.setResultZ(resultZ.getText());
    }
}
