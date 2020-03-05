package com.pack.SpringDataJPALimitingQuery;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
    
	@Autowired
	private EmployeeRepoistory empRepository;

	public Iterable<Employee> createEmployees(List<Employee> l) {
		Iterable<Employee> list=empRepository.saveAll(l);
	    return list;
	}

	public Employee findBySalaryDesc() {
		Employee emp=empRepository.findTopByOrderBySalaryDesc();
		return emp;
	}

	public List<Employee> findTopBySalary() {
		List<Employee> l=empRepository.findTop3ByOrderBySalaryDesc();
		return l;
	}

	public List<Employee> findFirstBySalary(int sal) {
		List<Employee> l=empRepository.findFirst2BySalary(sal);
		return l;
	}

	public List<Employee> findByName(String string) {
		List<Employee> l=empRepository.findByNameLike(string);
		return l;
	}

	public long countDept(String string) {
		return empRepository.countByDept("HR");
	}

	public List<Object[]> findMaxSalariesByDept(List<String> asList) {
		List<Object[]> l=empRepository.findMaxSalariesByDept(asList);
		return l;
	}

	public List<Object[]> findMaxSalarysByDept(List<String> asList) {
		List<Object[]> l=empRepository.findMaxSalarysByDept(asList);
		return l;
	}

	public List<Employee> findByName1(String name) {
		List<Employee> l=empRepository.findByName1(name);
		return l;
	}

	public List<Employee> findByName2(String name) {
		List<Employee> l=empRepository.findByName2(name);
		return l;
	}

	public List<Employee> findByNameContain(String name) {
		List<Employee> l=empRepository.findByNameContaining(name);
		return l;
	}

	public Object[][] findMaxMinAvgSalariesOfDept(List<String> asList) {
		Object[][] avgSal = empRepository.findMaxMinAvgSalariesOfDept(asList);
		return avgSal;
	}

	public List<Employee> executeNamedParam(String name) {
		List<Employee> l=empRepository.findByDeptName(name);
		return l;
	}

	public List<Employee> findByDepartment(String name) {
		List<Employee> l=empRepository.findByDepartment(name);
		return l;
	}

	public List<Employee> sortOnDept(String name, Sort ascending) {
		List<Employee> list = empRepository.findByDept(name,ascending);
	    return list;
	}

/*	public List<Employee> findByDept(String name) {
		long count = empRepository.count();
	      int pageSize = 2;
	      long pages = count / pageSize;
	      List<Employee> list=null;
	      for (int i = 0; i < pages; i++) {
	          System.out.printf("page num: %s%n", i);
	          list = empRepository.findByDept(name, PageRequest.of(i, pageSize));
	      }
	      return list;
	}*/

	/*public Page<Employee> findByDeptWithPage(String string) {
		 Page<Employee> page = null;
	      Pageable pageable = PageRequest.of(0, 3, Sort.by("id"));
	      while (true) {
	          page = empRepository.findByDept("HR", pageable);
	          int number = page.getNumber();
	          int numberOfElements = page.getNumberOfElements();
	          int size = page.getSize();
	          long totalElements = page.getTotalElements();
	          int totalPages = page.getTotalPages();
	          System.out.printf("page info - page number %s, numberOfElements: %s, size: %s, "
	                          + "totalElements: %s, totalPages: %s%n",
	                  number, numberOfElements, size, totalElements, totalPages);
	          List<Employee> employeeList = page.getContent();
	          employeeList.forEach(System.out::println);
	          if (!page.hasNext()) {
	              break;
	          }
	          pageable = page.nextPageable();
	      }
		return page;
	} */

	public Slice<Employee> findByDeptWithSlice(String string) {
		 Slice<Employee> slice = null;
	      Pageable pageable = PageRequest.of(0, 3, Sort.by("id").descending());
	      while (true) {
	          slice = empRepository.findByDept("HR", pageable);
	          int number = slice.getNumber();
	          int numberOfElements = slice.getNumberOfElements();
	          int size = slice.getSize();
	          System.out.printf("slice info - page number %s, numberOfElements: %s, size: %s%n",
	                  number, numberOfElements, size);
	          List<Employee> employeeList = slice.getContent();
	          employeeList.forEach(System.out::println);
	          if (!slice.hasNext()) {
	              break;
	          }
	          pageable = slice.nextPageable();
	      }
		return slice;
	}

}
