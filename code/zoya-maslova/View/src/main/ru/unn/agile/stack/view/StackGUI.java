package ru.unn.agile.stack.view;

import javax.swing.*;
import ru.unn.agile.stack.viewmodel.ViewModelStack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackGUI
{
    private JTextField txtInputString;
    private JButton pushButton;
    private JButton popButton;
    private JButton topButton;
    private JLabel lbStatus;
    private JLabel lbTopElement;
    private JPanel mainPanel;

    private ViewModelStack viewModelStack;

    public StackGUI(ViewModelStack viewModelStack)
    {
        popButton.setSize(250,100);
        topButton.setSize(250,100);

        this.viewModelStack = viewModelStack;

        backBind();

        pushButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.pushActionHandler.onClick();
                backBind();
            }
        });

        popButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.popActionHandler.onClick();
                backBind();
            }
        });

        topButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.topActionHandler.onClick();
                backBind();
            }
        });
    }

    public void bind()
    {
        viewModelStack.status = lbStatus.getText();
        viewModelStack.input = txtInputString.getText();
        viewModelStack.topElement= lbTopElement.getText();
    }

    public void backBind()
    {
        lbStatus.setText(viewModelStack.status);
        txtInputString.setText(viewModelStack.input);
        lbTopElement.setText(viewModelStack.topElement);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("StackGUI");
        frame.setContentPane(new StackGUI(new ViewModelStack()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
