package ru.unn.agile.Huffman;

import ru.unn.agile.Huffman.Infrastructure.TxtLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HuffmanView {
    private JPanel mainPanel;
    private JButton btnExpand;
    private JButton btnReset;
    private JButton btnCompress;
    private JTextArea txtArea;
    private JLabel lblStatus;
    private JList<String> listLog;
    private JButton btnClearLog;

    private HuffmanViewModel huffmanViewModel; 

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

        btnClearLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                HuffmanView.this.huffmanViewModel.clearLog();
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

        List<String> log = huffmanViewModel.getLog();
        String[] items = new String[log.size()];
        log.toArray(items);
        listLog.setListData(items);
    }

    public static void main(String[] args) {
        TxtLogger txtLogger = new TxtLogger("Huffman.logInfo");

        JFrame frame = new JFrame("HuffmanView");
        frame.setContentPane(new HuffmanView(new HuffmanViewModel(txtLogger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
