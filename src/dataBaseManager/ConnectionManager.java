package dataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import ifaces.*;

public class ConnectionManager {

	Connection c; // we define it outside because we use it in all the methods
	private static PatientManager patientMan;

	public ConnectionManager() {
		try {
			Class.forName("org.sqlite.JDBC"); // establish a connection with the database
			c = DriverManager.getConnection("jdbc:sqlite:./db/DataBase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();

			this.patientMan = new JDBCPatientManager(this);

		} catch (Exception e) {
			System.out.println("Database access error.");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	public static PatientManager getNurseMan() {
		return patientMan;
	}

	private void createTables() {

		try {
			Statement s = c.createStatement();
			String table;
			
			table = "CREATE TABLE patient(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "name TEXT NOT NULL,"
						+ "surname TEXT NOT NULL," 
						+ "email TEXT NOT NULL,"
						+ "dob DATE NOT NULL,"
						+ "symptoms JSON," 
						+ "frame JSON);";
			s.executeUpdate(table);

			s.close();

		} catch (SQLException e) {
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Database Error.");
			e.printStackTrace();
		}
	}
}
