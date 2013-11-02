package ru.unn.agile.queue;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueForm extends ViewModel{
    private JButton pushButton;
    private JTextField txtPushElement;
    private JButton popButton;
    private JTextField txtPopElement;
    private JButton cleanButton;
    private JPanel mainPanel;
    private JLabel lbStatus;
    private JLabel lbSize;

    public QueueForm()
    {
        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pushActionHandler.onClick();
            }
        });
        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                popActionHandler.onClick();
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cleanActionHandler.onClick();
            }
        });
    }

    @Override
    public void bind() {
        Element = txtPushElement.getText();
        message = lbStatus.getText();
        size = lbSize.getText();
    }

    @Override
    public void unbind() {
        txtPushElement.setText(Element);
        txtPopElement.setText(topElement);
        lbStatus.setText(message);
        lbSize.setText(size);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("QueueForm");
        frame.setContentPane(new QueueForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
