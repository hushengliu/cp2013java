package service;

import java.util.List;

import model.Account;
import model.Employee;
import model.Record;
import model.Workload;

public interface IEmployeeService {
	public boolean addEmpl(Employee em);
	public boolean changeEmpl(Employee em);
	public boolean delEmpl(int em_id);
	public boolean changeUnionRate(int union_id, float rate);
	public List<Employee> getAllEmpl();
	public List<Employee> getCommissionEmpl();
	public List<Employee> getHourCommisEmpl();
	public Employee getEmplById(int emid);
	public List<Employee> getUnionEmpl();
	public List<Workload> getById(int id);
	public boolean clearSalary(String workmode);
	public boolean judgeE_id(int e_id);
	public boolean judgeU_id(int u_id);
	public List<Employee> getFind(String name,String emid);
	public void addRecord(Record re);
	public List<Record> getRecords();
	public List<Record> getRecordsByDate(String time);
	public int addAccount(Account acc);
	public String getAccount(int e_id);
	public int updateAccount(Account acc);
	public int deleteRecord(String time);
}
