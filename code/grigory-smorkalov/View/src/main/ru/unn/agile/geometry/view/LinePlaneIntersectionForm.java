package ru.unn.agile.geometry.view;

import ru.unn.agile.geometry.viewModel.LinePlaneIntersection;

import javax.swing.*;
import java.awt.event.*;

import ru.unn.agile.geometry.TextLogger;

public class LinePlaneIntersectionForm {
    private JButton calcButton;
    private JTextField linePx;
    private JTextField linePy;
    private JTextField lineDirX;
    private JTextField lineDirY;
    private JTextField planePointX;
    private JTextField planePointY;
    private JTextField planeOrtX;
    private JTextField planeOrtY;
    private JTextField resultX;
    private JTextField resultY;
    private JPanel mainPanel;
    private JTextField linePz;
    private JTextField lineDirZ;
    private JTextField planePointZ;
    private JTextField planeOrtZ;
    private JTextField resultZ;
    private JTextArea log;

    private LinePlaneIntersection viewModel;

    public LinePlaneIntersectionForm(LinePlaneIntersection formVewModel) {
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
        JTextField textFields[] = {linePx, linePy, lineDirX, lineDirY, planePointX, planePointY, planeOrtX, planeOrtY, linePz, lineDirZ, planePointZ, planeOrtZ};
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
        LinePlaneIntersection viewModel = new LinePlaneIntersection(new TextLogger("textLogger.log"));
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
        planePointX.setText(viewModel.getPlanePointX());
        planePointY.setText(viewModel.getPlanePointY());
        planeOrtX.setText(viewModel.getPlaneOrtX());
        planeOrtY.setText(viewModel.getPlaneOrtY());
        resultX.setText(viewModel.getResultX());
        resultY.setText(viewModel.getResultY());
        linePz.setText(viewModel.getLinePz());
        lineDirZ.setText(viewModel.getLineDirZ());
        planePointZ.setText(viewModel.getPlanePointZ());
        planeOrtZ.setText(viewModel.getPlaneOrtZ());
        resultZ.setText(viewModel.getResultZ());
        calcButton.setEnabled(viewModel.isCalcButtonEnabled());
        log.setText(viewModel.getLog());
    }

    public void backBind() {
        viewModel.setLinePx(linePx.getText());
        viewModel.setLinePy(linePy.getText());
        viewModel.setLineDirX(lineDirX.getText());
        viewModel.setLineDirY(lineDirY.getText());
        viewModel.setPlanePointX(planePointX.getText());
        viewModel.setPlanePointY(planePointY.getText());
        viewModel.setPlaneOrtX(planeOrtX.getText());
        viewModel.setPlaneOrtY(planeOrtY.getText());
        viewModel.setResultX(resultX.getText());
        viewModel.setResultY(resultY.getText());
        viewModel.setLinePz(linePz.getText());
        viewModel.setLineDirZ(lineDirZ.getText());
        viewModel.setPlanePointZ(planePointZ.getText());
        viewModel.setPlaneOrtZ(planeOrtZ.getText());
        viewModel.setResultZ(resultZ.getText());
    }
}
