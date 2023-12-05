package ifaces;

import java.util.List;
import pojos.Patient;

public interface PatientManager {

	public void insertPatient(Patient patient);
	public Patient getPatient(int id);
	public Patient getPatientByEmail(String email);
	
	public List<Patient> getAllPatients();
	public List<Patient> getPatientsByName(String name);
}

