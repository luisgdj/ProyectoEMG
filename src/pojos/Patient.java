package pojos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.Frame;

public class Patient {

	private Integer id;
	private String name;
	private String surname;
	private Date dob;
	private String email;
	private LinkedList<String> symptoms;
	private Frame[] frame;

	public Patient(String name, String surname, Date dob, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.email = email;
		this.symptoms = new LinkedList<>();
	}
	
	public Patient(Integer id, String name, String surname, Date dob, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.email = email;
		this.symptoms = new LinkedList<>();
	}
	
	public Patient(Integer id, String name, String surname, Date dob, String email, LinkedList<String> s, Frame[] f) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.email = email;
		this.symptoms = s;
		this.frame = f;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LinkedList<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(LinkedList<String> symptoms) {
		this.symptoms = symptoms;
	}

	public Frame[] getFrame() {
		return frame;
	}

	public void setFrame(Frame[] frame) {
		this.frame = frame;
	}

	public void addSymptom(String s) {
		symptoms.add(s);
	}
	
	public File almacenarDatosEnFichero() throws FileNotFoundException {
		
		Date date = (Date) java.sql.Date.valueOf(LocalDate.now());
		long time = date.getTime();
		File file = new File("misc\\" + name + "_" + surname + "-" + date + "(" + time + ").txt");
		
		PrintWriter pw = new PrintWriter(file);
		pw.println(date);
		pw.println("Symptoms:");
		for(String s : symptoms) {
			pw.println(" -" + s);
		}
		pw.println("Bitalino recorded data:");
		for (Frame frame : frame) {
			pw.println(" " + frame.seq);
		}
		return file;
		
		/*
        System.out.println("size block: " + frame.length);
        //Print the samples
        for (int j = 0; j < frame.length; j++) {
            System.out.println((i * block_size + j) + " seq: " + frame[j].seq + " " + frame[j].analog[0] + " ");
        }
        */
	}
	
	public void recordBitalinoData(String macAddress) {
		
		BITalino bitalino = new BITalino();
        try {
    		Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);
            
            int samplingRate = 100;
            bitalino.open(macAddress, samplingRate);   
            
            int[] channelsToAcquire = {0};
            bitalino.start(channelsToAcquire);

            for (int i=0; i<10000000; i++) {
                frame = bitalino.read(samplingRate);
            }
            bitalino.stop();
            
        } catch (BITalinoException ex) {
            ex.printStackTrace();
        } catch (Throwable ex) {
        	ex.printStackTrace();
        } finally {
            try {
                //Connection stops when we disconnect the bluetooth
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
            	ex.printStackTrace();
            }
        }
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
		return " -ID: " + id + "\n -Name: " + name + " " + surname + "\n -Birth date: " + dob;
	}
}
