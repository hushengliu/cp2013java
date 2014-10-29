package GUI;

import java.awt.*;  
import java.awt.event.*;  

import javax.swing.*;  
import javax.swing.table.*;  

import model.Employee;
import service.IEmployeeService;
import service.impl.EmployeeServiceImpl;
import view.Main;
/** 
* @version 1.0 11/09/98 
*/  
public class ButtonEditor extends DefaultCellEditor {  
	
  protected JButton button;  
  private String    label;  
  private boolean   isPushed;  
  public ButtonEditor(JCheckBox checkBox) {  
    super(checkBox);  
    button = new JButton();  
    button.setOpaque(true);  
    button.addActionListener(new ActionListener() {  
      public void actionPerformed(ActionEvent e) {  
        fireEditingStopped();  
      }  
    });  
  }  
  public Component getTableCellEditorComponent(JTable table, Object value,  
                   boolean isSelected, int row, int column) {  
    if (isSelected) {  
      button.setForeground(table.getSelectionForeground());  
      button.setBackground(table.getSelectionBackground()); 
    } else{  
      button.setForeground(table.getForeground());  
      button.setBackground(table.getBackground());  
    }  
    label = (value ==null) ? "" : value.toString();  
    button.setText( label );  
    isPushed = true;  
    return button;  
  }  
  public Object getCellEditorValue() {  
    if (isPushed)  {   
    	if(label.length()>3&&label.length()<7){
    	//	int n=JOptionPane.showConfirmDialog(button ,label + ":ok!"); 
    		Alert al=new Alert(label);
//    		if(n==0){
//    			int m=label.length();
//    			//char[] a=new char[m-3];
//    			String str="";
//    			for(int k=0;k<m-3;k++){
//    				str+=label.charAt(k+3);
//    			}
//    			int e_id=Integer.parseInt(str);
//    			EmployeeService emse=new EmployeeService();
//    			emse.delEmpl(e_id);
//    			ListOfEmployee.contentPane.setVisible(false);
//    			JPanel p5=new ListOfEmployee().getJPanel();
//    			Main.jf.add(p5);
//    		} 
    	}
    	else{
    		int m=label.length();
			String str="";
			for(int k=0;k<m-6;k++){
				str+=label.charAt(k+6);
			}
			int e_id=Integer.parseInt(str);
			IEmployeeService emse=new EmployeeServiceImpl();
			Employee ee=emse.getEmplById(e_id);
			DetailChange dc=new DetailChange(ee);
			//dc.setEmployee(ee);
			ListOfEmployee.contentPane.setVisible(false);
			JPanel p5=dc.getJPanel();
			Main.jf.add(p5);
    	    
    	}
    }  
    isPushed = false;  
    return new String(label) ;  
  }  
    
  public boolean stopCellEditing() {  
    isPushed = false;  
    return super.stopCellEditing();  
  }  
  protected void fireEditingStopped() {  
    super.fireEditingStopped();  
  }  
 
}  
