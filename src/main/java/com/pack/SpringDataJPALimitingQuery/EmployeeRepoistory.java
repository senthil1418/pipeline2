package com.pack.SpringDataJPALimitingQuery;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepoistory extends CrudRepository<Employee,Integer> {
	//based on findFirst and Top
	Employee findTopByOrderBySalaryDesc();
	Employee findTopByOrderBySalaryAsc();
	List<Employee> findTop3ByOrderBySalaryDesc();
    List<Employee> findTop3ByOrderBySalaryAsc();
    List<Employee> findFirst2BySalary(int salary);
    List<Employee> findFirst2ByDeptOrderBySalaryDesc(String deptName);
    
    //using like
    List<Employee> findByNameLike(String name);
    List<Employee> findByDeptLike(String name);
    
    //using count
    long countByDept(String deptName);
    
    //Named Query
    List<Object[]> findMaxSalariesByDept(List<String> deptNames);
    
    //@Query where to execute queries in repo
    @Query("SELECT e.dept, MAX(e.salary) FROM Employee e GROUP BY e.dept HAVING e.dept in ?1")
    public List<Object[]> findMaxSalarysByDept(List<String> deptNames);
    
    //@Query with like operator without wild cards
    @Query("SELECT e FROM Employee e WHERE e.name LIKE ?1")
    public List<Employee> findByName1(String name);

    //@Query with like operator with wild cards
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
    public List<Employee> findByName2(String name);

    //If query is not too complex, instead of like we can use 'containing'
    public List<Employee> findByNameContaining(String name);
    
    //Native SQL query
    @Query(value = "SELECT max(avg_sal), min(avg_sal) FROM (SELECT dept , avg(salary) as avg_sal from empl  GROUP BY dept HAVING dept in ?1) as e ", nativeQuery = true)
    public Object[][] findMaxMinAvgSalariesOfDept(List<String> deptNames);
    
    //@Param in @Query
    @Query(value="select e from Employee e where e.dept =:d")
    public List<Employee> findByDeptName(@Param("d") String name);
    
    //SpEL to access entity name using #entityName
    @Query("SELECT e FROM #{#entityName} e WHERE e.dept = ?1")
    public List<Employee> findByDepartment(String deptName);
    
    //sorting on the returned result is to define repository methods with Sort parameter.
    public List<Employee> findByDept(String deptName, Sort sort);
    
    //Pagination
  //  public List<Employee> findByDept(String deptName, Pageable pageable);
    
   // public Page<Employee> findByDept(String deptName, Pageable pageable);
   
    public Slice<Employee> findByDept(String deptName, Pageable pageable);
    
}