package com.BankApp.BankManagement.model;

public class Customer {
	private int account_no;
	private String customer_name;
	private String address ;
	private long phone_no ;
	private int balance ;
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Customer(int account_no, String customer_name, String address, long phone_no, int balance) {
		super();
		this.account_no = account_no;
		this.customer_name = customer_name;
		this.address = address;
		this.phone_no = phone_no;
		this.balance = balance;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
