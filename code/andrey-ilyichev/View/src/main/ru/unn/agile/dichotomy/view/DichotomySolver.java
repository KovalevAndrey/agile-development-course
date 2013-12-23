package ru.unn.agile.dichotomy.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ru.unn.agile.dichotomy.infrastructure.TxtLogger;
import ru.unn.agile.dichotomy.viewmodel.ViewModel;

import javax.swing.JList;
import javax.swing.JScrollPane;


public class DichotomySolver {
	private ViewModel viewModel;

	private JFrame frmDichotomySolver;
	private JTextField textFieldB;
	private JTextField textFieldEps;
	private JTextField textFieldA;
	private JTextField textFieldSigma;
	private JTextField textFieldResult;
	private JComboBox<ViewModel.Function> comboBoxFunction;
	private JButton btnGetResult;
	private JList<String> jListLog;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DichotomySolver window = new DichotomySolver(new ViewModel(new TxtLogger("log.txt")));
					window.frmDichotomySolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DichotomySolver(ViewModel viewModel) {
		initialize();
		this.viewModel = viewModel;
		loadListOfFunction();
		
		btnGetResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				bind();
				DichotomySolver.this.viewModel.getResult();
				backBind();
			}
		});
	}

	private void loadListOfFunction() {
		ViewModel.Function[] functions = ViewModel.Function.values();
		comboBoxFunction.setModel(new JComboBox<ViewModel.Function>(functions).getModel());
	}

	private void initialize() {
		frmDichotomySolver = new JFrame();
		frmDichotomySolver.setResizable(false);
		frmDichotomySolver.setTitle("Dichotomy Solver");
		frmDichotomySolver.setBounds(100, 100, 512, 330);
		frmDichotomySolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{500, 0};
		gridBagLayout.rowHeights = new int[]{151, 145, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frmDichotomySolver.getContentPane().setLayout(gridBagLayout);
		
		JPanel panelMain = new JPanel();
		GridBagConstraints gbc_panelMain = new GridBagConstraints();
		gbc_panelMain.gridheight = 2;
		gbc_panelMain.insets = new Insets(0, 0, 5, 0);
		gbc_panelMain.fill = GridBagConstraints.BOTH;
		gbc_panelMain.gridx = 0;
		gbc_panelMain.gridy = 0;
		frmDichotomySolver.getContentPane().add(panelMain, gbc_panelMain);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[]{265, 0};
		gbl_panelMain.rowHeights = new int[]{27, 56, 165, 0};
		gbl_panelMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMain.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMain.setLayout(gbl_panelMain);
		
		JPanel panelInput = new JPanel();
		GridBagConstraints gbc_panelInput = new GridBagConstraints();
		gbc_panelInput.fill = GridBagConstraints.BOTH;
		gbc_panelInput.insets = new Insets(0, 0, 5, 0);
		gbc_panelInput.gridx = 0;
		gbc_panelInput.gridy = 0;
		panelMain.add(panelInput, gbc_panelInput);
		GridBagLayout gbl_panelInput = new GridBagLayout();
		gbl_panelInput.columnWidths = new int[]{30, 0, 0, 30, 30, 0, 0, 0, 0, 0, 0};
		gbl_panelInput.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelInput.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInput.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInput.setLayout(gbl_panelInput);
		
		JLabel lblA = new JLabel("a =");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.anchor = GridBagConstraints.EAST;
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.gridx = 1;
		gbc_lblA.gridy = 0;
		panelInput.add(lblA, gbc_lblA);
		
		textFieldA = new JTextField();
		GridBagConstraints gbc_textFieldA = new GridBagConstraints();
		gbc_textFieldA.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldA.gridx = 2;
		gbc_textFieldA.gridy = 0;
		panelInput.add(textFieldA, gbc_textFieldA);
		textFieldA.setColumns(10);
		
		JLabel lblB = new JLabel("b =");
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.anchor = GridBagConstraints.EAST;
		gbc_lblB.insets = new Insets(0, 0, 5, 5);
		gbc_lblB.gridx = 4;
		gbc_lblB.gridy = 0;
		panelInput.add(lblB, gbc_lblB);
		
		textFieldB = new JTextField();
		GridBagConstraints gbc_textFieldB = new GridBagConstraints();
		gbc_textFieldB.gridwidth = 3;
		gbc_textFieldB.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldB.gridx = 5;
		gbc_textFieldB.gridy = 0;
		panelInput.add(textFieldB, gbc_textFieldB);
		textFieldB.setColumns(10);
		
		JLabel lblSigma = new JLabel("sigma =");
		GridBagConstraints gbc_lblSigma = new GridBagConstraints();
		gbc_lblSigma.anchor = GridBagConstraints.EAST;
		gbc_lblSigma.insets = new Insets(0, 0, 5, 5);
		gbc_lblSigma.gridx = 1;
		gbc_lblSigma.gridy = 1;
		panelInput.add(lblSigma, gbc_lblSigma);
		
		textFieldSigma = new JTextField();
		GridBagConstraints gbc_textFieldSigma = new GridBagConstraints();
		gbc_textFieldSigma.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSigma.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSigma.gridx = 2;
		gbc_textFieldSigma.gridy = 1;
		panelInput.add(textFieldSigma, gbc_textFieldSigma);
		textFieldSigma.setColumns(10);
		
		JLabel lblEps = new JLabel("eps =");
		GridBagConstraints gbc_lblEps = new GridBagConstraints();
		gbc_lblEps.anchor = GridBagConstraints.EAST;
		gbc_lblEps.insets = new Insets(0, 0, 5, 5);
		gbc_lblEps.gridx = 4;
		gbc_lblEps.gridy = 1;
		panelInput.add(lblEps, gbc_lblEps);
		
		textFieldEps = new JTextField();
		GridBagConstraints gbc_textFieldEps = new GridBagConstraints();
		gbc_textFieldEps.gridwidth = 3;
		gbc_textFieldEps.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEps.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEps.gridx = 5;
		gbc_textFieldEps.gridy = 1;
		panelInput.add(textFieldEps, gbc_textFieldEps);
		textFieldEps.setColumns(10);
		
		JLabel lblFunction = new JLabel("Function:");
		GridBagConstraints gbc_lblFunction = new GridBagConstraints();
		gbc_lblFunction.anchor = GridBagConstraints.EAST;
		gbc_lblFunction.insets = new Insets(0, 0, 0, 5);
		gbc_lblFunction.gridx = 1;
		gbc_lblFunction.gridy = 2;
		panelInput.add(lblFunction, gbc_lblFunction);
		
		comboBoxFunction = new JComboBox<ViewModel.Function>();
		GridBagConstraints gbc_comboBoxFunction = new GridBagConstraints();
		gbc_comboBoxFunction.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFunction.gridwidth = 5;
		gbc_comboBoxFunction.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFunction.gridx = 2;
		gbc_comboBoxFunction.gridy = 2;
		panelInput.add(comboBoxFunction, gbc_comboBoxFunction);
		
		JPanel panelResult = new JPanel();
		GridBagConstraints gbc_panelResult = new GridBagConstraints();
		gbc_panelResult.gridheight = 2;
		gbc_panelResult.insets = new Insets(0, 0, 5, 0);
		gbc_panelResult.fill = GridBagConstraints.BOTH;
		gbc_panelResult.gridx = 0;
		gbc_panelResult.gridy = 1;
		panelMain.add(panelResult, gbc_panelResult);
		GridBagLayout gbl_panelResult = new GridBagLayout();
		gbl_panelResult.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelResult.rowHeights = new int[]{0, 0, 141, 0};
		gbl_panelResult.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelResult.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelResult.setLayout(gbl_panelResult);
		
		btnGetResult = new JButton("Get Result");
		GridBagConstraints gbc_btnGetResult = new GridBagConstraints();
		gbc_btnGetResult.gridwidth = 7;
		gbc_btnGetResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGetResult.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetResult.gridx = 1;
		gbc_btnGetResult.gridy = 0;
		panelResult.add(btnGetResult, gbc_btnGetResult);
		
		textFieldResult = new JTextField();
		GridBagConstraints gbc_textFieldResult = new GridBagConstraints();
		gbc_textFieldResult.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldResult.gridwidth = 7;
		gbc_textFieldResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldResult.gridx = 1;
		gbc_textFieldResult.gridy = 1;
		panelResult.add(textFieldResult, gbc_textFieldResult);
		textFieldResult.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		panelResult.add(scrollPane, gbc_scrollPane);
		
		jListLog = new JList<String>();
		scrollPane.setColumnHeaderView(jListLog);
	}

	public void bind(){
		this.viewModel.setA(textFieldA.getText());
		this.viewModel.setB(textFieldB.getText());
		this.viewModel.setEps(textFieldEps.getText());
		this.viewModel.setSigma(textFieldSigma.getText());
		this.viewModel.setFunction((ViewModel.Function)comboBoxFunction.getSelectedItem());
	}
	
	public void backBind(){
		textFieldResult.setText(this.viewModel.getResultMessage());
		List<String> listLog = viewModel.getLog();
		String[] log = listLog.toArray(new String[listLog.size()]);
		jListLog.setListData(log);
	}
}
