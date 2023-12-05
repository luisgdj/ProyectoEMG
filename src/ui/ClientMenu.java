package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import dataBaseManager.ConnectionManager;
import ifaces.PatientManager;
import pojos.Patient;
import threads.ClientTCP;

public abstract class ClientMenu {

	private static PatientManager patientMan;
	private static Patient p;
	
	public static void menu(String email) {

		File file = null;
		ConnectionManager conMan = new ConnectionManager();
 
		patientMan = conMan.getPatientMan();
		p = patientMan.getPatientByEmail(email);
		
		Patient p = null; //HAY QUE ALMACENAR PACIENTES EN BASE DE DATOS
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
	private static File sendDataToServer() {
		//Sent file to server:
		String serverIP = "10.60.85.53";
		File file = new File("/misc/FileForServer.txt");
		int port = 9000;
		try {
			Socket socket = new Socket(serverIP, port);
		    ClientTCP server = new ClientTCP(socket, file);
		    return file;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}