package dao;

import model.Account;

public interface IAccountDao {
	public int addAccount(Account acc);
	public String getAccount(int e_id);
	public int delAccount(int e_id);
	public int updateAccount(Account acc);
	
}
