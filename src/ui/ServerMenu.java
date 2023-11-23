package ui;

public abstract class ServerMenu {

	public static void menu() {

		while (true) {
			System.out.println("\nHespital menu (ECG signal):" 
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
