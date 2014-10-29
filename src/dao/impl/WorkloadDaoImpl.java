package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import view.Main;
import model.Workload;
import dao.IWorkloadDao;


public class WorkloadDaoImpl implements IWorkloadDao{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//为按业绩算薪水的雇员添加工作量信息
	public boolean addAmount(Workload w) {
		conn = Main.pool.getConnection();
		boolean flag = false;
		String sql = "insert into workload(employee_id,workdate,amount) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, w.getEmployee_id());
			ps.setDate(2, new java.sql.Date(w.getWorkdate().getTime()));
			ps.setFloat(3, w.getAmount());
			if (ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return flag;
	}
	//为按小时得薪水的雇员添加工作量信息
	public boolean addHours(Workload w) {
		conn = Main.pool.getConnection();
		boolean flag = false;
		String sql = "insert into workload(employee_id,workdate,hours) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, w.getEmployee_id());
			ps.setDate(2, new java.sql.Date(w.getWorkdate().getTime()));
			ps.setFloat(3, w.getHours());
			if (ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return flag;
	}
	//根据雇员编号获取其工作量信息
	public List<Workload> getById (int id) {
		conn = Main.pool.getConnection();
		boolean flag = false;
		List<Workload> list=new ArrayList();
		Workload w=new Workload();
		String sql = "select *from workload where employee_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				w=new Workload();
				w.setEmployee_id(rs.getInt(1));
				w.setWorkdate(rs.getDate(2));
				w.setAmount(rs.getFloat(3));
				w.setHours(rs.getFloat(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
	//删除所有按业绩算薪水的雇员的工作量信息
	public boolean delAmount(){
		conn = Main.pool.getConnection();
		boolean flag = false;
		String sql="delete from workload where hours is null";
		try {
			ps=conn.prepareStatement(sql);
			if(ps.executeUpdate()>0)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return flag;
	}
	//删除所有按小时得薪水的雇员的工作量信息
	public boolean delHours(){
		conn = Main.pool.getConnection();
		boolean flag = false;
		String sql="delete from workload where amount is null";
		try {
			ps=conn.prepareStatement(sql);
			if(ps.executeUpdate()>0)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return flag;
	}
	//删除指定雇员的所有工作量信息
	public boolean delWorkByEmId(int emid){
		conn = Main.pool.getConnection();
		boolean flag = false;
		String sql="delete from workload where employee_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, emid);
			if(ps.executeUpdate()>0)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return flag;
	}
	//所有按业绩算薪水的雇员的工作量信息
	public List<Workload> getAllAmount() {
		conn = Main.pool.getConnection();
		boolean flag = false;
		List<Workload> list=new ArrayList();
		Workload w=new Workload();
		String sql = "select *from workload where amount is not null";
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				w=new Workload();
				w.setEmployee_id(rs.getInt(1));
				w.setWorkdate(rs.getDate(2));
				w.setAmount(rs.getFloat(3));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
	//得到所有按小时得薪水的雇员的工作量信息
	public List<Workload> getAllHours() {
		conn = Main.pool.getConnection();
		boolean flag = false;
		List<Workload> list=new ArrayList();
		Workload w=new Workload();
		String sql = "select *from workload where hours is not null";
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				w=new Workload();
				w.setEmployee_id(rs.getInt(1));
				w.setWorkdate(rs.getDate(2));
				w.setHours(rs.getFloat(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
}
