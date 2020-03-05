package com.pack.SpringDataJPALimitingQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="empl")
@NamedQuery(name= "Employee.findMaxSalariesByDept",
query = "SELECT e.dept, MAX(e.salary) FROM Employee e GROUP BY e.dept HAVING e.dept in ?1")
public class Employee {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String dept;
  private int salary;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(String name, String dept, int salary) {
	super();
	this.name = name;
	this.dept = dept;
	this.salary = salary;
}
  
}
