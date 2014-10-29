package service;

import java.util.List;

import model.Employee;
import model.Workload;

public interface IHoursService {
	public List<Employee> getHourlyEmpl();
	public boolean addHours(Workload w);
	public boolean delHours();
	public List<Workload> getAllHours();
}
