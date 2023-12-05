package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Scanner;

import pojos.Patient;

public abstract class Utilities {

	private static InputStreamReader isr = new InputStreamReader(System.in);
	private static BufferedReader br = new BufferedReader(isr);

	public static int readInteger(String question) {

		int num;
		String line;
		while (true) {
			try {
				System.out.print(question);
				line = br.readLine();
				num = Integer.parseInt(line);
				return num;
				
			} catch (IOException ioe) {
				System.out.println(" ERROR: Unable to read.");

			} catch (NumberFormatException nfe) {
				System.out.println(" ERROR: Must be a whole number.");
			}
		}
	}

	public static long readLong(String question) {

		long num;
		String line;
		while (true) {
			try {
				System.out.print(question);
				line = br.readLine();
				num = Long.parseLong(line);
				return num;
				
			} catch (IOException ioe) {
				System.out.println(" ERROR: Unable to read.");

			} catch (NumberFormatException nfe) {
				System.out.println(" ERROR: Must be a whole number.");
			}
		}
	}

	public static float readFloat(String question) {

		float num;
		String line;
		while (true) {
			try {
				System.out.print(question);
				line = br.readLine();
				num = Float.parseFloat(line);
				return num;

			} catch (IOException ioe) {
				System.out.println(" ERROR: Unable to read.");

			} catch (NumberFormatException nfe) {
				System.out.println(" ERROR: Must be a real number.");
			}
		}
	}

	public static String readString(String question) {

		String line;
		while (true) {
			try {
				System.out.print(question);
				line = br.readLine();
				return line;

			} catch (IOException ioe) {
				System.out.println(" ERROR: Unable to read.");
			}
		}
	}

	public static Date askDate(String question) {

		while (true) {
			System.out.println(question);
			int day = Utilities.readInteger("   Day: ");
			int month = Utilities.readInteger("   Month: ");
			int year = Utilities.readInteger("   Year: ");
			return new Date(year, month, day);
		}
		
	}
	
	 public static LinkedList<String> askSymptoms() {
	        LinkedList<String> symptoms = new LinkedList<>();
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Add simptoms, when finished right x):");

	        while (true) {
	            System.out.print("Síntoma: ");
	            String sintoma = scanner.nextLine();

	            if (sintoma.equalsIgnoreCase("x")) {
	                break;
	            }

	            symptoms.add(sintoma);
	        }
	        scanner.close();
	        return symptoms;
	    }
	
	public static  Patient askDataPatients () {
		
		System.out.println("Please input Your Data as a Patient");
		String name = Utilities.readString("Name: ");
		String surname = Utilities.readString("Surname: ");
		Date dob = Utilities.askDate("Date of Birth: ");
		String email = Utilities.readString("Email");
		LinkedList <String> symptoms = Utilities.askSymptoms();
		Patient p = new Patient(name, surname,dob,email,symptoms);
		System.out.println("The patient has been added");
		
		return p;
	}
	
	
	
}
