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
	//Ϊ��ҵ����нˮ�Ĺ�Ա��ӹ�������Ϣ
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
	//Ϊ��Сʱ��нˮ�Ĺ�Ա��ӹ�������Ϣ
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
	//���ݹ�Ա��Ż�ȡ�乤������Ϣ
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
	//ɾ�����а�ҵ����нˮ�Ĺ�Ա�Ĺ�������Ϣ
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
	//ɾ�����а�Сʱ��нˮ�Ĺ�Ա�Ĺ�������Ϣ
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
	//ɾ��ָ����Ա�����й�������Ϣ
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
	//���а�ҵ����нˮ�Ĺ�Ա�Ĺ�������Ϣ
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
	//�õ����а�Сʱ��нˮ�Ĺ�Ա�Ĺ�������Ϣ
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
