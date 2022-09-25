package com.example.services;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.*;

import com.example.entity.Employee;
import com.example.exceptions.EmployeeNotFoundException;
import com.example.ifaces.EmployeeCrudRepository;
import com.example.repos.EmployeeRepository;

public class EmployeeServices {

	private EmployeeCrudRepository repository;
	private Connection con;
	private static final Logger logger=LogManager.getRootLogger();
	
	public EmployeeServices(Connection con) {
		super();
		this.con=con;
		this.repository=new EmployeeRepository(this.con);
	}
	
	public void addEmployee(Employee employee){
		boolean result=false;
		result = this.repository.save(employee);
		if(result) {
			System.out.println("New Employee Added : "+result);
		}
		else {
			logger.error("New Employee Added : "+result);
		}
	}
	
	public void findByName(String firstName) throws EmployeeNotFoundException{
		List<Employee> employeesList=new ArrayList<>();
		employeesList=this.repository.getByName(firstName);
		employeesList.forEach(e->System.out.println(e));
	}
	
	public void displayNameAndPhoneNumber() throws EmployeeNotFoundException{
		Map<String,Long> employeesNameAndPhoneNumber=this.repository.getNameAndPhoneNumber();
		Set<Map.Entry<String,Long>> items=employeesNameAndPhoneNumber.entrySet();
		items.forEach(e->System.out.println("First Name : "+e.getKey()+"  Phone Number : "+e.getValue()));
	}
	
	public void updateEmailAndPhoneNumber(String initialEmail,String changedEmail,long phoneNumber) throws EmployeeNotFoundException{
		boolean result=this.repository.updateEmailAndPhoneNumber(initialEmail, changedEmail, phoneNumber);
		if(result) {
			System.out.println("Employee details Updated : "+result);
		}
		else {
			logger.error("Employee details Updated : "+result);
		}
	}
	
	public void deleteEmployeeByFirstName(String firstName) throws  EmployeeNotFoundException{
		boolean result=this.repository.deleteByName(firstName);
		if(result) {
			System.out.println("Employee details deleted : "+result);
		}
		else {
			logger.error("Employee details deleted : "+result);
		}
	}
	
	public void displayNameAndMail(LocalDate date) throws EmployeeNotFoundException {
		Map<String,String> nameAndMail=this.repository.getNameAndEmail(date);
		Set<Map.Entry<String,String>> items=nameAndMail.entrySet();
		items.forEach(e->System.out.println("First Name : "+e.getKey()+"  Mail Address : "+e.getValue()));
	}
	
	public void displayNameAndPhoneNumber(LocalDate date) throws EmployeeNotFoundException {
		Map<String,Long> nameAndPhoneNumber=this.repository.getNameAndPhoneNumber(date);
		Set<Map.Entry<String,Long>> items=nameAndPhoneNumber.entrySet();
		items.forEach(e->System.out.println("First Name : "+e.getKey()+"  Phone Number : "+e.getValue()));
	}
	
}
