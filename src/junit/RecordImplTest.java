package junit;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import model.Record;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.IRecordDao;
import dao.impl.RecordDaoImpl;

public class RecordImplTest extends TestCase{
	private IRecordDao rdao;
	private Record r;
	
	public void setUp() throws Exception {
		rdao=new RecordDaoImpl();
		r=new Record();
	}


	public void tearDown() throws Exception {
	}

	public void testAddRecord(){
		r.setName("bns");
		r.setE_id(4);
		r.setAmount(143);
		r.setTime("2014-10-17");
		int num=rdao.addRecord(r);
		Assert.assertEquals(1, num);
	}
	public void testGetRecords(){
		List list=rdao.getRecords();
		Assert.assertNotNull(list);
	}
	public void testGetRecordsByDate(){
		List list=rdao.getRecordsByDate("2014-09-01");
		Assert.assertNotNull(list);
	}
	public void testDeleteRecord(){
		int num=rdao.deleteRecord("2014-10-17");
		boolean flag=false;
		if(num>0){
			flag=true;
		}
		Assert.assertEquals(true, flag);
	}
}
