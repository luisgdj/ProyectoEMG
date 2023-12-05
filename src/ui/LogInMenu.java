package ui;

import dataBaseManager.JPAUserManager;
import ifaces.UserManager;
import pojos.User;

public abstract class LogInMenu {

	private static UserManager userMan;

	public static void main(String[] Args) {

		userMan = new JPAUserManager();
		System.out.print("\nWelcome to EMG analytics!");

		while (true) {
			System.out.println("\nLog-In menu:");
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");

			User user = userMan.logIn(username, password);
			// User server = userMan.logIn("server", "default0", "server@hospital.com");
			// User client = userMan.logIn("client", "default0", "server@hospital.com");
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
