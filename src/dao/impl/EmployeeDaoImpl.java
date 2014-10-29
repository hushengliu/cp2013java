package dao.impl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import view.Main;
import model.Employee;
import dao.IEmployeeDao;


public class EmployeeDaoImpl implements IEmployeeDao{
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	//添加一个新雇员
	public boolean addEmpl(Employee em){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="insert into employee values(?,?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
				ps.setInt(1, em.getEmployee_id());
				ps.setString(2, em.getName());
				ps.setString(3, em.getAddress());
				ps.setString(4, em.getPayment());
				ps.setInt(5, em.getUnion_id());
				ps.setString(6, em.getWork_mode());
				ps.setFloat(7, em.getUnion_rate());
				ps.setFloat(8, em.getNo_salary());
				ps.setFloat(9, em.getSalary_rate());
			if(ps.executeUpdate()>0)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
		}
		return flag;
	}
	//修改雇员资料
	public boolean changeEmpl(Employee em){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="update employee set name=?,address=?,payment=?,union_id=?,work_mode=? where employee_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, em.getName());
			ps.setString(2, em.getAddress());
			ps.setString(3, em.getPayment());
			ps.setInt(4, em.getUnion_id());
			ps.setString(5, em.getWork_mode());
			ps.setInt(6, em.getEmployee_id());
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
	//删除指定的雇员
	public boolean delEmpl(int em_id){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="delete from employee where employee_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, em_id);
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
	//改变指定公会会员的会费率
	public boolean changeUnionRate(int union_id,float rate){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="update employee set union_rate=? where union_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setFloat(1, rate);
			ps.setInt(2, union_id);
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
	//获取所有雇员信息
	public List<Employee> getAllEmpl(){
		conn=Main.pool.getConnection();
		List<Employee> list=new ArrayList();
		Employee em=new Employee();
		String sql="select *from employee";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				em=new Employee();
				em.setEmployee_id(rs.getInt(1));
				em.setName(rs.getString(2));
				em.setAddress(rs.getString(3));
				em.setPayment(rs.getString(4));
				em.setUnion_id(rs.getInt(5));
				em.setWork_mode(rs.getString(6));
				em.setUnion_rate(rs.getFloat(7));
				em.setNo_salary(rs.getFloat(8));
				em.setSalary_rate(rs.getFloat(9));
				list.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
	//获取所有时间雇员信息
		public List<Employee> getHourlyEmpl(){
			conn=Main.pool.getConnection();
			List<Employee> list=new ArrayList();
			Employee em=new Employee();
			String sql="select *from employee where work_mode=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, "Hourly");
				rs=ps.executeQuery();
				while(rs.next()){
					em=new Employee();
					em.setEmployee_id(rs.getInt(1));
					em.setName(rs.getString(2));
					em.setAddress(rs.getString(3));
					em.setPayment(rs.getString(4));
					em.setUnion_id(rs.getInt(5));
					em.setWork_mode(rs.getString(6));
					em.setUnion_rate(rs.getFloat(7));
					em.setNo_salary(rs.getFloat(8));
					em.setSalary_rate(rs.getFloat(9));
					list.add(em);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				Main.pool.release();
				Main.pool.close(ps, rs);
			}
			return list;
		}
		
	//获取所有加了公会的雇员的信息
	public List<Employee> getUnionEmpl(){
		conn=Main.pool.getConnection();
		List<Employee> list=new ArrayList();
		Employee em=new Employee();
		String sql="select *from employee where union_id>0 ";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				em=new Employee();
				em.setEmployee_id(rs.getInt(1));
				em.setName(rs.getString(2));
				em.setAddress(rs.getString(3));
				em.setPayment(rs.getString(4));
				em.setUnion_id(rs.getInt(5));
				em.setWork_mode(rs.getString(6));
				em.setUnion_rate(rs.getFloat(7));
				em.setNo_salary(rs.getFloat(8));
				em.setSalary_rate(rs.getFloat(9));
				list.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
	//为指定雇员添加薪水(还未扣除会费的)
	public boolean AddSalary(int emid,float salary){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="update employee set no_salary=no_salary+? where employee_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setFloat(1, salary);
			ps.setInt(2, emid);
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
	//结算日清零
	public boolean clearSalary(String workmode){
		conn=Main.pool.getConnection();
		boolean flag=false;
		String sql="update employee set no_salary=0 where work_mode=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, workmode);
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
	//得到指定雇员的信息
	public Employee getEmplById(int emid){
		conn=Main.pool.getConnection();
		Employee em=new Employee();
		String sql="select * from employee where employee_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, emid);
			rs=ps.executeQuery();
			if(rs.next()){
				em.setEmployee_id(rs.getInt(1));
				em.setName(rs.getString(2));
				em.setAddress(rs.getString(3));
				em.setPayment(rs.getString(4));
				em.setUnion_id(rs.getInt(5));
				em.setWork_mode(rs.getString(6));
				em.setUnion_rate(rs.getFloat(7));
				em.setNo_salary(rs.getFloat(8));
				em.setSalary_rate(rs.getFloat(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return em;
	}
	public List<Employee> getFind(String name,String emid){
		conn=Main.pool.getConnection();
		List<Employee> list=new ArrayList();
		Employee em=new Employee();
		String sql="select * from employee where "+name+emid;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				em=new Employee();
				em.setEmployee_id(rs.getInt(1));
				em.setName(rs.getString(2));
				em.setAddress(rs.getString(3));
				em.setPayment(rs.getString(4));
				em.setUnion_id(rs.getInt(5));
				em.setWork_mode(rs.getString(6));
				em.setUnion_rate(rs.getFloat(7));
				em.setNo_salary(rs.getFloat(8));
				em.setSalary_rate(rs.getFloat(9));
				list.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Main.pool.release();
			Main.pool.close(ps, rs);
		}
		return list;
	}
	
	//获取出Commission雇员信息
		public List<Employee> getCommissionEmpl(){
			conn=Main.pool.getConnection();
			List<Employee> list=new ArrayList();
			Employee em=new Employee();
			String sql="select * from employee where work_mode='Commission'";
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					em=new Employee();
					em.setEmployee_id(rs.getInt(1));
					em.setName(rs.getString(2));
					em.setAddress(rs.getString(3));
					em.setPayment(rs.getString(4));
					em.setUnion_id(rs.getInt(5));
					em.setWork_mode(rs.getString(6));
					em.setUnion_rate(rs.getFloat(7));
					em.setNo_salary(rs.getFloat(8));
					em.setSalary_rate(rs.getFloat(9));
					list.add(em);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				Main.pool.release();
				Main.pool.close(ps, rs);
			}
			return list;
		}
		//获取Salary雇员信息
				public List<Employee> getHourCommisEmpl(){
					conn=Main.pool.getConnection();
					List<Employee> list=new ArrayList();
					Employee em=new Employee();
					String sql="select * from employee where work_mode='Commission' or work_mode='Hourly'";
					try {
						ps=conn.prepareStatement(sql);
						rs=ps.executeQuery();
						while(rs.next()){
							em=new Employee();
							em.setEmployee_id(rs.getInt(1));
							em.setName(rs.getString(2));
							em.setAddress(rs.getString(3));
							em.setPayment(rs.getString(4));
							em.setUnion_id(rs.getInt(5));
							em.setWork_mode(rs.getString(6));
							em.setUnion_rate(rs.getFloat(7));
							em.setNo_salary(rs.getFloat(8));
							em.setSalary_rate(rs.getFloat(9));
							list.add(em);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						Main.pool.release();
						Main.pool.close(ps, rs);
					}
					return list;
				}
	public boolean judgeE_id(int e_id){
		boolean falg=false;
		conn=Main.pool.getConnection();
		String sql="select *from employee";
		try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					if(rs.getInt(1)==e_id){
						falg=true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Main.pool.release();
				Main.pool.close(ps, rs);
			}
		return falg;
	}
	public boolean judgeU_id(int u_id){
		boolean falg=false;
		conn=Main.pool.getConnection();
		String sql="select *from employee";
		try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					if(rs.getInt(5)==u_id){
						falg=true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Main.pool.release();
				Main.pool.close(ps, rs);
			}
		return falg;
	}
	
	
}
