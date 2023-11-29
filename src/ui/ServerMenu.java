package ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import threads.ServerTCP;

public abstract class ServerMenu {

	public static void menu() {

		try {
	    	ServerSocket serverSocket = new ServerSocket(9000);
	        while (true) {
	            //This executes when we have a client
	            Socket socket;
				socket = serverSocket.accept();
	            new Thread(new ServerTCP(socket)).start();
	            
	            System.out.println("Sever conection stablished!");
	        	while (true) {
	        		
					System.out.println("\nHospital ALS diagnostic menu (EMG signal):" 
							+ "\n 1. Register new patient"
							+ "\n 2. View patient information"
							+ "\n 3. Send patient diagnostic"
							+ "\n 4. Change password"
							+ "\n 0. Log out");
					int option = Utilities.readInteger("Choose an option: ");
		
					switch (option) {
						case 1: {
							//registrar un nuevo paciente
							break;
						}
						case 2: {
							//coger el fichero de informacion enviado por el paciente y visualizarlo
							break;
						}
						case 3: {
							//analizar fichero recibido y enviar diagnostico al paciente
							break;
						}
						case 4: {
							//cambiar contraseña
						}
						case 0: {
							System.out.println("Program terminated.");
							serverSocket.close();
							return;
						}
						default: {
							System.out.println(" ERROR: Invalid option.");
						}
					}
	        	}
			}
		} catch (IOException e) {
			Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
