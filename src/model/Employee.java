package model;

public class Employee {
	private int employee_id,union_id;
	private float union_rate,no_salary,salary_rate;
	private String name,address,payment,work_mode;
	
	
	public float getSalary_rate() {
		return salary_rate;
	}
	public void setSalary_rate(float salary_rate) {
		this.salary_rate = salary_rate;
	}
	public float getNo_salary() {
		return no_salary;
	}
	public void setNo_salary(float no_salary) {
		this.no_salary = no_salary;
	}
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getUnion_id() {
		return union_id;
	}
	public void setUnion_id(int union_id) {
		this.union_id = union_id;
	}
	
	public float getUnion_rate() {
		return union_rate;
	}
	public void setUnion_rate(float union_rate) {
		this.union_rate = union_rate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getWork_mode() {
		return work_mode;
	}
	public void setWork_mode(String work_mode) {
		this.work_mode = work_mode;
	}
}
