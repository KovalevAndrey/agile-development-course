package ru.unn.agile.Triangle.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ru.unn.agile.Triangle.viewmodel.ViewModel;

public class Triangle
{
    private JPanel mainPanel;
    private JTextField txtPointA1;
    private JTextField txtPointA2;
    private JTextField txtPointB1;
    private JTextField txtPointB2;
    private JTextField txtPointC1;
    private JTextField txtPointC2;
    private JLabel lbStatus;
    private JTextField txtResult;
    private JButton btnCalc;
    private JComboBox <ViewModel.Operation> cbOperation;
    private JLabel lblPointA;
    private JLabel lblPointB;
    private JLabel lblPointC;

    private ViewModel viewModel;
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Triangle");
        frame.setContentPane(new Triangle(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents()
    {}

    public Triangle(ViewModel viewModel)
    {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        btnCalc.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                Triangle.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                bind();
                Triangle.this.viewModel.enterKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        txtPointA1.addKeyListener(keyListener);
        txtPointA2.addKeyListener(keyListener);
        txtPointB1.addKeyListener(keyListener);
        txtPointB2.addKeyListener(keyListener);
        txtPointC1.addKeyListener(keyListener);
        txtPointC2.addKeyListener(keyListener);
    }

    private void loadListOfOperations()
    {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        cbOperation.setModel(new JComboBox<ViewModel.Operation>(operations).getModel());
    }

    public void bind()
    {
        viewModel.pointA1 = txtPointA1.getText();
        viewModel.pointA2 = txtPointA2.getText();
        viewModel.pointB1 = txtPointB1.getText();
        viewModel.pointB2 = txtPointB2.getText();
        viewModel.pointC1 = txtPointC1.getText();
        viewModel.pointC2 = txtPointC2.getText();
        viewModel.operation = (ViewModel.Operation) cbOperation.getSelectedItem();
        viewModel.result = txtResult.getText();
        viewModel.status = lbStatus.getText();
    }

    public void backBind()
    {
        txtPointA1.setText(viewModel.pointA1);
        txtPointA2.setText(viewModel.pointA2);
        txtPointB1.setText(viewModel.pointB1);
        txtPointB2.setText(viewModel.pointB2);
        txtPointC1.setText(viewModel.pointC1);
        txtPointC2.setText(viewModel.pointC2);
        txtResult.setText(viewModel.result);
        lbStatus.setText(viewModel.status);
        btnCalc.setEnabled(viewModel.isCalculateButtonEnabled);
    }
}
