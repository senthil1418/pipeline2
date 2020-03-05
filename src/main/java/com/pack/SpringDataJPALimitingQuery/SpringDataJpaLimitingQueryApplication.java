package com.pack.SpringDataJPALimitingQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;


@SpringBootApplication
public class SpringDataJpaLimitingQueryApplication implements CommandLineRunner{

	@Autowired
	EmployeeService empService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaLimitingQueryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createEmployees();
		//findBySalaryDesc();
		//findTopBySalary();
		//findFirstBySalary();
		//findByName();
		//countDept();
		//findMaxSalary();
		//findMaxSalarys();
		//findByName1();
		//findByName2();
		//findByNameContain();
		//executeNativeQuery();
		//executeNamedParam();
		//findByDepartment();
		//sortOnDept();
		//findByDept();
		//findByDeptWithPage();
		findByDeptWithSlice();
		
	}

	private void findByDeptWithSlice() {
		 Slice<Employee> s=empService.findByDeptWithSlice("HR");
	}

	private void findByDeptWithPage() {
		//Page<Employee> list = empService.findByDeptWithPage("HR");
       // list.forEach(System.out::println);
	}

	/*private void findByDept() {
		      List<Employee> list = empService.findByDept("HR");
	          list.forEach(System.out::println);
    }*/

	private void sortOnDept() {
		List<Employee> list = empService.sortOnDept("Sales", Sort.by("salary", "name").ascending());
	      list.forEach(System.out::println);
	}

	private void findByDepartment() {
		List<Employee> list = empService.findByDepartment("Admin");
	      list.forEach(System.out::println);
	}

	private void executeNamedParam() {
		List<Employee> l=empService.executeNamedParam("HR");
		l.forEach(System.out::println);
	}

	private void executeNativeQuery() {
		Object[][] maxAvgSalaries = empService.findMaxMinAvgSalariesOfDept(Arrays.asList("Admin", "IT"));
	      for (Object[] maxAvgSalary : maxAvgSalaries) {
	          System.out.println("max avg salary: " + maxAvgSalary[0]);
	          System.out.println("min avg salary: " + maxAvgSalary[0]);
	      }
	}

	private void findByNameContain() {
		List<Employee> list = empService.findByNameContain("am");
	      list.forEach(System.out::println);
	}

	private void findByName2() {
		List<Employee> list = empService.findByName2("am");
	      list.forEach(System.out::println);
	}

	private void findByName1() {
		List<Employee> list = empService.findByName1("%a%");
	      list.forEach(System.out::println);
	}

	private void findMaxSalarys() {
		List<Object[]> list = empService.findMaxSalarysByDept(Arrays.asList("Admin", "HR"));
		list.forEach(arr -> {
	          System.out.println(Arrays.toString(arr));
	      });	
	}

	private void findMaxSalary() {
		List<Object[]> list = empService.findMaxSalariesByDept(Arrays.asList("Admin", "HR"));
		list.forEach(arr -> {
	          System.out.println(Arrays.toString(arr));
	      });	
		}

	private void countDept() {
		long count=empService.countDept("HR");
		System.out.println(count);
	}

	private void findByName() {
		List<Employee> l=empService.findByName("%am");
		l.forEach(System.out::println);	
	}

	private void findFirstBySalary() {
		List<Employee> l=empService.findFirstBySalary(25000);
		l.forEach(System.out::println);
	}

	private void findTopBySalary() {
		List<Employee> l=empService.findTopBySalary();
		l.forEach(System.out::println);
	}

	private void findBySalaryDesc() {
		Employee emp=empService.findBySalaryDesc();
		System.out.println(emp);
	}

	private void createEmployees() {
		List<Employee> l=new ArrayList<>();
		l.add(new Employee("ram","HR",25000));
		l.add(new Employee("sam","Admin",20000));
		l.add(new Employee("raj","Sales",30000));
		l.add(new Employee("Ramu","Admin",35000));
		l.add(new Employee("Pack","HR",26000));
		l.add(new Employee("Mack","Sales",25000));
		
		Iterable<Employee> elist=empService.createEmployees(l);
		for(Employee e: elist){
			System.out.println(e);
		}
	}

}
