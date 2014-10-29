package junit;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.IEmployeeDao;
import dao.impl.EmployeeDaoImpl;

public class EmployeeDaoImplTest extends TestCase{
	private IEmployeeDao emdao;
	private Employee em;
	public void setUp() throws Exception {
		emdao=new EmployeeDaoImpl();
		em=new Employee();
	}

	public void tearDown() throws Exception {
	}
	public void testAddEmpl(){
		em.setEmployee_id(104);
		em.setName("lplp");
		em.setAddress("beijing");
		em.setPayment("email");
		em.setUnion_id(37);
		em.setWork_mode("Commission");
		em.setUnion_rate((float) 0.1);
		em.setNo_salary(0);
		em.setSalary_rate((float) 0.5);
		Assert.assertEquals(true,emdao.addEmpl(em));
	}
	public void testChangeEmpl(){
		em.setEmployee_id(5);
		em.setName("lple");
		em.setAddress("hunan");
		em.setPayment("email");
		em.setUnion_id(33);
		em.setWork_mode("Commission");
		em.setUnion_rate((float) 0.1);
		em.setNo_salary(0);
		em.setSalary_rate((float) 0.5);
		Assert.assertEquals(true,emdao.changeEmpl(em));
	}
	public void testDelEmpl(){
		boolean flag=emdao.delEmpl(104);
		Assert.assertEquals(true,flag);
	}
	public void testChangeUnionRate(){
		boolean flag=emdao.changeUnionRate(1, (float) 0.5);
		Assert.assertEquals(true,flag);
	}
	public void testGetAllEmpl(){
		List list=emdao.getAllEmpl();
		Assert.assertNotNull(list);
	}
	public void testGetHourlyEmpl(){
		List list=emdao.getHourlyEmpl();
		Assert.assertNotNull(list);
	}
	public void testGetUnionEmpl(){
		List list=emdao.getUnionEmpl();
		Assert.assertNotNull(list);
	}
	public void testAddSalary(){
		boolean flag=emdao.AddSalary(5, 100);
		Assert.assertEquals(true,flag);
	}
	public void testClearSalary(){
		boolean flag=emdao.clearSalary("Hourly");
		Assert.assertEquals(true,flag);
	}
	public void testGetEmplById(){
		em=new Employee();
		em=emdao.getEmplById(4);
		Assert.assertNotNull(em);
	}
	public void testGetFind(){
		List list=emdao.getFind("name like '%b%'", "");
		Assert.assertNotNull(list);
	}
	public void testGetCommissionEmpl(){
		List list=emdao.getCommissionEmpl();
		Assert.assertNotNull(list);
	}
	public void testGetHourCommisEmpl(){
		List list=emdao.getHourCommisEmpl();
		Assert.assertNotNull(list);
	}
	public void testJudgeE_id(){
		boolean flag=emdao.judgeE_id(3);
		Assert.assertEquals(true, flag);
	}
	public void testJudgeU_id(){
		boolean flag=emdao.judgeE_id(2);
		Assert.assertEquals(true, flag);
	}
}
