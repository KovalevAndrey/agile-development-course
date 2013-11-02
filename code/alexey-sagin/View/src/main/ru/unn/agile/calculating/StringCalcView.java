package ru.unn.agile.calculating;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StringCalcView {

	private JFrame frmAsd;
	private JTextField txtExpression;
	private JTextField txtResult;
	private JButton btnCalculate;
	private JLabel lblStatus;
	private StringCalcViewModel viewModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringCalcView window = new StringCalcView(
							new StringCalcViewModel());
					window.frmAsd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StringCalcView(StringCalcViewModel viewModel) {
		this.viewModel = viewModel;
		initialize();
	}

	private void initialize() {
		frmAsd = new JFrame();
		frmAsd.setResizable(false);
		frmAsd.setTitle("String Calculator");
		frmAsd.setBounds(100, 100, 406, 132);
		frmAsd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAsd.getContentPane().setLayout(null);

		txtExpression = new JTextField();
		txtExpression.setBounds(81, 9, 209, 20);
		frmAsd.getContentPane().add(txtExpression);
		txtExpression.setColumns(10);

		JLabel lblExpression = new JLabel("Expression:");
		lblExpression.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExpression.setBounds(10, 8, 61, 20);
		frmAsd.getContentPane().add(lblExpression);

		JLabel lblStatusMark = new JLabel("Status:");
		lblStatusMark.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatusMark.setBounds(10, 70, 39, 20);
		frmAsd.getContentPane().add(lblStatusMark);

		txtResult = new JTextField();
		txtResult.setEditable(false);
		txtResult.setBounds(221, 40, 69, 20);
		frmAsd.getContentPane().add(txtResult);
		txtResult.setColumns(10);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCalculate.setBounds(300, 7, 89, 53);
		frmAsd.getContentPane().add(btnCalculate);

		JLabel lblResultMark = new JLabel("Result:");
		lblResultMark.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResultMark.setBounds(165, 40, 46, 20);
		frmAsd.getContentPane().add(lblResultMark);

		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(59, 70, 330, 20);
		frmAsd.getContentPane().add(lblStatus);

		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bind();
				viewModel.processCalculate();
				unbind();
			}

		});
	}

	private void bind() {
		viewModel.status = lblStatus.getText();
		viewModel.expression = txtExpression.getText();
		viewModel.result = txtResult.getText();
	}

	private void unbind() {
		lblStatus.setText(viewModel.status);
		txtExpression.setText(viewModel.expression);
		txtResult.setText(viewModel.result);
	}
}
