package ru.unn.agile.view;

import javax.swing.*;

public class QuadEquationSolver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("QuadEquationSolver");
        frame.setContentPane(new QuadEquationSolver().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JPanel dataPanel;
    private JPanel resultPanel;
    private JLabel eqLabel;
    private JTextField TextFieldA;
    private JLabel labelA;
    private JLabel labelB;
    private JLabel labelC;
    private JTextField TextFieldB;
    private JTextField TextFieldC;
    private JLabel coeffLabel;
    private JLabel theSolutionIsLabel;
    private JLabel solutionLabel;
    private JButton solveButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
