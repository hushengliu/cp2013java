package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import view.Main;
import model.Record;
import dao.IRecordDao;

public class RecordDaoImpl implements IRecordDao{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public int addRecord(Record re){
		int n=0;
		String sql="insert into record values(?,?,?,?)";
		conn=Main.pool.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, re.getName());
			ps.setInt(2, re.getE_id());
			ps.setFloat(3, re.getAmount());
			ps.setString(4, re.getTime());
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
	public List<Record> getRecords(){
	       List<Record> res=new ArrayList();
	       String sql="select * from record";
	       conn=Main.pool.getConnection();
	       try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Record re=new Record();
				re.setName(rs.getString(1));
				re.setE_id(rs.getInt(2));
				re.setAmount(rs.getFloat(3));
				re.setTime(rs.getString(4));
				res.add(re);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
	   return res;
	       
	}
	public List<Record> getRecordsByDate(String time){
	       List<Record> res=new ArrayList();
	       String sql="select * from record where time=?";
	       conn=Main.pool.getConnection();
	       try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, time);
			rs=ps.executeQuery();
			while(rs.next()){
				Record re=new Record();
				re.setName(rs.getString(1));
				re.setE_id(rs.getInt(2));
				re.setAmount(rs.getFloat(3));
				re.setTime(rs.getString(4));
				res.add(re);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
	   return res;     
	}
	public int deleteRecord(String time){
		int n=0;
		String sql="delete from record where time=?";
		conn=Main.pool.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,time);
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
