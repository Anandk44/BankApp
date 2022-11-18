package com.BankApp.BankManagement.exceptions;

public class CusNotFoundException extends Exception {
	
	private int account_no;

	public CusNotFoundException(int account_no) {
		super();
		this.account_no = account_no;
	}

	@Override
	public String toString() {
		return "CusNotFoundException [account_no=" + account_no + "]";
	}
	

}
