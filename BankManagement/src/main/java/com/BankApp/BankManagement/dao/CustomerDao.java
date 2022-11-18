package com.BankApp.BankManagement.dao;

import java.sql.ResultSet;
import java.util.List;

import com.BankApp.BankManagement.exceptions.CusNotFoundException;
import com.BankApp.BankManagement.model.Customer;




public interface CustomerDao {
	public abstract void createAccount(Customer c);
	public abstract int getBalance(int account_no);
    public abstract  void withdraw(int account_no);
    public abstract String findCustomer(int Account_no) throws CusNotFoundException ;
    public abstract void deposit (int account_no);
    public abstract void updateCustomerInformation(Customer c);
    public List<Customer>showAllCustomers();
    public   abstract void deleteCustomer(int account_no);
    public ResultSet getCustomerFullInformation(int account_no) throws Exception;
    public boolean checkIfUserExists(int account_no) throws Exception;
	public static final String URL = "jdbc:mysql://localhost:3306/BankApp";
	public static final String UserName = "root" ;
	public static final String Password = "123456";
	public static final String Driver_Class = "com.mysql.cj.jdbc.Driver";

}



