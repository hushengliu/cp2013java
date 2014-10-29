package junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import model.Workload;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.IWorkloadDao;
import dao.impl.WorkloadDaoImpl;

public class WorkloadDaoImplTest extends TestCase{
	private IWorkloadDao wdao;
	private Workload w;

	public void setUp() throws Exception {
		wdao = new WorkloadDaoImpl();
		w = new Workload();
	}

	public void tearDown() throws Exception {
	}

	public void testAddAmount() {
		w.setEmployee_id(5);
		w.setWorkdate(new Date());
		w.setAmount(43);
		boolean flag=wdao.addAmount(w);
		Assert.assertEquals(true, flag);
	}
	public void testAddHours(){
		w.setEmployee_id(4);
		w.setWorkdate(new Date());
		w.setHours(12);
		boolean flag=wdao.addHours(w);
		Assert.assertEquals(true, flag);
	}
	public void testGetById(){
		List list=wdao.getById(4);
		Assert.assertNotNull(list);
	}
	public void testDelWorkByEmId(){
		boolean flag=wdao.delWorkByEmId(4);
		Assert.assertEquals(true, flag);
	}
	public void testGetAllAmount(){
		List list=wdao.getAllAmount();
		Assert.assertNotNull(list);
	}
	public void testgetAllHours(){
		List list=wdao.getAllHours();
		Assert.assertNotNull(list);
	}
	
}
