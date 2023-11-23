package ui;

import userManager.JPAUserManager;
import ifaces.UserManager;
import pojos.User;

public abstract class LogInMenu {

	private static UserManager userMan;

	public static void main(String[] Args) {

		userMan = new JPAUserManager();
		System.out.print("\nWelcome to ECG analytics!");

		while (true) {
			System.out.println("\nLog-In menu:");
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");

			User user = userMan.logIn(username, password);
			// User user = userMan.logIn("server", "default0", "doctor@hospital.com");
			
			if (user != null) {
				if (user.getRole().getName().equals("server")) {
					ServerMenu.menu();
				}
				if (user.getRole().getName().equals("client")) {
					ClientMenu.menu(user.getEmail());
				}
			} else {
				System.out.println("Error: Wrong username or password.");
			}
		}
	}
}
