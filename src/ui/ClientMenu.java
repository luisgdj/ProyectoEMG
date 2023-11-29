package ui;

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