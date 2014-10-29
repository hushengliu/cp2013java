package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.IAccountDao;
import dao.impl.AccountDaoImpl;

public class AccountDaoTest extends TestCase{
	private IAccountDao adao;
	private Account a;

	public void setUp() throws Exception {
		adao=new AccountDaoImpl();
		a=new Account();
	}


	public void tearDown() throws Exception {
	}


	public void testAddAccount() {
		a.setE_id(100);
		a.setE_account("agsdgs");
		int num=adao.addAccount(a);
		Assert.assertEquals(1,num);
	}
	public void testGetAccount(){
		String account=adao.getAccount(34);
		Assert.assertEquals("213@qq.com", account);
	}
	public void testDelAccount(){
		int num=adao.delAccount(100);
		Assert.assertEquals(1, num);
	}
	public void testUpdateAccount(){
		a.setE_id(6);
		a.setE_account("bkkb1424");
		int num=adao.updateAccount(a);
		Assert.assertEquals(1, num);
	}
}
