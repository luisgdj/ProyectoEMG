package pojos;

import java.io.File;
import java.sql.Date;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.bluetooth.RemoteDevice;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.BitalinoDemo;
import BITalino.Frame;

public class Patient {

	private Integer id;
	private String name;
	private String surname;
	private String condition;
	private Date dob;
	private long ssn;
	private Frame[] frame;
	//private EMGSignal signal;

	public Patient(String name, String surname, String condition, Date dob, long ssn) {
		super();
		this.name = name;
		this.surname = surname;
		this.condition = condition;
		this.dob = dob;
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return " -ID: " + id + "\n -Name: " + name + " " + surname + "\n -Blood type: " + condition + "\n -Birth date: "
				+ dob + "\n -SSN: " + ssn;
	}
	
}
