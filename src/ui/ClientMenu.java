package ui;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import threads.ClientTCP;

public abstract class ClientMenu {

	public static void menu(String email) {

		File file = null;
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
					//introducir sintomas
					//grabar señal de bitalino
					file = new File("misc\\FileToRead.txt");
					//guardar datos en un fichero (variable file)
					break;
				}
				case 2: {
					//enviar datos (file) al servidor
					if(file != null) {
						sendDataToServer();
					}else {
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

	//OPTION 
	private static void sendDataToServer() {
		//Sent file to server:
		String serverIP = "10.60.85.53";
		int port = 9000;
		try {
			Socket socket = new Socket(serverIP, port);
		    File file = new File("misc\\FileToRead.txt");
		    ClientTCP server = new ClientTCP(socket, file);
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}