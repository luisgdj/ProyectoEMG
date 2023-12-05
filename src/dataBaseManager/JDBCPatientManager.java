package dataBaseManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BITalino.Frame;
import ifaces.PatientManager;
import pojos.*;

public class JDBCPatientManager implements PatientManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void insertPatient(Patient patient) {

		try {
			String sql = "INSERT INTO patient (name, surname, email, dob)" + " VALUES (?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, patient.getName());
			p.setString(2, patient.getSurname());
			p.setString(3, patient.getEmail());
			p.setDate(4, patient.getDob());

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public Patient getPatient(int id) {

		try {
			String sql = "SELECT * FROM patient WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + id);
			ResultSet rs = p.executeQuery();

			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String email = rs.getString("email");
			Date dob = rs.getDate("dob");
			//LinkedList<String> symptoms = rs.getString("symptoms");
			//Frame[] frame = rs.getArray("symptoms");

			p.close();
			rs.close();
			return new Patient(id, name, surname, dob, email, null, null);

		} catch (SQLException e) {
			System.out.println(" ERROR: donor does not exist.");
			return null;
		}
	}
	
	@Override
	public Patient getPatientByEmail(String email) {

		try {
			String sql = "SELECT * FROM patient WHERE email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();

			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date dob = rs.getDate("dob");
			//LinkedList<String> symptoms = rs.getString("symptoms");
			//Frame[] frame = rs.getArray("symptoms");

			p.close();
			rs.close();
			return new Patient(id, name, surname, dob, email, null, null);

		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Patient> getAllPatients() {

		List<Patient> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM patient";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String email = rs.getString("email");
				//LinkedList<String> symptoms = rs.getString("symptoms");
				//Frame[] frame = rs.getArray("symptoms");
				
				Patient patient = new Patient(id, name, surname, dob, email, null, null);
				list.add(patient);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Patient> getPatientsByName(String name) {

		List<Patient> list = new LinkedList<Patient>();
		try {
			String sql = "SELECT * FROM patient WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String email = rs.getString("email");
				//LinkedList<String> symptoms = rs.getString("symptoms");
				//Frame[] frame = rs.getArray("symptoms");
				
				Patient patient = new Patient(id, name, surname, dob, email, null, null);
				list.add(patient);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}
}