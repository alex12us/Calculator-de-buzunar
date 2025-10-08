package ro.usv;

import java.awt.EventQueue;
import ro.usv.Evaluator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField display;
	private StringBuilder expresia=new StringBuilder();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
					frame.setTitle("Calculator de buzunar");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		display = new JTextField();
		display.setBounds(44, 24, 188, 40);
		contentPane.add(display);
		display.setColumns(10);
		
		JButton btnNumber1 = new JButton("1");
		btnNumber1.setBounds(44, 74, 40, 40);
		btnNumber1.addActionListener(e->appendNumber("1"));
		contentPane.add(btnNumber1);
		
		JButton btnNumber2 = new JButton("2");
		btnNumber2.setBounds(94, 74, 40, 40);
		btnNumber2.addActionListener(e->appendNumber("2"));
		contentPane.add(btnNumber2);
		
		JButton btnNumber3 = new JButton("3");
		btnNumber3.addActionListener(e->appendNumber("3"));
		btnNumber3.setBounds(141, 74, 40, 40);
		contentPane.add(btnNumber3);
		
		JButton btnNumber4 = new JButton("4");
		btnNumber4.setBounds(188, 74, 40, 40);
		btnNumber4.addActionListener(e->appendNumber("4"));
		contentPane.add(btnNumber4);
		
		JButton btnNumber5 = new JButton("5");
		btnNumber5.setBounds(44, 124, 40, 40);
		btnNumber5.addActionListener(e->appendNumber("5"));
		contentPane.add(btnNumber5);
		
		JButton btnNumber6 = new JButton("6");
		btnNumber6.setBounds(94, 124, 40, 40);
		btnNumber6.addActionListener(e->appendNumber("6"));
		contentPane.add(btnNumber6);
		
		JButton btnNumber7 = new JButton("7");
		btnNumber7.setBounds(141, 124, 40, 40);
		btnNumber7.addActionListener(e->appendNumber("7"));
		contentPane.add(btnNumber7);
		
		JButton btnNumber8 = new JButton("8");
		btnNumber8.setBounds(188, 124, 40, 40);
		btnNumber8.addActionListener(e->appendNumber("8"));
		contentPane.add(btnNumber8);
		
		JButton btnNumber9 = new JButton("9");
		btnNumber9.setBounds(44, 174, 40, 40);
		btnNumber9.addActionListener(e->appendNumber("9"));
		contentPane.add(btnNumber9);
		
		JButton btnNumber0 = new JButton("0");
		btnNumber0.setBounds(94, 174, 40, 40);
		btnNumber0.addActionListener(e->appendNumber("0"));
		contentPane.add(btnNumber0);
		
		JButton btnOpSubstract = new JButton("-");
		btnOpSubstract.setBounds(188, 174, 41, 41);
		btnOpSubstract.addActionListener(e->appendOperator("-"));
		contentPane.add(btnOpSubstract);
		
		JButton btnOpMult = new JButton("*");
		btnOpMult.setBounds(44, 224, 41, 41);
		btnOpMult.addActionListener(e->appendOperator("*"));
		contentPane.add(btnOpMult);
		
		JButton btnOpAdd = new JButton("+");
		btnOpAdd.setBounds(141, 174, 41, 41);
		btnOpAdd.addActionListener(e->appendOperator("+"));
		contentPane.add(btnOpAdd);
		
		JButton btnOpDivide = new JButton("/");
		btnOpDivide.setBounds(94, 224, 41, 41);
		btnOpDivide.addActionListener(e->appendOperator("/"));
		contentPane.add(btnOpDivide);
		
		JButton btnClear = new JButton("C");
		btnClear.setFont(new Font("Dialog", Font.BOLD, 10));
		btnClear.setBounds(140, 224, 41, 41);
		btnClear.addActionListener(e->clearDisplay());
		contentPane.add(btnClear);
		
		JButton btnEqual = new JButton("=");
		btnEqual.setBounds(188, 224, 41, 41);
		btnEqual.addActionListener(e->calculate());
		contentPane.add(btnEqual);

	}
	//Adugarea unui numar
	private void appendNumber(String text) {
		expresia.append(text);
		display.setText(expresia.toString());
	}
	//Stergerea totala a rezultatului
	private void clearDisplay() {
		expresia.setLength(0);
		display.setText("0");
		
	}
//adaugarea operatorilor
private void appendOperator(String op) {
	
	char last = expresia.charAt(expresia.length() - 1);
	if ("+-*/".indexOf(last) != -1)
		expresia.setCharAt(expresia.length() - 1, op.charAt(0));
	else
		expresia.append(op);
	display.setText(expresia.toString());
}
///Caluclarea rezultatului prin tratarea exceptiilor
private void calculate() {
	try {
		double result = Evaluator.Evaluare(expresia.toString());
		String text = (result == (long) result) ? String.valueOf((long) result) : String.format("%.2f",result);
		display.setText(text);
		expresia.setLength(0);
		expresia.append(text);
	} catch (Exception ex) {
		display.setText("Error");
		expresia.setLength(0);
	}
}
}
