package com.example;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.*;

import com.example.entity.Employee;
import com.example.exceptions.EmployeeNotFoundException;
import com.example.services.EmployeeServices;
import com.example.utils.ConnectionFactory;

public class App {
	private EmployeeServices services;
	private Connection connection;
	private static final Logger logger=LogManager.getLogger();
	public App() {
		super();
		this.connection=ConnectionFactory.getMySqlConnection();
		this.services=new EmployeeServices(this.connection);
	}
	
	public LocalDate marriedOrUnmarried(Scanner scanner,LocalDate weddingDate) {
		System.out.println("Married/UnMarried - Y/N : ");
		
		char isMarried=scanner.nextLine().charAt(0);
		switch (isMarried) {
			case 'Y':
			case 'y':
				System.out.println("Enter the Wedding Date in (YYYY-MM-DD) Format : ");
				weddingDate = LocalDate.parse(scanner.nextLine());
				break;
			case 'N':
			case 'n':
				break;
			default:
				System.out.println("Invalid choice");
				weddingDate=marriedOrUnmarried(scanner, weddingDate);
				break;
		}
		return weddingDate;
	}

	public void getInputAndOutput(Scanner scanner) {
        while(true) {
        	System.out.println("Employee managemrnt Application\n");
        	System.out.println("1-Add Employee Details\n"
        			+ "2-Display List of Employees by the Firstname\n"
        			+ "3-Display List of Employees with Firstname and Phone number\n"
        			+ "4-Update the email and phone number of a particular employee\n"
        			+ "5-Delete details of a particular employee by Firstname\n"
        			+ "6-List of employees with firstname and email address whose birthday falls on the given date\n"
        			+ "7-Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date\n");
        	int choice=0;
        	System.out.println("Enter any choice from 1 to 7");
        	choice=Integer.parseInt(scanner.nextLine());
        	switch (choice) {
				case 1:{
					System.out.println("1)Add Employee Detail");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					System.out.println("Enter the last name : ");
					String lastName=scanner.nextLine();
					System.out.println("Enter the address : ");
					String address=scanner.nextLine();
					System.out.println("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					System.out.println("Enter the phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					System.out.println("Enter the Date of Birth in (YYYY-MM-DD) Format : ");
					LocalDate dateOfBirth=LocalDate.parse(scanner.nextLine());
					LocalDate weddingDate=null;
					weddingDate=marriedOrUnmarried(scanner,weddingDate);
					this.services.addEmployee(new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate));
					break;
				}	
				case 2:{
					System.out.println("2)List of employees by their firstName");
					String firstName=scanner.nextLine();
					try {
						this.services.findByName(firstName);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{
					System.out.println("3)List of employees with FirstName and Phone Number");
					try {
						this.services.displayNameAndPhoneNumber();
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}	
				case 4:{
					System.out.println("4)Update the email and phoneNumber of a particular employee");
					System.out.println("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					System.out.println("Enter the new mail id : ");
					String newEmailAddress=scanner.nextLine();
					System.out.println("Enter the new phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					try {
						this.services.updateEmailAndPhoneNumber(emailAddress,newEmailAddress,phoneNumber);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 5:{
					System.out.println("5)Delete Details of a Particular employee by firstName");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					try {
						this.services.deleteEmployeeByFirstName(firstName);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 6:{
					System.out.println("6)list of employees with their firstName and emailAddress  whose Birthday falls on the given date");
					System.out.println("Enter the date (YYYY-MM-DD) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					try {
						this.services.displayNameAndMail(date);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 7:{
					System.out.println("7)list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date<-----------------");
					System.out.println("Enter the date (YYYY-MM-DD) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					try {
						this.services.displayNameAndPhoneNumber(date);
					} catch (EmployeeNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				default:{
					logger.error("Give a valid choice");
					break;
				}
			}
        	System.out.println("Do you want to continue 'Y' Continue / 'N' Exit :");
        	char notExitChoice=scanner.nextLine().charAt(0);
        	if(notExitChoice=='N' || notExitChoice=='n') {
        		System.out.println("You have successfully exited");
                scanner.close();
        		return;
        	}
        	else if(notExitChoice=='Y'|| notExitChoice=='Y') {
        		getInputAndOutput(scanner);
        	}
        }	
        
	}
	
    public static void main( String[] args ){
    	App object=new App();
        Scanner scanner=new Scanner(System.in);
        object.getInputAndOutput(scanner);
        
    }
}
