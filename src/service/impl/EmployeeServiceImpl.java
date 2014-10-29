package service.impl;

import java.util.List;

import service.IAmountService;
import service.IEmployeeService;
import service.IHoursService;
import model.Account;
import model.Employee;
import model.Record;
import model.Workload;
import dao.impl.AccountDaoImpl;
import dao.impl.EmployeeDaoImpl;
import dao.impl.RecordDaoImpl;
import dao.impl.WorkloadDaoImpl;
import dao.*;
public class EmployeeServiceImpl implements IEmployeeService,IAmountService,IHoursService{
	IEmployeeDao emdao = new EmployeeDaoImpl();
	IWorkloadDao wdao = new WorkloadDaoImpl();
	IRecordDao redao=new RecordDaoImpl();
	IAccountDao accdao=new AccountDaoImpl();
	// 添加一个新雇员
	public boolean addEmpl(Employee em) {
		return emdao.addEmpl(em);
	}

	// 修改雇员资料
	public boolean changeEmpl(Employee em) {
		return emdao.changeEmpl(em);
	}

	// 删除指定的雇员
	public boolean delEmpl(int em_id) {
		if (emdao.delEmpl(em_id)) {
			accdao.delAccount(em_id);
			wdao.delWorkByEmId(em_id);
			return true;
		}
		return false;
	}

	// 改变指定公会会员的会费率
	public boolean changeUnionRate(int union_id, float rate) {
		return emdao.changeUnionRate(union_id, rate);
	}

	// 获取所有雇员信息
	public List<Employee> getAllEmpl() {
		return emdao.getAllEmpl();
	}
	// 获取所有雇员信息
		public List<Employee> getHourlyEmpl() {
			return emdao.getHourlyEmpl();
		}
		// 获取Commission雇员信息
				public List<Employee> getCommissionEmpl() {
					return emdao.getCommissionEmpl();
				}
				// 获取Salary雇员信息
				public List<Employee> getHourCommisEmpl() {
					return emdao.getHourCommisEmpl();
				}

	// 查询雇员信息
		public Employee getEmplById(int emid) {
			return emdao.getEmplById(emid);
		}

	// 获取所有加了公会的雇员的信息
	public List<Employee> getUnionEmpl() {
		return emdao.getUnionEmpl();
	}

	// 为按业绩算薪水的雇员添加工作量信息
	public boolean addAmount(Workload w) {
		if (wdao.addAmount(w)
				&& emdao.AddSalary(w.getEmployee_id(), w.getAmount() * 10))
			return true;
		else
			return false;
	}

	// 为按小时得薪水的雇员添加工作量信息
	public boolean addHours(Workload w) {
		boolean flag = false;
		Employee em = emdao.getEmplById(w.getEmployee_id());
		float rate = em.getSalary_rate();
		if (wdao.addHours(w)) {
			if (w.getHours() > 8) {
				emdao.AddSalary(w.getEmployee_id(), (float) ((w.getHours() - 8)
						* rate * 1.5 + 8 * rate) * 10);
			}
			if (w.getHours() <= 8) {
				emdao.AddSalary(w.getEmployee_id(), w.getHours() * rate * 10);
			}
			flag = true;
		}
		return flag;
	}

	// 根据雇员编号获取其工作量信息
	public List<Workload> getById(int id) {
		return wdao.getById(id);
	}

	// 删除所有按业绩算薪水的雇员的工作量信息
	public boolean delAmount() {
		return wdao.delAmount();
	}

	// 删除所有按小时得薪水的雇员的工作量信息
	public boolean delHours() {
		return wdao.delHours();
	}
	

	// 结算日指定类型员工清零
	public boolean clearSalary(String workmode) {
		return emdao.clearSalary(workmode);
	}

	// 所有按业绩算薪水的雇员的工作量信息
	public List<Workload> getAllAmount() {
		return wdao.getAllAmount();
	}

	// 得到所有按小时得薪水的雇员的工作量信息
	public List<Workload> getAllHours() {
		return wdao.getAllHours();
	}
	// 判断e_id是否存在
		public boolean judgeE_id(int e_id){ 
			return emdao.judgeE_id(e_id);
		}
	// 判断u_id是否存在
		public boolean judgeU_id(int u_id){ 
			return emdao.judgeU_id(u_id);
		}
	//查询指定的雇员信息
	public List<Employee> getFind(String name,String emid) {
		int num=0;
		if(!name.equals("")){
			name="name like '%"+name+"%' and ";
			num=1;
		}
		if(!emid.equals("")){
			emid="employee_id="+emid;
			num=2;
		}
		if(num==1){
			name=name.replaceAll(" and ", "");
		}
		return emdao.getFind(name, emid);
	}
	//add Record get Salary
	public void addRecord(Record re){
		redao.addRecord(re);
	}
	public List<Record> getRecords(){
		return redao.getRecords();
	}
	public List<Record> getRecordsByDate(String time){
		return redao.getRecordsByDate(time);
	}
	//添加雇员账户和邮箱
	public int addAccount(Account acc){
		return accdao.addAccount(acc);
	}
	//得到雇员账户信息
	public String getAccount(int e_id){
		return accdao.getAccount(e_id);
	}
	//修改雇员账户信息
	public int updateAccount(Account acc){
		return accdao.updateAccount(acc);
	}
	public int deleteRecord(String time){
		return redao.deleteRecord(time);
	}
}
