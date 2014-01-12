package ru.unn.agile.geometry.view;

import ru.unn.agile.geometry.viewModel.LinePlainIntersection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    private LinePlainIntersection viewModel;

    public LinePlainIntersectionForm() {
        viewModel = new LinePlainIntersection();

        linePx.addKeyListener(new KeyAdapter() {
        });
        calcButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LinePlainIntersectionForm.this.getData(viewModel);
                LinePlainIntersectionForm.this.viewModel.calc();
                LinePlainIntersectionForm.this.setData(viewModel);
            }
        });
        JTextField textFields[] = {linePx, linePy, lineDirX, lineDirY, plainPointX, plainPointY, plainOrtX, plainOrtY, linePz, lineDirZ, plainPointZ, plainOrtZ};
        for(JTextField textField : textFields) {
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyTyped(e);
                    LinePlainIntersectionForm.this.getData(LinePlainIntersectionForm.this.viewModel);
                    LinePlainIntersectionForm.this.viewModel.inputSomething();
                    calcButton.setEnabled(LinePlainIntersectionForm.this.viewModel.isCalcButtonEnabled);
                }
            });
        }
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

    public void setData(LinePlainIntersection data) {
        linePx.setText(data.getLinePx());
        linePy.setText(data.getLinePy());
        lineDirX.setText(data.getLineDirX());
        lineDirY.setText(data.getLineDirY());
        plainPointX.setText(data.getPlainPointX());
        plainPointY.setText(data.getPlainPointY());
        plainOrtX.setText(data.getPlainOrtX());
        plainOrtY.setText(data.getPlainOrtY());
        resultX.setText(data.getResultX());
        resultY.setText(data.getResultY());
        linePz.setText(data.getLinePz());
        lineDirZ.setText(data.getLineDirZ());
        plainPointZ.setText(data.getPlainPointZ());
        plainOrtZ.setText(data.getPlainOrtZ());
        resultZ.setText(data.getResultZ());
        calcButton.setEnabled(data.isCalcButtonEnabled);
    }

    public void getData(LinePlainIntersection data) {
        data.setLinePx(linePx.getText());
        data.setLinePy(linePy.getText());
        data.setLineDirX(lineDirX.getText());
        data.setLineDirY(lineDirY.getText());
        data.setPlainPointX(plainPointX.getText());
        data.setPlainPointY(plainPointY.getText());
        data.setPlainOrtX(plainOrtX.getText());
        data.setPlainOrtY(plainOrtY.getText());
        data.setResultX(resultX.getText());
        data.setResultY(resultY.getText());
        data.setLinePz(linePz.getText());
        data.setLineDirZ(lineDirZ.getText());
        data.setPlainPointZ(plainPointZ.getText());
        data.setPlainOrtZ(plainOrtZ.getText());
        data.setResultZ(resultZ.getText());
    }

    public boolean isModified(LinePlainIntersection data) {
        if (linePx.getText() != null ? !linePx.getText().equals(data.getLinePx()) : data.getLinePx() != null)
            return true;
        if (linePy.getText() != null ? !linePy.getText().equals(data.getLinePy()) : data.getLinePy() != null)
            return true;
        if (lineDirX.getText() != null ? !lineDirX.getText().equals(data.getLineDirX()) : data.getLineDirX() != null)
            return true;
        if (lineDirY.getText() != null ? !lineDirY.getText().equals(data.getLineDirY()) : data.getLineDirY() != null)
            return true;
        if (plainPointX.getText() != null ? !plainPointX.getText().equals(data.getPlainPointX()) : data.getPlainPointX() != null)
            return true;
        if (plainPointY.getText() != null ? !plainPointY.getText().equals(data.getPlainPointY()) : data.getPlainPointY() != null)
            return true;
        if (plainOrtX.getText() != null ? !plainOrtX.getText().equals(data.getPlainOrtX()) : data.getPlainOrtX() != null)
            return true;
        if (plainOrtY.getText() != null ? !plainOrtY.getText().equals(data.getPlainOrtY()) : data.getPlainOrtY() != null)
            return true;
        if (resultX.getText() != null ? !resultX.getText().equals(data.getResultX()) : data.getResultX() != null)
            return true;
        if (resultY.getText() != null ? !resultY.getText().equals(data.getResultY()) : data.getResultY() != null)
            return true;
        if (linePz.getText() != null ? !linePz.getText().equals(data.getLinePz()) : data.getLinePz() != null)
            return true;
        if (lineDirZ.getText() != null ? !lineDirZ.getText().equals(data.getLineDirZ()) : data.getLineDirZ() != null)
            return true;
        if (plainPointZ.getText() != null ? !plainPointZ.getText().equals(data.getPlainPointZ()) : data.getPlainPointZ() != null)
            return true;
        if (plainOrtZ.getText() != null ? !plainOrtZ.getText().equals(data.getPlainOrtZ()) : data.getPlainOrtZ() != null)
            return true;
        if (resultZ.getText() != null ? !resultZ.getText().equals(data.getResultZ()) : data.getResultZ() != null)
            return true;
        return false;
    }
}
