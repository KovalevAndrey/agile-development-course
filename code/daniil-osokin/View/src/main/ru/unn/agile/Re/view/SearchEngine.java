package ru.unn.agile.Re.view;

import javax.swing.*;

import static javax.swing.UIManager.setLookAndFeel;

public class SearchEngine
{
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
        frame.setContentPane(new SearchEngine().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JPanel searchPanel;
    private JPanel textPanel;
    private JPanel statusPanel;
    private JLabel regexLabel;
    private JTextField regexTextField;
    private JButton searchButton;
    private JLabel textLabel;
    private JTextArea searchTextArea;
    private JLabel statusLabel;
    private JLabel statusTextLabel;
}
