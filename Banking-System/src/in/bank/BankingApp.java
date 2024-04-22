package in.bank;

import java.awt.desktop.UserSessionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
	private static String url = "jdbc:mysql://localhost:3306/banking";
	private static String username = "root";
	private static String password = "Rays@123";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Scanner scanner = new Scanner(System.in);
			
			User user= new User(connection, scanner);
			Accounts accounts = new Accounts(connection, scanner);
			AccountsManager accountsManager= new AccountsManager(connection, scanner);
			
			String email;
			long account_number;
			
			while (true) {
				
			}
			
		} catch (Exception e) {
			
		}
	}
}
