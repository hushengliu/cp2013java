package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import service.IAmountService;
import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Main;
import model.Workload;

public class Post_Sale extends JFrame implements ActionListener{
	

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	JButton btnSubmit,btnNewButton;
    private List<Workload> wls;
    private JScrollPane scrollPane;
    private JTable table_1;
    JLabel lblNewLabel_1;
    IEmployeeService emse=new EmployeeServiceImpl();
    IAmountService aemse=new EmployeeServiceImpl();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Post_Sale frame = new Post_Sale();
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
	//JPanel getJPanel
	public JPanel getJPanel() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 485, 399);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		JLabel lblPostSale = new JLabel("Post Sale");
		lblPostSale.setBounds(306, 31, 122, 27);
		lblPostSale.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPane.add(lblPostSale);
		
		JLabel lblEid = new JLabel("E-ID:");
		lblEid.setBounds(38, 71, 54, 15);
		contentPane.add(lblEid);
		
		 lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(38, 31, 80, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		//添加监听器
				textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						lblNewLabel_1.setText("");
						String e_id=textField.getText();
						boolean falg=emse.judgeE_id(Integer.parseInt(e_id));
						if(!falg){
							lblNewLabel_1.setText("E_ID不存在");
						}
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						lblNewLabel_1.setText("");
					}
		
				  });
		textField.setBounds(38, 100, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setBounds(38, 143, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(38, 175, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(38, 222, 54, 15);
		contentPane.add(label);
		
		JLabel lblHours = new JLabel("Amount:");
		lblHours.setBounds(38, 222, 54, 15);
		contentPane.add(lblHours);
		
		textField_2 = new JTextField();
		textField_2.setBounds(38, 256, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		 btnSubmit = new JButton("submit");
		 btnSubmit.setBounds(37, 328, 81, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		 btnNewButton = new JButton("back");
		 btnNewButton.setBounds(38, 380, 81, 23);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 68, 512, 378);
		contentPane.add(scrollPane);
		wls=aemse.getAllAmount();
		int n=wls.size();
		Object[][] aa=new Object[n][3]; 
		for(int i=0;i<n;i++){
				aa[i][0]=wls.get(i).getEmployee_id();
				aa[i][1]=wls.get(i).getWorkdate();
				aa[i][2]=wls.get(i).getAmount();
		}
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"E_ID", "Date", "Amount"
			}
		));
		scrollPane.setViewportView(table_1);
		
		
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}
		if(e.getSource()==btnSubmit){
			if(lblNewLabel_1.getText().equals("E_ID不存在")){
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}else{
			addPostSale();
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			}
		}
	}
	public void addPostSale(){

		Workload wl=new Workload();
		wl.setEmployee_id(Integer.parseInt(textField.getText()));
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String date = textField_1.getText();
		Date createDate = null;
		try{
		createDate = simpledateformat.parse(date); 
		}catch(Exception ce){
		System.out.println(ce.getMessage());
		}
		wl.setWorkdate(createDate);
		wl.setAmount(Float.parseFloat(textField_2.getText()));
		aemse.addAmount(wl);
		
		//EmployeeService emse=new EmployeeService();
		wls=aemse.getAllAmount();
		int n=wls.size();
		Object[][] aa=new Object[n][3]; 
		for(int i=0;i<n;i++){
				aa[i][0]=wls.get(i).getEmployee_id();
				aa[i][1]=wls.get(i).getWorkdate();
				aa[i][2]=wls.get(i).getAmount();
		}
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"E_ID", "Date", "Amount"
			}
		));
		scrollPane.setViewportView(table_1);
//		
	}
}
