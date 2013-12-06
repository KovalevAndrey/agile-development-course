package ru.unn.agile.interpolationSearch.view;

import ru.unn.agile.interpolationSearch.infrastructure.TextLogger;
import ru.unn.agile.interpolationSearch.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 11/8/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Finder {
    private JTextField txtListOfElements;
    private JTextField txtKey;
    private ViewModel viewModel;
    private JPanel mainPanel;
    private JButton searchButton;
    private JLabel lbResult;
    private JLabel lbStatus;
    private JList lstLog;

    public Finder(ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bind();
                Finder.this.viewModel.Search();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                bind();
                Finder.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };

        txtListOfElements.addKeyListener(keyListener);
        txtKey.addKeyListener(keyListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                bind();
                Finder.this.viewModel.focusLost();
                backBind();
            }
        };
        txtListOfElements.addFocusListener(focusLostListener);
        txtKey.addFocusListener(focusLostListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Finder");

        TextLogger textLogger = new TextLogger("./InterpolationSearch.log");
        frame.setContentPane(new Finder(new ViewModel(textLogger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void bind() {
        viewModel.listOfElements = txtListOfElements.getText();
        viewModel.key = txtKey.getText();

        viewModel.keyIndex = lbResult.getText();
        viewModel.status = lbStatus.getText();
    }

    public void backBind() {
        txtListOfElements.setText(viewModel.listOfElements);
        txtKey.setText(viewModel.key);

        lbResult.setText(viewModel.keyIndex);
        lbStatus.setText(viewModel.status);

        searchButton.setEnabled(viewModel.isSearchButtonEnabled);

        lstLog.setListData(viewModel.log.ReadLog());
    }
}
