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
import view.Main;
import model.Account;
import model.Employee;

import java.awt.Color;

public class DetailChange extends JFrame implements ActionListener,ItemListener {
	

	

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JComboBox comboBox,comboBox1;
	JButton btnSubmit;
	String payment;
	public static String type;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	IEmployeeService emse;
	Employee ee;
	private JTextField account;
	/**
	 * Launch the application.
	 *
	 **/

	public DetailChange(Employee ee) {
		// TODO Auto-generated constructor stub
		this.ee=ee;
	}

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
		emse=new EmployeeServiceImpl();
		
		JLabel lblNewEm = new JLabel("Detail Change");
		lblNewEm.setBounds(289, 33, 140, 53);
		contentPane.add(lblNewEm);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(175, 80, 83, 25);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(154, 125, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Method of Payment:");
		lblNewLabel_1.setBounds(99, 157, 110, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEmployeeId = new JLabel("E_ID:");
		lblEmployeeId.setBounds(175, 209, 89, 15);
		contentPane.add(lblEmployeeId);
		
		JLabel lblU = new JLabel("Union:");
		lblU.setBounds(165, 239, 54, 27);
		contentPane.add(lblU);
		
		JLabel lblWorkingMet = new JLabel("working mode:");
		lblWorkingMet.setBounds(130, 288, 89, 25);
		contentPane.add(lblWorkingMet);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(546, 206, 83, 18);
		lblNewLabel_2.setForeground(Color.RED);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(546, 239, 83, 21);
		lblNewLabel_3.setForeground(Color.RED);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(250, 81, 173, 23);
		textField.setText(ee.getName());
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(250, 122, 173, 21);
		textField_1.setText(ee.getAddress());
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		//ÕË»§ ÓÊ¼þ
		account = new JTextField();
		account.setText(emse.getAccount(ee.getEmployee_id()));
		account.setBounds(475, 159, 121, 21);
		contentPane.add(account);
		account.setColumns(10);
		
		String[] arry1 = new String[3];
		arry1[0]="email";
		arry1[1]="pickup";
		arry1[2]="bankaccount";
		payment=ee.getPayment();
		if(payment.equals("pickup")){
			account.setVisible(false);
		}
		comboBox1 = new JComboBox(arry1);
		comboBox1.setBounds(250, 159, 173, 21);
		comboBox1.setSelectedItem(payment);
		comboBox1.addItemListener(this);
		contentPane.add(comboBox1);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(250, 206, 173, 21);
		textField_3.setText(String.valueOf(ee.getEmployee_id()));
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
		textField_4.setBounds(250, 242, 173, 21);
		if(ee.getUnion_id()==0)
			textField_4.setText("null");
		else
			textField_4.setText(String.valueOf(ee.getUnion_id()));
		textField_4.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
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
		DetailChange.type=ee.getWork_mode();
		comboBox = new JComboBox(arry);
		comboBox.setBounds(250, 287, 173, 27);
		comboBox.setSelectedItem(ee.getWork_mode());
		comboBox.addItemListener(this);
		contentPane.add(comboBox);
		
		 btnSubmit = new JButton("submit");
		 btnSubmit.setBounds(458, 369, 83, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				JPanel p=new ListOfEmployee().getJPanel();
				Main.jf.add(p);
			}
		});
		btnNewButton.setBounds(563, 369, 83, 23);
		contentPane.add(btnNewButton);
		
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSubmit){
			emse.delEmpl(ee.getEmployee_id());
			addEmployee();
			contentPane.setVisible(false);
			JPanel p7=new ListOfEmployee().getJPanel();
			Main.jf.getContentPane().add(p7);	
		}
	
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboBox){
			 if(e.getStateChange()==ItemEvent.SELECTED){
				 DetailChange.type=(String)comboBox.getSelectedItem();
			 }
		}
		if(e.getSource()==comboBox1){
			 if(e.getStateChange()==ItemEvent.SELECTED){
				 payment=(String)comboBox1.getSelectedItem();
				 if(payment.equals("email")){
					 account.setVisible(true);
					 account.setText("");
				 }
				 if(payment.equals("pickup")){
					 account.setVisible(false);
				 }
				 if(payment.equals("bankaccount")){
					 account.setVisible(true);
					 account.setText("");
				 }
			 }
		}
	}
	public void addEmployee(){
			float salary_rate=0;
			if(DetailChange.type.equals("Hourly")){
				salary_rate=(float) 0.6;
			}
			Employee em=new Employee();
			Account acc=new Account();
			acc.setE_id(Integer.parseInt(textField_3.getText()));
			acc.setE_account(account.getText());
			em.setName(textField.getText());
			em.setAddress(textField_1.getText());
			em.setPayment(payment);
			em.setEmployee_id(Integer.parseInt(textField_3.getText()));
			if(textField_4.getText().equals("")||textField_4.getText().equals("null")){
				em.setUnion_id(0);
			}else{
			em.setUnion_id(Integer.parseInt(textField_4.getText()));
			}
			em.setWork_mode(type);
			em.setUnion_rate(0);
			em.setSalary_rate(salary_rate);
			em.setNo_salary(0);
			emse.addEmpl(em);
			emse.addAccount(acc);
	}
}