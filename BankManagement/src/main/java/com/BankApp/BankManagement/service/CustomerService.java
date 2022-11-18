package com.BankApp.BankManagement.service;

import java.sql.ResultSet;
import java.util.List;

import com.BankApp.BankManagement.exceptions.CusNotFoundException;
import com.BankApp.BankManagement.model.Customer;




public interface CustomerService {
	public abstract void createAccount(Customer c);
	public abstract int getBalance(int account_no);
    public abstract  void withdraw(int account_no);
    public abstract String findCustomer(int Account_no) throws CusNotFoundException ;
    public abstract void deposit (int account_no);
    public abstract void updateCustomerInformation(Customer c);
    public List<Customer>showAllCustomers();
    public   abstract void deleteCustomer(int account_no);

}
