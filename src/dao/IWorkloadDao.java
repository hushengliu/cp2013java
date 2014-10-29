package dao;

import java.util.List;

import model.Workload;

public interface IWorkloadDao {
	public boolean addAmount(Workload w);
	public boolean addHours(Workload w);
	public List<Workload> getById (int id);
	public boolean delAmount();
	public boolean delHours();
	public boolean delWorkByEmId(int emid);
	public List<Workload> getAllAmount();
	public List<Workload> getAllHours();
	
}
