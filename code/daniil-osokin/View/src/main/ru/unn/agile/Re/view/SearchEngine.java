package ru.unn.agile.Re.view;

import ru.unn.agile.Re.viewmodel.MockLogger;
import ru.unn.agile.Re.viewmodel.RegexViewModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;

import static javax.swing.UIManager.setLookAndFeel;

public class SearchEngine
{
    public SearchEngine(final RegexViewModel viewModel)
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

        KeyAdapter defaultKeyListener = new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                super.keyReleased(e);
                updateButtonState();
            }
        };
        patternTextField.addKeyListener(defaultKeyListener);
        searchTextArea.addKeyListener(defaultKeyListener);

        FocusAdapter defaultTextFocusAdapter = new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                super.focusLost(e);
                JTextComponent component = (JTextComponent)e.getSource();
                viewModel.focusLost(component.getClass().getName(), component.getText());
            }
        };
        patternTextField.addFocusListener(defaultTextFocusAdapter);
        searchTextArea.addFocusListener(defaultTextFocusAdapter);
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
        frame.setContentPane(new SearchEngine(new RegexViewModel(new MockLogger())).mainPanel);
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

    private RegexViewModel viewModel;

    private JPanel mainPanel;
    private JTextField patternTextField;
    private JButton searchButton;
    private JTextArea searchTextArea;
    private JLabel statusTextLabel;
}
