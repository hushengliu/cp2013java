package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Main;
import model.Employee;

public class Rate extends JFrame implements ActionListener{

	

	private JPanel contentPane;
	JButton btnNewButton,btnNewButton_1;
	private JTable table;
	private List<Employee> ems;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Rate frame = new Rate();
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
//		setBounds(100, 100, 485, 401);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		JLabel lblListOfEmployees = new JLabel("List of Employees");
		lblListOfEmployees.setBounds(280, 31, 152, 36);
		lblListOfEmployees.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		contentPane.add(lblListOfEmployees);
		
		 btnNewButton = new JButton("submit");
		 btnNewButton.setBounds(575, 439, 77, 23);
		 btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		 btnNewButton_1 = new JButton("back");
		 btnNewButton_1.setBounds(468, 439, 72, 23);
		 btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 78, 509, 333);
		contentPane.add(scrollPane);
		
		
		IEmployeeService mese=new EmployeeServiceImpl();
		ems=mese.getUnionEmpl();
		int n=ems.size();
		Object[][] aa=new Object[n][4];
		for(int k=0;k<n;k++){
				aa[k][0]=ems.get(k).getName();
			aa[k][1]=ems.get(k).getUnion_id();
			aa[k][2]=ems.get(k).getNo_salary()*ems.get(k).getUnion_rate();
			aa[k][3]=ems.get(k).getUnion_rate();
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "U_ID", "Charges", "rate"
			}
		));
		for(int k=0;k<n;k++){
			table.isCellEditable(k, 3);
		}
//		table.getColumn("rate").setCellRenderer(new TextFieldRenderer());  
//	    table.getColumn("rate").setCellEditor(new TextFieldEditor(new JTextField()));
		scrollPane.setViewportView(table);
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		IEmployeeService emse=new EmployeeServiceImpl();
		if(e.getSource()==btnNewButton){
			int u_id=0;
			for(int k=0;k<ems.size();k++){
				String q=String.valueOf(table.getValueAt(k, 3));	
				if(q.equals("")){
					
				}else{
					float rate=Float.parseFloat(q);
					String uid=String.valueOf(table.getValueAt(k, 1));
					if(uid.equals("null")){
					
					}else{
					u_id=Integer.parseInt(uid);
					emse.changeUnionRate(u_id, rate);
					}
				}
			}
			repaint();
		}
		if(e.getSource()==btnNewButton_1){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}	
	}
	public void repaint(){
		contentPane.setVisible(false);
		JPanel p4=new Rate().getJPanel();
		Main.jf.add(p4);
	}
}
