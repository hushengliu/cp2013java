package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Msg extends JFrame implements ActionListener{

	private JPanel contentPane;
	JLabel lblNewLabel;
	String pay;
	JButton btnOk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Msg frame = new Msg("Hourly");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Msg(String payment) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 273, 152);
		setTitle("Message");
		this.pay=payment;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(payment.equals("Hourly")){
			lblNewLabel = new JLabel("Hourly can get Salary");
		}
		if(payment.equals("Commission")){
			lblNewLabel = new JLabel("Hourly Commission can get Salary");
		}
		if(payment.equals("Salary")){
			lblNewLabel = new JLabel("Hourly Commission Salary can get Salary");
		}
		lblNewLabel.setBounds(10, 31, 237, 28);
		contentPane.add(lblNewLabel);
		
		btnOk = new JButton("ok");
		btnOk.addActionListener(this);
		btnOk.setBounds(97, 81, 73, 23);
		contentPane.add(btnOk);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOk){
			if(pay.equals("Hourly")){
				setVisible(false);
				dispose();
			}
			if(pay.equals("Commission")){
				
			}
			if(pay.equals("Salary")){
	
			}
		}
	}

}
