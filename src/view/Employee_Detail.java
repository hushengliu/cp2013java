package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.New_employee;

public class Employee_Detail extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton btnBack;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Employee_Detail frame = new Employee_Detail();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	//JPanel getJPanel()
	public JPanel getJPanel() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 484, 400);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String name=New_employee.textField.getText();
		String address=New_employee.textField_1.getText();
		String payment=(String) New_employee.comboBox1.getSelectedItem();
		String E_ID=New_employee.textField_3.getText();
		String U_ID=New_employee.textField_4.getText();
		String working_mode=(String) New_employee.type;
		
		JLabel lblNewLabel = new JLabel("Congratulation");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(270, 27, 151, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("you are successes enroll to system");
		lblNewLabel_1.setBounds(237, 75, 220, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(255, 123, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(237, 170, 54, 15);
		contentPane.add(lblAddress);
		
		JLabel lblMethodOfPayment = new JLabel("Method of Payment:");
		lblMethodOfPayment.setBounds(177, 219, 108, 15);
		contentPane.add(lblMethodOfPayment);
		
		JLabel lblEmployeeId = new JLabel("Employee Id:");
		lblEmployeeId.setBounds(209, 259, 78, 25);
		contentPane.add(lblEmployeeId);
		
		JLabel lblUnionId = new JLabel("Union Id:");
		lblUnionId.setBounds(237, 317, 63, 15);
		contentPane.add(lblUnionId);
		
		JLabel lblWorkingMode = new JLabel("Working mode:");
		lblWorkingMode.setBounds(213, 364, 78, 15);
		contentPane.add(lblWorkingMode);
		
		JLabel lblNewLabel_3 = new JLabel(name);
		lblNewLabel_3.setBounds(367, 123, 113, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(address);
		lblNewLabel_4.setBounds(367, 170, 90, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(payment);
		lblNewLabel_5.setBounds(367, 219, 90, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(E_ID);
		lblNewLabel_6.setBounds(367, 264, 90, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(U_ID);
		lblNewLabel_7.setBounds(367, 317, 90, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(working_mode);
		lblNewLabel_8.setBounds(367, 365, 90, 15);
		contentPane.add(lblNewLabel_8);
		
		btnBack = new JButton("back");
		btnBack.addActionListener(this);
		btnBack.setBounds(550, 428, 78, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
	
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnBack){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
			
		}
	}

}
