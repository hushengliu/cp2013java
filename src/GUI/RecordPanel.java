package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Main;

public class RecordPanel extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton_1,btnNewButton,btnDelete;
	IEmployeeService mese=new EmployeeServiceImpl();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Record frame = new Record();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(32, 65, 54, 15);
		contentPane.add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(32, 90, 86, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(32, 208, 78, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("back");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(32, 297, 78, 23);
		contentPane.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 65, 467, 347);
		contentPane.add(scrollPane);
		
		List<model.Record> res=mese.getRecords();
		int n=res.size();
		table = new JTable();
		Object[][] aa=new Object[n][4];
		for(int i=0;i<n;i++){
			aa[i][0]=res.get(i).getName();
			aa[i][1]=res.get(i).getE_id();
			aa[i][2]=res.get(i).getAmount();
			aa[i][3]=res.get(i).getTime();
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount","Time"
			}
		));
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(32, 252, 78, 23);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton){
			findRecord();
		}
		if(e.getSource()==btnNewButton_1){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}
		if(e.getSource()==btnDelete){
			deleteRecord();
		}
	}
	public void findRecord(){
		String time=textField.getText();
		if(time.equals("")){
			
		}else{
		List<model.Record> res=mese.getRecordsByDate(time);
		int n=res.size();
		table = new JTable();
		Object[][] aa=new Object[n][4];
		for(int i=0;i<n;i++){
			aa[i][0]=res.get(i).getName();
			aa[i][1]=res.get(i).getE_id();
			aa[i][2]=res.get(i).getAmount();
			aa[i][3]=res.get(i).getTime();
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount","Time"
			}
		));
		textField.setText("");
		scrollPane.setViewportView(table);
		}
	}  
	public void deleteRecord(){
		String time=textField.getText();
		if(time.equals("")){
			
		}else{
			mese.deleteRecord(time);
			List<model.Record> res=mese.getRecords();
			int n=res.size();
			table = new JTable();
			Object[][] aa=new Object[n][4];
			for(int i=0;i<n;i++){
				aa[i][0]=res.get(i).getName();
				aa[i][1]=res.get(i).getE_id();
				aa[i][2]=res.get(i).getAmount();
				aa[i][3]=res.get(i).getTime();
			}
			table.setModel(new DefaultTableModel(
				aa,
				new String[] {
					"Name", "E_ID", "Amount","Time"
				}
			));
			textField.setText("");
			scrollPane.setViewportView(table);
		}
	}
}
