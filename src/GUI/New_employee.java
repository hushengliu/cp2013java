package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Employee_Detail;
import view.Main;
import model.Account;
import model.Employee;

import java.awt.Color;

public class New_employee extends JFrame implements ActionListener,ItemListener {
	

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JComboBox comboBox,comboBox1;
	JButton btnSubmit;
	String payment="email";
	public static String type="Hourly";
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	IEmployeeService emse;
	private JTextField account;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] sd){
//		New_employee e=new New_employee();
//		e.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	// JPanel getJPanel
	public JPanel getJPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewEm = new JLabel("New Employee Detail");
		lblNewEm.setBounds(289, 33, 140, 53);
		contentPane.add(lblNewEm);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(155, 80, 83, 25);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(134, 131, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Method of Payment:");
		lblNewLabel_1.setBounds(78, 170, 110, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setBounds(104, 219, 89, 15);
		contentPane.add(lblEmployeeId);
		
		JLabel lblU = new JLabel("Union ID:");
		lblU.setBounds(128, 244, 54, 27);
		contentPane.add(lblU);
		
		JLabel lblWorkingMet = new JLabel("working mode:");
		lblWorkingMet.setBounds(104, 281, 89, 25);
		contentPane.add(lblWorkingMet);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(462, 216, 83, 18);
		lblNewLabel_2.setForeground(Color.RED);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(462, 250, 83, 21);
		lblNewLabel_3.setForeground(Color.RED);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(227, 81, 173, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 128, 173, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		String[] arry1 = new String[3];
		arry1[0]="email";
		arry1[1]="pickup";
		arry1[2]="bankaccount";
		comboBox1 = new JComboBox(arry1);
		comboBox1.setBounds(227, 172, 173, 21);
		comboBox1.addItemListener(this);
		contentPane.add(comboBox1);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(227, 213, 173, 21);
		emse=new EmployeeServiceImpl();
		textField_3.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				String e_id=textField_3.getText();
				boolean falg=emse.judgeE_id(Integer.parseInt(e_id));
				if(falg){
					lblNewLabel_2.setText("E_ID is exist");
				}
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				lblNewLabel_2.setText("");
				String e_id=textField_3.getText();
				boolean falg=emse.judgeE_id(Integer.parseInt(e_id));
				if(falg){
					lblNewLabel_2.setText("E_ID is exist");
				}
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				lblNewLabel_2.setText("");
			}
		  });
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(227, 247, 173, 21);
		textField_4.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setText("");
				String u_id=textField_4.getText();
				boolean falg=emse.judgeU_id(Integer.parseInt(u_id));
				if(falg){
					lblNewLabel_3.setText("U_ID is exist");
				}
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setText("");
			}
		  });
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		String[] arry = new String[3];
		arry[0]="Hourly";
		arry[1]="Commission";
		arry[2]="Salary";
		comboBox = new JComboBox(arry);
		comboBox.setBounds(227, 280, 173, 27);
		comboBox.addItemListener(this);
		contentPane.add(comboBox);
		
		 btnSubmit = new JButton("submit");
		 btnSubmit.setBounds(482, 369, 83, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				Main.contentPane.setVisible(true);
			}
		});
		btnNewButton.setBounds(581, 369, 83, 23);
		contentPane.add(btnNewButton);
		
		account = new JTextField();
		account.setBounds(451, 172, 140, 21);
		contentPane.add(account);
		account.setColumns(10);
	
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSubmit){
			if(lblNewLabel_2.getText().equals("E_ID is exist")||lblNewLabel_3.getText().equals("U_ID is exist")){
				contentPane.repaint();
			}else{
			addEmployee();
			contentPane.setVisible(false);
			JPanel p7=new Employee_Detail().getJPanel();
			Main.jf.getContentPane().add(p7);		
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboBox){
			 if(e.getStateChange()==ItemEvent.SELECTED){
				 New_employee.type=(String)comboBox.getSelectedItem();
			 }
		}
		if(e.getSource()==comboBox1){
			 if(e.getStateChange()==ItemEvent.SELECTED){
				 payment=(String)comboBox1.getSelectedItem();
				 if(payment.equals("email")){
					 account.setVisible(true);
				 }
				 if(payment.equals("pickup")){
					 account.setVisible(false);
				 }
				 if(payment.equals("bankaccount")){
					 account.setVisible(true);
				 }
			 }
		}
	}
	public void addEmployee(){
			float salary_rate=0;
			if(New_employee.type.equals("Hourly")){
				salary_rate=(float) 0.6;
			}
			Account acc=new Account();
			Employee em=new Employee();
			em.setName(textField.getText());
			em.setAddress(textField_1.getText());
			em.setPayment(payment);
			em.setEmployee_id(Integer.parseInt(textField_3.getText()));
			acc.setE_id(Integer.parseInt(textField_3.getText()));
			acc.setE_account(account.getText());
			if(textField_4.getText().equals("")||textField_4.getText()==null){
			em.setUnion_id(0);
			}else{
			em.setUnion_id(Integer.parseInt(textField_4.getText()));
			}
			em.setWork_mode(type);
			em.setUnion_rate(0);
			em.setSalary_rate(salary_rate);
			if(type.equals("Salary")){
				em.setNo_salary(2000);	
			}else{
			em.setNo_salary(0);
			}
			IEmployeeService emse=new EmployeeServiceImpl();
			emse.addEmpl(em);
			emse.addAccount(acc);
	}
}
