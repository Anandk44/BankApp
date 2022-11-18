package com.BankApp.BankManagement.serviceimpl;

import java.sql.ResultSet;
import java.util.List;

import com.BankApp.BankManagement.dao.CustomerDao;
import com.BankApp.BankManagement.daoImpl.CustomerDaoImpl;
import com.BankApp.BankManagement.exceptions.CusNotFoundException;
import com.BankApp.BankManagement.model.Customer;
import com.BankApp.BankManagement.service.CustomerService;

public class CustomerImpl implements CustomerService{
	CustomerDao cd = new CustomerDaoImpl();

	public void createAccount(Customer c) {
		cd.createAccount(c);
		
	}

	public int getBalance(int account_no) {
		
		return cd.getBalance(account_no);
	}

	public void withdraw(int account_no) {
		
		cd.withdraw(account_no);
	}

	public String findCustomer(int Account_no) throws CusNotFoundException {
		
		return cd.findCustomer(Account_no);
	}

	public void deposit(int account_no) {
		cd.deposit(account_no);
		
	}

	public void updateCustomerInformation(Customer c) {
		cd.updateCustomerInformation(c);
		
	}

	public List<Customer> showAllCustomers() {
		
		return cd.showAllCustomers();
	}

	public void deleteCustomer(int account_no) {
	 cd.deleteCustomer(account_no);
		
	}
	
	

}