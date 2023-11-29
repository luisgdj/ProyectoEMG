package ui;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import threads.ClientTCP;
import threads.ServerTCP;

public abstract class ClientMenu {

	public static void menu(String email) {

		while (true) {
			System.out.println("\nEMG signal (patient menu: " + email + ")" 
					+ "\n 1. View signal"
					+ "\n 2. _____________" 
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					
					break;
				}
				case 2: {
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