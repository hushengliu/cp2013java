package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import service.IEmployeeService;
import service.IHoursService;
import service.impl.EmployeeServiceImpl;
import view.Main;
import model.Employee;
import model.Workload;

import java.awt.Color;
public class Time_Card extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	JButton btnBack,btnSubmit;
	private JScrollPane scrollPane;
	private JTable table_1;
	private List<Workload> wls;
	private JLabel lblNewLabel_1;
	IEmployeeService emse=new EmployeeServiceImpl();
	IHoursService hemse=new EmployeeServiceImpl();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Time_Card frame = new Time_Card();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.JPanel getJPanel
	 */
	public JPanel getJPanel() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 484, 400);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		
		JLabel lblPostSale = new JLabel("Time Card");
		lblPostSale.setBounds(304, 27, 122, 27);
		lblPostSale.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPane.add(lblPostSale);
		
		JLabel lblEid = new JLabel("E-ID:");
		lblEid.setBounds(38, 71, 54, 15);
		contentPane.add(lblEid);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(36, 34, 83, 20);
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
		label.setBounds(10, 0, 458, 352);
		contentPane.add(label);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(38, 219, 54, 15);
		contentPane.add(lblHours);
		
		textField_2 = new JTextField();
		textField_2.setBounds(38, 244, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnSubmit = new JButton("submit");
		btnSubmit.setBounds(36, 308, 83, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		 btnBack = new JButton("back");
		 btnBack.setBounds(38, 354, 81, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(143, 71, 512, 369);
		contentPane.add(scrollPane);
		
		
		wls=hemse.getAllHours();
		int n=wls.size();
		Object[][] aa=new Object[n][4]; 
		for(int i=0;i<n;i++){
			Employee ee=emse.getEmplById(wls.get(i).getEmployee_id());
			String name=ee.getName();
				aa[i][0]=wls.get(i).getEmployee_id();
				aa[i][1]=name;
				aa[i][2]=wls.get(i).getWorkdate();
				aa[i][3]=wls.get(i).getHours();
		}
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"E_ID", "Name", "Date", "TotalHours"
			}
		));
		scrollPane.setViewportView(table_1);
		
		
		return contentPane;
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnBack){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}
		if(e.getSource()==btnSubmit){
			if(lblNewLabel_1.getText().equals("E_ID不存在")){
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}else{
			addTimeCard();
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			}
		}
	}
	public void addTimeCard(){
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
		wl.setHours(Float.parseFloat(textField_2.getText()));
		hemse.addHours(wl);
		
		wls=hemse.getAllHours();
		int n=wls.size();
		Object[][] aa=new Object[n][4]; 
		for(int i=0;i<n;i++){
			Employee ee=emse.getEmplById(wls.get(i).getEmployee_id());
			String name=ee.getName();
				aa[i][0]=wls.get(i).getEmployee_id();
				aa[i][1]=name;
				aa[i][2]=wls.get(i).getWorkdate();
				aa[i][3]=wls.get(i).getHours();
		}
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"E_ID", "Name", "Date", "TotalHours"
			}
		));
		scrollPane.setViewportView(table_1);
	}
}
