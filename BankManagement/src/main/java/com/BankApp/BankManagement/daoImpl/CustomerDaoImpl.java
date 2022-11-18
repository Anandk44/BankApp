package com.BankApp.BankManagement.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.BankApp.BankManagement.dao.CustomerDao;
import com.BankApp.BankManagement.exceptions.CusNotFoundException;
import com.BankApp.BankManagement.model.Customer;

import jdk.internal.org.jline.utils.Log;

public class CustomerDaoImpl implements CustomerDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	public Scanner scanner = new Scanner(System.in);
	public static final String INS_COMMAND = "INSERT INTO Customer VALUES(?,?,?,?,?)";
	public static final String UPDATE_COMMAND = "UPDATE Customer SET customer_name = ? , address = ? , phone_no = ? , balance = ? WHERE account_no = ?  ";
	public static final String DELETE_COMMAND = "DELETE FROM  Customer WHERE  account_no = ? ";
	public static final String FIND_COMMAND = "SELECT *  FROM Customer WHERE account_no =? ";
	public static final String SELECT_ALL = "SELECT * FROM Customer";
	public static final String BALANCE = "SELECT * FROM Customer WHERE account_no = ? ";

	public CustomerDaoImpl() {
		try {
			cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName, CustomerDao.Password);
//		     Log.info("App::Connection is established with DB");
		} catch (SQLException e) {
			System.out.println("unable to establishh the connection with db");
			e.printStackTrace();
		}
	}

	public void createAccount(Customer c) {
		int i = 0;
		try {
			st = cn.prepareStatement(INS_COMMAND);
//			Log.debug("App::JDBC Statement object is created");
			st.setLong(1, c.getAccount_no());
			st.setString(2, c.getCustomer_name());
			st.setString(3, c.getAddress());
			st.setLong(4, c.getPhone_no());
			st.setInt(5, c.getBalance());
			i = st.executeUpdate();
//			Log.debug("App::SQL query is send to Db for execution ");
		} catch (SQLException e1) {
//		    Log.error("App::Known Db problem");
			System.out.println("Creating account failed");
			e1.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (i > 1) {
//			Log.info("App:: Account is created");
			System.out.println("Account Created..");
		}
	}

	public void withdraw(int account_no) {
		int amount = 0;

		try {

			try {
				cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName, CustomerDao.Password);
//				Log.debug("App::JDBC Statement object is created");
				String checkQuery = "select * from customer where account_no = ? ";
				PreparedStatement preparedStatement = cn.prepareStatement(checkQuery);
//				Log.debug("App::JDBC Statement object is created");
				preparedStatement.setInt(1, account_no);
				ResultSet result = preparedStatement.executeQuery();
//				Log.debug("App::SQL query is send to Db for execution ");

				if (result.next()) {
					System.out.println("Current Balance = " + result.getInt("balance"));
					int balance = result.getInt("balance");
					System.out.println("Enter what amount you want ?");
					amount = scanner.nextInt();
					if (balance < amount)
						System.out.println("Sorry your balance is less than what you want ");
					else {
						try {
							cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName,
									CustomerDao.Password);
							String updateQuery = "update customer set balance = ? where account_no = ? ";
							preparedStatement = cn.prepareStatement(updateQuery);
							int newBalance = balance - amount;
							preparedStatement.setInt(1, newBalance);
							preparedStatement.setInt(2, account_no);

							int updateResult = preparedStatement.executeUpdate();
							if (updateResult == 1) {
								System.out.println("withdraw completed ");
								System.out.println("Your new Balance =  " + newBalance);
							}
						} catch (SQLException throwables) {
							System.out.println(" error from database from update withdraw  ");
						}

					}
				} else {
					System.out.println(" Customer not found ");
				}
				preparedStatement.close();
			} catch (SQLException throwables) {
				System.out.println(" error from database   ");
			}
		} catch (Exception e) {
			System.out.println("Sorry Input error ");
		}
	}

	public void deposit(int account_no) {
		{
			int amount = 0;

			try {

				try {

					if (getCustomerFullInformation(account_no) == null) {
						System.out.println(" Customer not found ");

					} else {

						ResultSet result = getCustomerFullInformation(account_no);
						System.out.println("Current Balance = " + result.getInt("balance"));
						int balance = result.getInt("balance");
						System.out.println("Enter what amount you want to deposit ?");
						amount = scanner.nextInt();
						if (amount <= 0) {
							System.out.println("Sorry You can't deposit that number");
						}
						try {
							cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName,
									CustomerDao.Password);
							String updateQuery = "update customer set balance = ? where account_no = ? ";
							PreparedStatement preparedStatement = cn.prepareStatement(updateQuery);
							int newBalance = balance + amount;
							preparedStatement.setInt(1, newBalance);
							preparedStatement.setInt(2, account_no);

							int updateResult = preparedStatement.executeUpdate();
							if (updateResult == 1) {
								System.out.println("deposit completed ");
								System.out.println("Your new Balance =  " + newBalance);
							}
						} catch (SQLException throwables) {
							System.out.println(" error from database from update deposit  ");
						}
					}

				} catch (SQLException throwables) {
					System.out.println(" error from database   ");
				}
			} catch (Exception e) {
				System.out.println("Sorry Input error ");
			}
		}
	}

	public void updateCustomerInformation(Customer c) {
		int i = 0;
		try {
			st = cn.prepareStatement(UPDATE_COMMAND);
			st.setLong(5, c.getAccount_no());
			st.setString(1, c.getCustomer_name());
			st.setString(2, c.getAddress());
			st.setLong(3, c.getPhone_no());
			st.setInt(4, c.getBalance());
			i = st.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Update operation failed .. unable to update ");
			e1.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (i > 0) {
			System.out.println("Customer updated succesfully");
		}

	}

	public void deleteCustomer(int account_no) {
		int i = 0;
		try {
			st = cn.prepareStatement(DELETE_COMMAND);
			st.setInt(1, account_no);
			i = st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete operation failed... unable to execute command");
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (i > 0) {
			System.out.println("Customer deleted succesfully");
		} else {
			System.out.println("Customer not found");
		}

	}

	public String findCustomer(int account_no) throws CusNotFoundException {
		String findcus = null;
		try {
			st = cn.prepareStatement(FIND_COMMAND);
			st.setInt(1, account_no);
			rs = st.executeQuery();
			while (rs.next()) {

				findcus = rs.getString("customer_name");
				System.out.println("The name is : " + findcus);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return findcus;
	}

	public int getBalance(int account_no) {
		int i = 0;
		try {
			st = cn.prepareStatement(BALANCE);
			st.setInt(1, account_no);
			rs = st.executeQuery();
			while (rs.next()) {
				System.out.println("Balance is : " + rs.getInt(5));
				System.out.println("Name is : " + rs.getString(2));
				System.out.println("City is : " + rs.getString(3));

				i = rs.getInt(1);

			}
		} catch (Exception e) {
			System.err.println("Employee not found");

		} finally {
			try {
				st.close();
				rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;

	}

	public List<Customer> showAllCustomers() {
		Customer selectAllTemp = null;
		List<Customer> elist = new LinkedList();
		try {
			st = cn.prepareStatement(SELECT_ALL);
			rs = st.executeQuery();
			while (rs.next()) {
				selectAllTemp = new Customer();
				selectAllTemp.setAccount_no(rs.getInt("account_no"));
				selectAllTemp.setCustomer_name(rs.getString("customer_name"));
				selectAllTemp.setAddress(rs.getString("address"));
				selectAllTemp.setPhone_no(rs.getInt("phone_no"));
				selectAllTemp.setBalance(rs.getInt("balance"));
				elist.add(selectAllTemp);
			}
			return elist;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkIfUserExists(int account_no) throws Exception {
		boolean isUserExists = false;
		cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName, CustomerDao.Password);
		String checkQuery = "select * from customer where account_no = ? ";
		PreparedStatement preparedStatement = cn.prepareStatement(checkQuery);
		preparedStatement.setInt(1, account_no);
		ResultSet result = preparedStatement.executeQuery();
		if (result.next()) {
			isUserExists = true;
		}
		return isUserExists;
	}

	public ResultSet getCustomerFullInformation(int account_no) throws Exception {
		ResultSet resultSet = null;
		cn = DriverManager.getConnection(CustomerDao.URL, CustomerDao.UserName, CustomerDao.Password);
		String checkQuery = "select * from customer where account_no = ? ";
		PreparedStatement preparedStatement = cn.prepareStatement(checkQuery);
		preparedStatement.setInt(1, account_no);
		ResultSet result = preparedStatement.executeQuery();
		if (result.next()) {
			resultSet = result;
		}
		return resultSet;
	}

}
