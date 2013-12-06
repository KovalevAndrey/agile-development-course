package ru.unn.agile.calculating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StringCalcView {

    private JFrame mainFrame;
    private JTextField txtExpression;
    private JTextField txtResult;
    private JButton btnCalculate;
    private JLabel lblStatus;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private StringCalcViewModel viewModel;

    public StringCalcView(StringCalcViewModel viewModel) {
        this.viewModel = viewModel;
        initialize();
        backBind();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StringCalcView window = new StringCalcView(
                            new StringCalcViewModel(new FileLogger("./program.log")));
                    window.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setTitle("String Calculator");
        mainFrame.setBounds(100, 100, 406, 251);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        txtExpression = new JTextField();
        txtExpression.setBounds(81, 9, 209, 20);
        mainFrame.getContentPane().add(txtExpression);
        txtExpression.setColumns(10);

        JLabel lblExpression = new JLabel("Expression:");
        lblExpression.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblExpression.setBounds(10, 8, 61, 20);
        mainFrame.getContentPane().add(lblExpression);

        JLabel lblStatusMark = new JLabel("Status:");
        lblStatusMark.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblStatusMark.setBounds(10, 70, 39, 20);
        mainFrame.getContentPane().add(lblStatusMark);

        txtResult = new JTextField();
        txtResult.setEditable(false);
        txtResult.setBounds(221, 40, 69, 20);
        mainFrame.getContentPane().add(txtResult);
        txtResult.setColumns(10);

        btnCalculate = new JButton("Calculate");
        btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCalculate.setBounds(300, 7, 89, 53);
        mainFrame.getContentPane().add(btnCalculate);

        JLabel lblResultMark = new JLabel("Result:");
        lblResultMark.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblResultMark.setBounds(165, 40, 46, 20);
        mainFrame.getContentPane().add(lblResultMark);

        lblStatus = new JLabel("");
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblStatus.setBounds(59, 70, 330, 20);
        mainFrame.getContentPane().add(lblStatus);

        textArea = new JTextArea();
        textArea.setAutoscrolls(true);
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 101, 380, 107);
        scrollPane.setAutoscrolls(true);
        mainFrame.getContentPane().add(scrollPane);

        btnCalculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                bind();
                viewModel.processCalculate();
                backBind();
            }

        });
    }

    private void bind() {
        viewModel.status = lblStatus.getText();
        viewModel.expression = txtExpression.getText();
        viewModel.result = txtResult.getText();
    }

    private void backBind() {
        lblStatus.setText(viewModel.status);
        txtExpression.setText(viewModel.expression);
        txtResult.setText(viewModel.result);
        textArea.setText("");
        List<String> log = viewModel.getLog().getAllMessages();
        for (int i = 0; i < log.size(); i++) {
            textArea.append(log.get(i) + "\n");
        }
    }
}
