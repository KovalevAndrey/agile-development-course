package ru.unn.agile.Huffman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HuffmanView {
    private JPanel mainPanel;
    private JButton btnExpand;
    private JButton btnReset;
    private JButton btnCompress;
    private JTextArea txtArea;
    private JLabel lblStatus;

    private HuffmanViewModel huffmanViewModel; 

    public static void main(String[] args) {
        JFrame frame = new JFrame("HuffmanView");
        frame.setContentPane(new HuffmanView(new HuffmanViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public HuffmanView(HuffmanViewModel huffmanViewModel) {
        this.huffmanViewModel = huffmanViewModel;
        backBind();

        btnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                HuffmanView.this.huffmanViewModel.compress();
                backBind();
            }
        });

        btnExpand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                HuffmanView.this.huffmanViewModel.expand();
                backBind();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                HuffmanView.this.huffmanViewModel.reset();
                backBind();
            }
        });

    }


    private void bind() {
        huffmanViewModel.status = lblStatus.getText();
        huffmanViewModel.text=txtArea.getText();
        huffmanViewModel.isEditableTextArea =txtArea.isEnabled();
        huffmanViewModel.isActiveCompressButton=btnCompress.isEnabled();
        huffmanViewModel.isActiveEncodingButton=btnExpand.isEnabled();
        huffmanViewModel.isEditableTextArea=txtArea.isEditable();
    }

    private void backBind() {
        lblStatus.setText(huffmanViewModel.status);
        txtArea.setText(huffmanViewModel.text);
        btnCompress.setEnabled(huffmanViewModel.isActiveCompressButton);
        btnExpand.setEnabled(huffmanViewModel.isActiveEncodingButton);
        txtArea.setEditable(huffmanViewModel.isEditableTextArea);
        txtArea.setEditable(huffmanViewModel.isEditableTextArea);
    }
}
