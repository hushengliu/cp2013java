package service;

import java.util.List;

import model.Workload;

public interface IAmountService {
	public boolean addAmount(Workload w);
	public boolean delAmount();
	public List<Workload> getAllAmount();
	
}
