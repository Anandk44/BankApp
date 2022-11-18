package com.BankApp.BankManagement;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.BankApp.BankManagement.exceptions.CusNotFoundException;
import com.BankApp.BankManagement.model.Customer;
import com.BankApp.BankManagement.service.CustomerService;
import com.BankApp.BankManagement.serviceimpl.CustomerImpl;

import jdk.internal.org.jline.utils.Log;

/**
 * Hello world!
 *
 */
public class App {
	public static Logger log = Logger.getLogger(App.class);
	static {
		try {
			SimpleLayout sl = new SimpleLayout();
			ConsoleAppender ca = new ConsoleAppender(sl);
			log.addAppender(ca);
			log.setLevel(Level.DEBUG);
			log.info("App::Log4j setup ready");
		} catch (Exception e) {

			e.printStackTrace();
			log.fatal("App::problem while setting up log4j");
		}
	}

	public static void main(String[] args) {
		log.debug("App::start of main(.) method");

		Scanner sc = new Scanner(System.in);
		CustomerService cs = new CustomerImpl();

		System.out.println(" Welcome to RevBank ");
		System.out.println(" ");

		int choice = 0;
		while (true) {

			System.out.println("Choose the action you want to perform ");
			System.out.println("PRESS 1 to Create Account ");
			System.out.println("PRESS 2 to DELETE Account");
			System.out.println("PRESS 3 to Update Account");
			System.out.println("PRESS 4 to Find Customer");
			System.out.println("PRESS 5 to print all Customers : ");
			System.out.println("PRESS 6 to For Bank Balance of Customer : ");
			System.out.println("PRESS 7 to Deposit Money : ");
			System.out.println("PRESS 8 to Withdraw : ");
			System.out.println("PRESS 9 to EXIT App");
			try {
				choice = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
				main(null);
			}
			switch (choice) {
			case 1:
				System.out.println("Enter Your Information : ");
				System.out.println("please enter your name : ");
				String customerName = sc.nextLine();
				System.out.println("please enter your address : ");
				String address = sc.nextLine();
				System.out.println("please enter your phone number : ");
				long phoneNo = sc.nextLong();
				System.out.println("please enter Balance : ");
				int Balance = sc.nextInt();
				System.out.println("Enter Account Number");
				int acc_no = sc.nextInt();

				cs.createAccount(new Customer(acc_no, customerName, address, phoneNo, Balance));
				System.out.println("*******************************************************************************");

				break;
			case 2:
				System.out.println("Enter Customer acc_no to delete Customer Infomation : ");
				int id = sc.nextInt();
				cs.deleteCustomer(id);
				System.out.println("*******************************************************************************");
				break;
			case 3:
				System.out.println("Enter Customer Account number  to update Customer Information  :  ");
				int UpdatedAccNo = Integer.parseInt(sc.nextLine());
				System.out.println("Enter new name : ");
				String updatedName = sc.nextLine();
				System.out.println("Enter new Address ; ");
				String updatedAddress = sc.nextLine();
				System.out.println("Enter new phone number : ");
				long updatedPhoneNo = sc.nextLong();
				System.out.println("enter new balance : ");
				int updatedBalance = sc.nextInt();
				cs.updateCustomerInformation(
						new Customer(UpdatedAccNo, updatedName, updatedAddress, updatedPhoneNo, updatedBalance));
				System.out.println("*******************************************************************************");
				break;
			case 4:
				System.out.println("Enter Customer id to find Customer :");
				int findcus = sc.nextInt();
				try {
					cs.findCustomer(findcus);
				} catch (CusNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("*******************************************************************************");
				break;
			case 5:
				List<Customer> elist = cs.showAllCustomers();
				for (Customer e : elist) {
					System.out.println(" Customer accNo  : " + e.getAccount_no() + " Customer Name  : "
							+ e.getCustomer_name() + " Customer Address  : " + e.getAddress() + " Customer phoneNo :  "
							+ e.getPhone_no() + " Customer Balance  : " + e.getBalance());
				}
				System.out.println("**********************************************************************");
				break;
			case 6:
				System.out.println("Enter Account_no to get Balance :");
				int accNo = sc.nextInt();
				cs.getBalance(accNo);
				System.out.println("**********************************************************************");
				break;
			case 7:
				System.out.println("Enter accNo to Deposit Money ");
				int accNo1 = sc.nextInt();
				cs.deposit(accNo1);
				System.out.println("*******************************************************************************");
				break;
			case 8:
				System.out.println("Enter Account Number to withdraw money");
				int accno = sc.nextInt();
				cs.withdraw(accno);
				System.out.println("*******************************************************************************");
				break;
			case 9:
				System.out.println("Thank you for using RevBank  ");
				System.exit(0);
				break;
			default:
				System.out.println("wrong option");
			}
		}
	
	}
	
}
