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
	// ���һ���¹�Ա
	public boolean addEmpl(Employee em) {
		return emdao.addEmpl(em);
	}

	// �޸Ĺ�Ա����
	public boolean changeEmpl(Employee em) {
		return emdao.changeEmpl(em);
	}

	// ɾ��ָ���Ĺ�Ա
	public boolean delEmpl(int em_id) {
		if (emdao.delEmpl(em_id)) {
			accdao.delAccount(em_id);
			wdao.delWorkByEmId(em_id);
			return true;
		}
		return false;
	}

	// �ı�ָ�������Ա�Ļ����
	public boolean changeUnionRate(int union_id, float rate) {
		return emdao.changeUnionRate(union_id, rate);
	}

	// ��ȡ���й�Ա��Ϣ
	public List<Employee> getAllEmpl() {
		return emdao.getAllEmpl();
	}
	// ��ȡ���й�Ա��Ϣ
		public List<Employee> getHourlyEmpl() {
			return emdao.getHourlyEmpl();
		}
		// ��ȡCommission��Ա��Ϣ
				public List<Employee> getCommissionEmpl() {
					return emdao.getCommissionEmpl();
				}
				// ��ȡSalary��Ա��Ϣ
				public List<Employee> getHourCommisEmpl() {
					return emdao.getHourCommisEmpl();
				}

	// ��ѯ��Ա��Ϣ
		public Employee getEmplById(int emid) {
			return emdao.getEmplById(emid);
		}

	// ��ȡ���м��˹���Ĺ�Ա����Ϣ
	public List<Employee> getUnionEmpl() {
		return emdao.getUnionEmpl();
	}

	// Ϊ��ҵ����нˮ�Ĺ�Ա��ӹ�������Ϣ
	public boolean addAmount(Workload w) {
		if (wdao.addAmount(w)
				&& emdao.AddSalary(w.getEmployee_id(), w.getAmount() * 10))
			return true;
		else
			return false;
	}

	// Ϊ��Сʱ��нˮ�Ĺ�Ա��ӹ�������Ϣ
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

	// ���ݹ�Ա��Ż�ȡ�乤������Ϣ
	public List<Workload> getById(int id) {
		return wdao.getById(id);
	}

	// ɾ�����а�ҵ����нˮ�Ĺ�Ա�Ĺ�������Ϣ
	public boolean delAmount() {
		return wdao.delAmount();
	}

	// ɾ�����а�Сʱ��нˮ�Ĺ�Ա�Ĺ�������Ϣ
	public boolean delHours() {
		return wdao.delHours();
	}
	

	// ������ָ������Ա������
	public boolean clearSalary(String workmode) {
		return emdao.clearSalary(workmode);
	}

	// ���а�ҵ����нˮ�Ĺ�Ա�Ĺ�������Ϣ
	public List<Workload> getAllAmount() {
		return wdao.getAllAmount();
	}

	// �õ����а�Сʱ��нˮ�Ĺ�Ա�Ĺ�������Ϣ
	public List<Workload> getAllHours() {
		return wdao.getAllHours();
	}
	// �ж�e_id�Ƿ����
		public boolean judgeE_id(int e_id){ 
			return emdao.judgeE_id(e_id);
		}
	// �ж�u_id�Ƿ����
		public boolean judgeU_id(int u_id){ 
			return emdao.judgeU_id(u_id);
		}
	//��ѯָ���Ĺ�Ա��Ϣ
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
	//��ӹ�Ա�˻�������
	public int addAccount(Account acc){
		return accdao.addAccount(acc);
	}
	//�õ���Ա�˻���Ϣ
	public String getAccount(int e_id){
		return accdao.getAccount(e_id);
	}
	//�޸Ĺ�Ա�˻���Ϣ
	public int updateAccount(Account acc){
		return accdao.updateAccount(acc);
	}
	public int deleteRecord(String time){
		return redao.deleteRecord(time);
	}
}
