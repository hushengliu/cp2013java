package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

public class ConnectionPool {  
    
    private Vector<Connection> pool;  
      
    /*��������*/  
    private String url = "jdbc:mysql://localhost:3306/payroll";  
    private String username = "root";  
    private String password = "123";  
    private String driverClassName = "com.mysql.jdbc.Driver";  
  
    private int poolSize = 20;  
    private static ConnectionPool instance = null;  
    Connection conn = null;  
    /*���췽������һЩ��ʼ������*/  
    public ConnectionPool() {  
        pool = new Vector<Connection>(poolSize);  
  
        for (int i = 0; i < poolSize; i++) {  
            try {  
                Class.forName(driverClassName);  
                conn = DriverManager.getConnection(url, username, password);  
                pool.add(conn);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /* �������ӵ����ӳ� */  
    public synchronized void release() {  
        pool.add(conn);  
    }  
  
    /* �������ӳ��е�һ�����ݿ����� */  
    public synchronized Connection getConnection() {  
        if (pool.size() > 0) {  
            Connection conn = pool.get(0);  
            pool.remove(conn);  
            return conn;  
        } else {  
            return null;  
        }  
    }  
    public void close(java.sql.PreparedStatement ps,ResultSet rs){
    	if(ps!=null){
    		try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }  
}
