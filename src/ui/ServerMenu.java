package ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import threads.ServerTCP;

public abstract class ServerMenu {

	public static void menu() {

		while (true) {
		    try {
		    	ServerSocket serverSocket = new ServerSocket(9000);
		        while (true) {
		            //Thie executes when we have a client
		            Socket socket;
					socket = serverSocket.accept();
		            new Thread(new ServerTCP(socket)).start();
		            System.out.println("Sever conection stablished!");
		        }
		    } catch (IOException e) {
				e.printStackTrace();
			}
		    
		        
		    
			System.out.println("\nHospital menu (EMG signal):" 
					+ "\n 1. Register patient"
					+ "\n 2. Change password"
					+ "\n 3. ____________"
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					
					break;
				}
				case 2: {
					
					break;
				}
				case 3: {
					
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
}
