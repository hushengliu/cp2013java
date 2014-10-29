package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import model.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JScrollPane;

import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.ButtonRenderer;
import view.Main;

public class ListOfEmployee extends JFrame implements ActionListener{
	
	

	public static JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton_1,btnNewButton;
	private JScrollPane scrollPane;
	private List<Employee> ems;
	private List<Employee> findems;
	private JTable table;
	private JButton del,change;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	//JPanel getJPanel()
	public JPanel getJPanel(){
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 484, 400);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(24, 49, 54, 15);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(23, 74, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEid = new JLabel("E_ID:");
		lblEid.setBounds(24, 116, 54, 15);
		contentPane.add(lblEid);
		
		textField_1 = new JTextField();
		textField_1.setBounds(23, 141, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton = new JButton("Serach");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(24, 218, 74, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblListOfEmployees = new JLabel("List Of Employees");
		lblListOfEmployees.setBounds(299, 40, 155, 31);
		lblListOfEmployees.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		contentPane.add(lblListOfEmployees);
		
		 btnNewButton_1 = new JButton("back");
		 btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(24, 268, 74, 23);
		contentPane.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 83, 526, 368);
		contentPane.add(scrollPane);
		
		IEmployeeService mese=new EmployeeServiceImpl();
		ems=mese.getAllEmpl();
		int n=ems.size();
		table = new JTable();
		Object[][] aa=new Object[n][8];
		for(int i=0;i<n;i++){
			del=new JButton("del");
			change=new JButton("change");
			aa[i][0]=ems.get(i).getName();
			aa[i][1]=ems.get(i).getAddress();
			aa[i][2]=ems.get(i).getEmployee_id();
			if(ems.get(i).getUnion_id()==0){
				aa[i][3]="null";
			}else{
			aa[i][3]=ems.get(i).getUnion_id();
			}
			aa[i][4]=ems.get(i).getWork_mode();
			aa[i][5]=ems.get(i).getPayment();
			aa[i][6]="del"+ems.get(i).getEmployee_id();
			aa[i][7]="change"+ems.get(i).getEmployee_id();
		
		}
		//table.getColumn("del").setCellEditor((TableCellEditor) (new JButton("del")));
		
		table.setModel(new DefaultTableModel(
				aa,
			new String[] {
				"Name", "Address", "E_ID", "Union", "working_mode", "MethodofPayMent", "del", "change"
			}
		));
		table.getColumn("del").setCellRenderer(new ButtonRenderer());  
	    table.getColumn("del").setCellEditor(new ButtonEditor(new JCheckBox()));
	    table.getColumn("change").setCellRenderer(new ButtonRenderer());  
	    table.getColumn("change").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane.setViewportView(table);
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton){
			findEmployee();
		}
		if(e.getSource()==btnNewButton_1){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}
	}
	public void findEmployee(){
		String name=textField.getText();
		String E_ID=textField_1.getText();
		IEmployeeService mese=new EmployeeServiceImpl();
		findems=mese.getFind(name, E_ID);
		int n=findems.size();
		table = new JTable();
		Object[][] aa=new Object[n][8];
		for(int i=0;i<n;i++){
			aa[i][0]=findems.get(i).getName();
			aa[i][1]=findems.get(i).getAddress();
			aa[i][2]=findems.get(i).getEmployee_id();
			if(findems.get(i).getUnion_id()==0){
				aa[i][3]="null";
			}else{
			aa[i][3]=findems.get(i).getUnion_id();
			}
			aa[i][4]=findems.get(i).getWork_mode();
			aa[i][5]=findems.get(i).getPayment();
			aa[i][6]="del"+findems.get(i).getEmployee_id();
			aa[i][7]="change"+findems.get(i).getEmployee_id();
		
		}
		//table.getColumn("del").setCellEditor((TableCellEditor) (new JButton("del")));
		
		table.setModel(new DefaultTableModel(
				aa,
			new String[] {
				"Name", "Address", "E_ID", "Union", "working_mode", "MethodofPayMent", "del", "change"
			}
		));
		table.getColumn("del").setCellRenderer(new ButtonRenderer());  
	    table.getColumn("del").setCellEditor(new ButtonEditor(new JCheckBox()));
	    table.getColumn("change").setCellRenderer(new ButtonRenderer());  
	    table.getColumn("change").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane.setViewportView(table);
	}
}
