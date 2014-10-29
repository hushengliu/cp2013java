package dao;

import java.util.List;

import model.Employee;

public interface IEmployeeDao {
	public boolean addEmpl(Employee em);
	public boolean changeEmpl(Employee em);
	public boolean delEmpl(int em_id);
	public boolean changeUnionRate(int union_id,float rate);
	public List<Employee> getAllEmpl();
	public List<Employee> getHourlyEmpl();
	public List<Employee> getUnionEmpl();
	public boolean AddSalary(int emid,float salary);
	public boolean clearSalary(String workmode);
	public Employee getEmplById(int emid);
	public List<Employee> getFind(String name,String emid);
	public List<Employee> getCommissionEmpl();
	public List<Employee> getHourCommisEmpl();
	public boolean judgeE_id(int e_id);
	public boolean judgeU_id(int u_id);
}
