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
    private JTextField planePointX;
    private JTextField planePointY;
    private JTextField plainOrtX;
    private JTextField plainOrtY;
    private JTextField resultX;
    private JTextField resultY;
    private JPanel mainPanel;
    private JTextField linePz;
    private JTextField lineDirZ;
    private JTextField planePointZ;
    private JTextField planeOrtZ;
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
                LinePlaneIntersectionForm.this.backBind();
                LinePlaneIntersectionForm.this.viewModel.calc();
                LinePlaneIntersectionForm.this.bind();
            }
        });
        JTextField textFields[] = {linePx, linePy, lineDirX, lineDirY, planePointX, planePointY, plainOrtX, plainOrtY, linePz, lineDirZ, planePointZ, planeOrtZ};
        for(JTextField textField : textFields) {
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyTyped(e);
                    LinePlaneIntersectionForm.this.backBind();
                    LinePlaneIntersectionForm.this.viewModel.inputSomething();
                    LinePlaneIntersectionForm.this.bind();
                }
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LinePlaneIntersectionForm");
        LinePlainIntersection viewModel = new LinePlainIntersection(null);
        frame.setContentPane(new LinePlaneIntersectionForm(viewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void bind() {
        linePx.setText(viewModel.getLinePx());
        linePy.setText(viewModel.getLinePy());
        lineDirX.setText(viewModel.getLineDirX());
        lineDirY.setText(viewModel.getLineDirY());
        planePointX.setText(viewModel.getPlainPointX());
        planePointY.setText(viewModel.getPlainPointY());
        plainOrtX.setText(viewModel.getPlainOrtX());
        plainOrtY.setText(viewModel.getPlainOrtY());
        resultX.setText(viewModel.getResultX());
        resultY.setText(viewModel.getResultY());
        linePz.setText(viewModel.getLinePz());
        lineDirZ.setText(viewModel.getLineDirZ());
        planePointZ.setText(viewModel.getPlainPointZ());
        planeOrtZ.setText(viewModel.getPlainOrtZ());
        resultZ.setText(viewModel.getResultZ());
        calcButton.setEnabled(viewModel.isCalcButtonEnabled());
    }

    public void backBind() {
        viewModel.setLinePx(linePx.getText());
        viewModel.setLinePy(linePy.getText());
        viewModel.setLineDirX(lineDirX.getText());
        viewModel.setLineDirY(lineDirY.getText());
        viewModel.setPlainPointX(planePointX.getText());
        viewModel.setPlainPointY(planePointY.getText());
        viewModel.setPlainOrtX(plainOrtX.getText());
        viewModel.setPlainOrtY(plainOrtY.getText());
        viewModel.setResultX(resultX.getText());
        viewModel.setResultY(resultY.getText());
        viewModel.setLinePz(linePz.getText());
        viewModel.setLineDirZ(lineDirZ.getText());
        viewModel.setPlainPointZ(planePointZ.getText());
        viewModel.setPlainOrtZ(planeOrtZ.getText());
        viewModel.setResultZ(resultZ.getText());
    }
}
