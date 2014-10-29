package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import GUI.ListOfEmployee;
import GUI.New_employee;
import GUI.Payroll_Detail;
import GUI.Post_Sale;
import GUI.Rate;
import GUI.Time_Card;
import dbutil.ConnectionPool;

import java.awt.Color;

public class Main extends JFrame implements ActionListener{
	public static ConnectionPool pool=new ConnectionPool();
	JButton PostUnion = new JButton("PostUnion");
	JButton New = new JButton("New");
	JButton PostSale = new JButton("PostSale");
	JButton PostTimeCard = new JButton("PostTimeCard");
	JButton View= new JButton("View/Eidt");
	JButton RunPayroll = new JButton("RunPayroll");
	public static JPanel contentPane,p1,p2,p3,p4,p5,p6;
	public static JFrame jf=null;
	public static int num=0;
	public static int week=0;
	public static int month=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 Main jfmain=new Main();
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		 jf=new JFrame();
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		p1=new JPanel();

		JLabel lblNewLabel = new JLabel("Payroll System");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		lblNewLabel.setBounds(300, 29, 158, 38);
		contentPane.add(lblNewLabel);
		
		PostUnion.setBounds(131, 91, 133, 38);
		contentPane.add(PostUnion);
		PostUnion.addActionListener(this);
		
		New.setBounds(131, 174, 133, 38);
		contentPane.add(New);
		New.addActionListener(this);
		
		
		PostSale.setBounds(131, 250, 133, 38);
		contentPane.add(PostSale);
		PostSale.addActionListener(this);
		
		PostTimeCard.setBounds(400, 91, 133, 38);
		contentPane.add(PostTimeCard);
		PostTimeCard.addActionListener(this);
	
		View.setBounds(400, 174, 133, 38);
		contentPane.add(View);
		View.addActionListener(this);
		
	
		RunPayroll.setBounds(400, 250, 133, 38);
		contentPane.add(RunPayroll);
		RunPayroll.addActionListener(this);
		
		jf.getContentPane().add(contentPane);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(100, 100, 443, 308);
		jf.setVisible(true);
		jf.setSize(700, 500);
		jf.setResizable(false);
	//	contentPane.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==PostUnion){
			contentPane.setVisible(false);
			p1=new Rate().getJPanel();
			jf.add(p1);
		}	
		if(e.getSource()==New){
			contentPane.setVisible(false);
			 p2=new New_employee().getJPanel();
			jf.add(p2);
		}	
		if(e.getSource()==PostSale){
			contentPane.setVisible(false);
			 p3=new Post_Sale().getJPanel();
			jf.add(p3);
		}	
		if(e.getSource()==PostTimeCard){
			contentPane.setVisible(false);
			p4=new Time_Card().getJPanel();
			jf.add(p4);
		}	
		if(e.getSource()==View){
			contentPane.setVisible(false);
			 p5=new ListOfEmployee().getJPanel();
			jf.add(p5);
		}	
		if(e.getSource()==RunPayroll){
			contentPane.setVisible(false);
			 p6=new Payroll_Detail().getJPanel();
			jf.add(p6);
		}	
	}
}
