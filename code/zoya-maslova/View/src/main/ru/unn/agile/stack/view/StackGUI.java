package ru.unn.agile.stack.view;

import javax.swing.*;

import ru.unn.agile.stack.TxtStackLogger;
import ru.unn.agile.stack.viewmodel.ViewModelStack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StackGUI
{
    private JTextField txtInputString;
    private JButton pushButton;
    private JButton popButton;
    private JButton topButton;
    private JLabel lbStatus;
    private JLabel lbTopElement;
    private JPanel mainPanel;
    private JList<String> lstLog;

    private ViewModelStack viewModelStack;

    public StackGUI(ViewModelStack viewModelStack)
    {
        this.viewModelStack = viewModelStack;

        backBind();

        pushButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.pushPushAction();
                backBind();
            }
        });

        popButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.popPushAction();
                backBind();
            }
        });

        topButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                bind();
                StackGUI.this.viewModelStack.topPushAction();
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
        List<String> log = viewModelStack.getLog();
        String[] items = log.toArray(new String[log.size()]);
        lstLog.setListData(items);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("StackGUI");
        TxtStackLogger logger = new TxtStackLogger("StackLog.log");
        frame.setContentPane(new StackGUI(new ViewModelStack(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
