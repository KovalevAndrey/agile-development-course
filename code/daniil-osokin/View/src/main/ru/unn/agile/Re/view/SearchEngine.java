package ru.unn.agile.Re.view;

import ru.unn.agile.Re.viewmodel.ReViewModel;

import javax.swing.*;
import java.awt.event.*;

import static javax.swing.UIManager.setLookAndFeel;

public class SearchEngine
{
    public SearchEngine(ReViewModel viewModel)
    {
        this.viewModel = viewModel;
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bind();
                SearchEngine.this.viewModel.search();
                backBind();
            }
        });
        patternTextField.addKeyListener(new DefaultKeyListener());
        searchTextArea.addKeyListener(new DefaultKeyListener());
    }

    private void bind()
    {
        viewModel.pattern = patternTextField.getText();
        viewModel.text = searchTextArea.getText();
        viewModel.status = statusTextLabel.getText();
    }

    private void backBind()
    {
        patternTextField.setText(viewModel.pattern);
        searchTextArea.setText(viewModel.text);
        statusTextLabel.setText(viewModel.status);
    }

    public static void main(String[] args)
    {
        try
        {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        JFrame frame = new JFrame("SearchEngine");
        frame.setContentPane(new SearchEngine(new ReViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateButtonState()
    {
        if (patternTextField.getText().equals("") || searchTextArea.getText().equals(""))
        {
            if (searchButton.isEnabled())
            {
                searchButton.setEnabled(false);
            }
        }
        else if (!searchButton.isEnabled())
        {
            searchButton.setEnabled(true);
        }
    }

    private class DefaultKeyListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
            updateButtonState();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            updateButtonState();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            updateButtonState();
        }
    }

    private ReViewModel viewModel;

    private JPanel mainPanel;
    private JTextField patternTextField;
    private JButton searchButton;
    private JTextArea searchTextArea;
    private JLabel statusTextLabel;
}
