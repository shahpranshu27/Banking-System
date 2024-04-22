package in.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountsManager {

	private Connection connection;
	private Scanner scanner;
	
	public AccountsManager(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
	public void credit_money(long account_number) throws SQLException {
		scanner.nextLine();
		System.out.println("Enter amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter security pin : ");
		String security_pin = scanner.nextLine();
		
		try {
			connection.setAutoCommit(false);
			if(account_number!=0) {
				PreparedStatement ps = connection.prepareStatement("select * from accounts where account_number=? and security_pin=?");
				ps.setDouble(1, account_number);
				ps.setString(2, security_pin);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					String credit_query = "update accounts set balance=balance+? where account_number=?";
					PreparedStatement ps1 = connection.prepareStatement(credit_query);
					ps.setDouble(1, amount);
					ps.setLong(2, account_number);
					int rows=ps.executeUpdate();
					if(rows>0) {
						System.out.println("Rs. "+amount+" credited successfully!");
						connection.commit();
						connection.setAutoCommit(true);
					}
					else {
						System.out.println("Transaction failed!");
						connection.rollback();
						connection.setAutoCommit(true);
					}
				}
				else {
					System.out.println("Invalid security pin!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}
	
	public void debit_money(long account_number) throws SQLException {
		scanner.nextLine();
		System.out.println("Enter amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter security pin : ");
		String security_pin = scanner.nextLine();
		
		try {
			connection.setAutoCommit(false);
			if(account_number!=0) {
				PreparedStatement ps = connection.prepareStatement("select * from accounts where account_number=? and security_pin=?");
				ps.setDouble(1, account_number);
				ps.setString(2, security_pin);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					double current_balance = rs.getDouble("balance");
					if(amount<=current_balance) {
						String debit_query = "update accounts set balance=balance-? where account_number=?";
						PreparedStatement ps1 = connection.prepareStatement(debit_query);
						ps.setDouble(1, amount);
						ps.setLong(2, account_number);
						int rows=ps.executeUpdate();
						if(rows>0) {
							System.out.println("Rs. "+amount+" debited successfully!");
							connection.commit();
							connection.setAutoCommit(true);
						}
						else {
							System.out.println("Transaction failed!");
							connection.rollback();
							connection.setAutoCommit(true);
						}
					}
					else {
						System.out.println("Insufficient balance!");
					}
				}
				else {
					System.out.println("Invalid pin!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}
	
	public void getBalance(long account_number) {
		scanner.nextLine();
		System.out.println("Enter security pin : ");
		String security_pin = scanner.nextLine();
		try {
			
		} catch (Exception e) {
			
		}
	}

}
