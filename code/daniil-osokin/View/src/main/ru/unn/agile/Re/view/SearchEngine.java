package ru.unn.agile.Re.view;

import ru.unn.agile.Re.infrastructure.TxtLogger;
import ru.unn.agile.Re.viewmodel.ILogger;
import ru.unn.agile.Re.viewmodel.LogEntry;
import ru.unn.agile.Re.viewmodel.RegexViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
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
                bind();
                super.focusLost(e);
                JTextComponent component = (JTextComponent)e.getSource();
                viewModel.focusLost(component.getClass().getName(), component.getText());
                backBind();
            }
        };
        patternTextField.addFocusListener(defaultTextFocusAdapter);
        searchTextArea.addFocusListener(defaultTextFocusAdapter);

        logTableModel = new LogTableModel();
        logTable.setModel(logTableModel);
        logTable.setDefaultRenderer(Object.class, new LogTableRowRenderer());
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
        logTableModel.updateData(viewModel.getLog().get(viewModel.getLog().size()-1));
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
        frame.setContentPane(new SearchEngine(new RegexViewModel(new TxtLogger("searchEngine.log"))).mainPanel);
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

    private class LogTableModel extends DefaultTableModel
    {
        String[] columnNames = {"Type", "Tag", "Text", "Date"};

        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int index)
        {
            return columnNames[index];
        }

        public void updateData(LogEntry logEntry)
        {
            int firstRow = getRowCount();
            addRow(viewModel.getLogTableRow(logEntry));
            int lastRow = getRowCount() - 1;

            fireTableRowsInserted(firstRow, lastRow);
        }
    }

    private class LogTableRowRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String logType = (String) table.getValueAt(row, 0);

            if (ILogger.INFO.equals(logType))
            {
                setBackground(Color.LIGHT_GRAY);
            }
            else if (ILogger.WARN.equals(logType))
            {
                setBackground(Color.YELLOW);
            }
            else if (ILogger.ERROR.equals(logType))
            {
                setBackground(Color.MAGENTA);
            }

            return this;
        }
    }

    private RegexViewModel viewModel;
    private LogTableModel logTableModel;

    private JPanel mainPanel;
    private JTextField patternTextField;
    private JButton searchButton;
    private JTextArea searchTextArea;
    private JLabel statusTextLabel;
    private JTable logTable;
    private JScrollPane logTableScrollPane;
}
