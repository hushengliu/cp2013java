package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Main;

public class Alert extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	JButton okButton,cancelButton;
	private String label;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	
	public Alert(String label) {
		this.label=label;
		setBounds(200, 200, 273, 152);
		setTitle("please check!");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are you sure?");
		lblAreYouSure.setBounds(75, 31, 100, 25);
		contentPanel.add(lblAreYouSure);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton){
			int m=label.length();
			//char[] a=new char[m-3];
			String str="";
			for(int k=0;k<m-3;k++){
				str+=label.charAt(k+3);
			}
			int e_id=Integer.parseInt(str);
			IEmployeeService emse=new EmployeeServiceImpl();
			emse.delEmpl(e_id);
			ListOfEmployee.contentPane.setVisible(false);
			JPanel p5=new ListOfEmployee().getJPanel();
			Main.jf.add(p5);
			setVisible(false);
			dispose();
		}
		if(e.getSource()==cancelButton){
			setVisible(false);
			dispose();
		}
		
	}
}
