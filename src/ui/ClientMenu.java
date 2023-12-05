package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Date;

import dataBaseManager.ConnectionManager;
import ifaces.PatientManager;
import pojos.Patient;
import threads.ClientTCP;

public abstract class ClientMenu {

	private static PatientManager patientMan;
	private static Patient p;
	
	public static void menu(String email) {

		File file = null;
		//ConnectionManager conMan = new ConnectionManager();
 
		//patientMan = conMan.getPatientMan();
		//p = patientMan.getPatientByEmail(email);
		
		Patient p = new Patient(2,"Maria", "Carrasco", new Date(), "email"); //HAY QUE ALMACENAR PACIENTES EN BASE DE DATOS
		while (true) {
			//ALS: Amyotrophic Lateral Sclerosis (ELA)
			System.out.println("\nPatient ALS diagnostic menu: (patient: " + email + ")" 
					+ "\n 1. Register new data"
					+ "\n 2. Send data to server"
					+ "\n 3. Check doctor diagnostic"
					+ "\n 4. Change password"
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					System.out.println(" -Symptoms (press x to exit): ");
					while(true) {
						String s = Utilities.readString("   ");
						if(s.equals("x")) {
							break;
						} else {
							p.addSymptom(s);
						}
					}
					System.out.println(" -Record BITalino signal: ");
					
					String macAddress = Utilities.readString("   Bitalino MAC address: ");
					//macAddress = "20:17:11:20:51:27"
					int minutes = Utilities.readInteger("   Duration: ");
					p.recordBitalinoData(macAddress); //IMPLEMENTAR DURACION
					
					break;
				}
				case 2: {
					//enviar datos (file) al servidor
					try {
						file = p.almacenarDatosEnFichero();
						sendDataToServer(file);
					} catch (FileNotFoundException e) {
						System.out.println("ERROR: No data has been recorded yet.");
					}
					break;
				}
				case 3: {
					//recibir datos del servidor
					break;
				}
				case 4: {
					//cambiar contraseña
					break;
				}
				case 0: {
					System.out.println("Program terminated.");
					return;
				}
				default: {
					System.out.println(" ERROR: Invalid option.");
				}
			}
		}
	}

	//OPTION 2:
	private static void sendDataToServer(File file) {
		//Sent file to server:
		String serverIP = "10.60.84.189";
		int port = 9000;
		try {
			Socket socket = new Socket(serverIP, port);
		    ClientTCP client = new ClientTCP(socket, file);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}