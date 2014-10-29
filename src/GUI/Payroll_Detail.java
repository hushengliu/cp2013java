package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import model.Employee;
import model.Record;
import chrriis.dj.nativeswing.swtimpl.Message;
import service.IAmountService;
import service.IEmployeeService;
import service.IHoursService;
import service.impl.EmployeeServiceImpl;
import view.Main;

public class Payroll_Detail extends JFrame implements ActionListener{
	

	private JPanel contentPane;
	private JButton btnLoad,btnReset;
	private JButton btnNewButton;
	JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable table;
	private List<Employee> ems;
	JLabel lblNewLabel_1,lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblMonth;
	private JLabel lblNewLabel_4;
	IEmployeeService mese=new EmployeeServiceImpl();
	IAmountService aemse=new EmployeeServiceImpl();
	IHoursService hemse=new EmployeeServiceImpl();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Payroll_Detail frame = new Payroll_Detail();
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
//		setBounds(100, 100, 486, 401);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payroll Detail");
		lblNewLabel.setBounds(285, 20, 136, 52);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(444, 438, 73, 23);
		btnLoad.addActionListener(this);
		contentPane.add(btnLoad);
		
		btnNewButton = new JButton("Run");
		btnNewButton.setBounds(530, 438, 67, 23);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 71, 525, 338);
		contentPane.add(scrollPane);
		ems=mese.getAllEmpl();
		int n=ems.size();
		table = new JTable();
		Object[][] aa=new Object[n][3];
		for(int i=0;i<n;i++){
			aa[i][0]=ems.get(i).getName();
			aa[i][1]=ems.get(i).getEmployee_id();
			if(ems.get(i).getUnion_rate()==0){
				aa[i][2]=ems.get(i).getNo_salary();
			}else{
				float rate=ems.get(i).getUnion_rate();
				aa[i][2]=ems.get(i).getNo_salary()*(1-rate);
			}
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel(String.valueOf(Main.num));
		lblNewLabel_1.setBounds(580, 40, 15, 15);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("back");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(361, 438, 73, 23);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_2 = new JLabel("week:");
		lblNewLabel_2.setBounds(530, 20, 32, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(String.valueOf(Main.week));
		lblNewLabel_3.setBounds(563, 20, 32, 15);
		contentPane.add(lblNewLabel_3);
		
		lblMonth = new JLabel("month:");
		lblMonth.setBounds(592, 20, 54, 15);
		contentPane.add(lblMonth);
		
		lblNewLabel_4 = new JLabel(String.valueOf(Main.month));
		lblNewLabel_4.setBounds(646, 20, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(607, 438, 67, 23);
		btnReset.addActionListener(this);
		contentPane.add(btnReset);
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnLoad){
			contentPane.setVisible(false);
			JPanel p4=new RecordPanel().getJPanel();
			Main.jf.getContentPane().add(p4);	
		}
		if(e.getSource()==btnNewButton){
			Main.num++;
			lblNewLabel_1.setText(String.valueOf(Main.num));
			lblNewLabel_1.repaint();
			if(Main.num%5==0&&Main.num%10!=0){
				Main.week++;
				lblNewLabel_3.setText(String.valueOf(Main.week));
				getHourly();
				JOptionPane jp=new JOptionPane();
				jp.showMessageDialog(btnNewButton,"Hourly can get Salary");
				addRecord();
				hemse.delHours();
				mese.clearSalary("Hourly");
				contentPane.setVisible(false);
				JPanel p4=new Payroll_Detail().getJPanel();
				Main.jf.getContentPane().add(p4);
			}
			if(Main.num%10==0&&Main.num!=30){
				Main.week++;
				lblNewLabel_3.setText(String.valueOf(Main.week));
				getHourCommisEmpl();
				JOptionPane jp=new JOptionPane();
				jp.showMessageDialog(btnNewButton,"Hourly Commission can get Salary");
				addRecord();
				hemse.delHours();
				aemse.delAmount();
				mese.clearSalary("Commission");
				mese.clearSalary("Hourly");
				contentPane.setVisible(false);
				JPanel p4=new Payroll_Detail().getJPanel();
				Main.jf.getContentPane().add(p4);
			}
			if(Main.num%30==0){
				Main.month++;
				lblNewLabel_3.setText(String.valueOf(Main.month));
				getSalaryEmpy();
				JOptionPane jp=new JOptionPane();
				jp.showMessageDialog(btnNewButton,"Hourly Commission Salary can get Salary");
				Main.num=0;
				addRecord();
				mese.clearSalary("Commission");
				mese.clearSalary("Hourly");
				mese.clearSalary("Salary");
				contentPane.setVisible(false);
				JPanel p4=new Payroll_Detail().getJPanel();
				Main.jf.getContentPane().add(p4);
			}
		}
		if(e.getSource()==btnNewButton_1){
			contentPane.setVisible(false);
			Main.contentPane.setVisible(true);
		}
		if(e.getSource()==btnReset){
			Main.month=0;
			Main.week=0;
			Main.num=0;
			lblNewLabel_1.setText(String.valueOf(0));
			lblNewLabel_1.repaint();
			lblNewLabel_3.setText(String.valueOf(0));
			lblNewLabel_3.repaint();
			lblNewLabel_4.setText(String.valueOf(0));
			lblNewLabel_4.repaint();
		}
	}
	public void getHourly(){
		ems=hemse.getHourlyEmpl();
		int n=ems.size();
		table = new JTable();
		Object[][] aa=new Object[n][3];
		for(int i=0;i<n;i++){
			aa[i][0]=ems.get(i).getName();
			aa[i][1]=ems.get(i).getEmployee_id();
			if(ems.get(i).getUnion_rate()==0){
				aa[i][2]=ems.get(i).getNo_salary();
			}else{
				float rate=ems.get(i).getUnion_rate();
				aa[i][2]=ems.get(i).getNo_salary()*(1-rate);
			}
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount"
			}
		));
		scrollPane.setViewportView(table);	
	}
	public void getSalaryEmpy(){
		ems=mese.getAllEmpl();
		int n=ems.size();
		table = new JTable();
		Object[][] aa=new Object[n][3];
		for(int i=0;i<n;i++){
			aa[i][0]=ems.get(i).getName();
			aa[i][1]=ems.get(i).getEmployee_id();
			if(ems.get(i).getUnion_rate()==0){
				aa[i][2]=ems.get(i).getNo_salary();
			}else{
				float rate=ems.get(i).getUnion_rate();
				aa[i][2]=ems.get(i).getNo_salary()*(1-rate);
			}
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount"
			}
		));
		scrollPane.setViewportView(table);	
	}
	public void getHourCommisEmpl(){
		ems=mese.getHourCommisEmpl();
		int n=ems.size();
		table = new JTable();
		Object[][] aa=new Object[n][3];
		for(int i=0;i<n;i++){
			aa[i][0]=ems.get(i).getName();
			aa[i][1]=ems.get(i).getEmployee_id();
			if(ems.get(i).getUnion_rate()==0){
				aa[i][2]=ems.get(i).getNo_salary();
			}else{
				float rate=ems.get(i).getUnion_rate();
				aa[i][2]=ems.get(i).getNo_salary()*(1-rate);
			}
		}
		table.setModel(new DefaultTableModel(
			aa,
			new String[] {
				"Name", "E_ID", "Amount"
			}
		));
		scrollPane.setViewportView(table);	
	}
	public void addRecord(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String time=df.format(new Date());//当前系统时间
		int row=table.getRowCount();
		for(int n=0;n<row;n++){
			Record re=new Record();
			re.setName((String) table.getValueAt(n, 0));
			re.setE_id((int)table.getValueAt(n, 1));
			re.setAmount((float)table.getValueAt(n, 2));
			re.setTime(time);
			mese.addRecord(re);
		}
	}
}

