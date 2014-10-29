package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.Main;
import model.Account;
import dao.IAccountDao;


public class AccountDaoImpl implements IAccountDao{
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public int addAccount(Account acc){
		int n=0;
		conn=Main.pool.getConnection();
		String sql="insert into account values(?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, acc.getE_id());
			ps.setString(2, acc.getE_account());
			if(ps.executeUpdate()>0){
				n=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		
		return n;
	}
	public String getAccount(int e_id){
		String account=null;
		conn=Main.pool.getConnection();
		String sql="select * from account where e_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,e_id);
			rs=ps.executeQuery();
			while(rs.next()){
				account=rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return account;
	}
	public int delAccount(int e_id){
		int n=0;
		conn=Main.pool.getConnection();
		String sql="delete from account where e_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,e_id);
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return n;
	}
	public int updateAccount(Account acc){
		int n=0;
		conn=Main.pool.getConnection();
		String sql="update account set e_account=? where e_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, acc.getE_account());
			ps.setInt(2, acc.getE_id());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return n;
	}
}
