package com.revature.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.UserDaoDB;
import com.revature.utils.SessionCache;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {

	public static void main(String[] args) {

		// your code here...

		try {

			File file = new File("userList.txt");

			FileOutputStream f = new FileOutputStream(file, true);
			ObjectOutputStream o = new ObjectOutputStream(f);

			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);

			o.close();
			oi.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user = new User();
		UserDaoDB newUser = new UserDaoDB();

		String firstName;
		String lastName;
		String username;
		String password;
		Int id;
		boolean result;

		System.out.println("Welcome to Kiana's Bank \n\n\n");

		Scanner sc = new Scanner(System.in);

		int choice = 0;

		while (choice < 4) {

			System.out.println("1. Register New User");
			System.out.println("2. Employee Login");
			System.out.println("3. Customer Login");
			System.out.println("4. Exit Application");
			System.out.println();
			System.out.print("Enter choice [1-4] :");
			choice = sc.nextInt();

			switch (choice) {

			case 1: // reg new user
				System.out.print("Enter First Name : ");
				firstName = sc.next();
				user.setFirstName(firstName);
				System.out.print("Enter Last Name : ");
				lastName = sc.next();
				user.setLastName(lastName);
				System.out.print("Create Username :");
				username = sc.next();
				user.setUsername(username);
				System.out.print("Create Password :");
				password = sc.next();
				user.setPassword(password);
				System.out.println("Are you an Employee ? [Type True or False] :");
				result = sc.nextBoolean();
				if (result = false) {
					user.setUserType(UserType.EMPLOYEE);
				} else {
					user.setUserType(UserType.CUSTOMER);
				}

				newUser.addUser(user);

				System.out.println("\n New User Has Been Registered! \n");

				System.out.println(
						"========================================================================================================================");
				System.out.println("SIGN IN TO GET YOUR USER ID!!!");

				System.out.println("\n\n Choose from the options below [1-4] :");
				System.out.println(
						"======================================================================================================================== \n\n");

				break;
			case 2: // emp login

				int option = 0;

				System.out.print("Enter Username :");
				username = sc.next();
				user.setUsername(username);
				System.out.print("Enter Password :");
				password = sc.next();
				user.setPassword(password);

				newUser.getUser(username, password);

				System.out.println();
				System.out.println();
				System.out.print("Login Successful! \n\n");
				SessionCache.setCurrentUser(null);

				System.out.print("Welcome " + user.getFirstName() + " ! \n\n");
				System.out
						.println("Your Info : " + user.getFirstName() + " " + user.getLastName() + " " + user.getId());

				System.out.print("\n\n What would you like to do? \n");

				System.out.println("1. Update Personal Information");
				System.out.println("2. Review Accounts Pending Approval");
				System.out.println("3. View Tranaction History");
				System.out.println("4. Delete Customer Account");
				System.out.println("5. Log out");
				System.out.println();
				System.out.println("Enter option [1-5] :");
				option = sc.nextInt();

				while (option < 5) {
					switch (option) {
					case 1: // update personal info
						System.out.println("Confirm Your User ID :");
						id = sc.nextInt();

						break;

					case 2: // review accounts pending approval

						break;

					case 3: // View Transaction history

						break;

					case 4: // delete user

						break;

					case 5: // log out
						System.out.println("You have been logged out.");
						System.exit(0);

						break;
					}
				}

				break;

			case 3: // cust login

				int opt = 0;

				System.out.print("Enter Username :");
				username = sc.next();
				user.setUsername(username);
				System.out.print("Enter Password :");
				password = sc.next();
				user.setPassword(password);

				newUser.getUser(username, password);

				System.out.println();
				System.out.println();
				System.out.print("Login Successful! \n\n");

				System.out.print("Welcome " + username + " ! \n\n");

				System.out.print("What would you like to do?");
				System.out.println();
				System.out.println("1. Update Personal Information");
				System.out.println("2. View Balance");
				System.out.println("3. Make a Deposit");
				System.out.println("4. Make a Withdrawl");
				System.out.println("5. Make a Transfer");
				System.out.println("6. Apply for New Checking or Savings Account");
				System.out.println("7. Log Out");
				System.out.println();
				System.out.println("Enter option [1-7] :");
				option = sc.nextInt();

				while (opt < 7) {
					switch (opt) {
					case 1: // update personal info

						break;

					case 2: // view balance

						break;

					case 3: // deposit

						break;

					case 4: // withdraw

						break;

					case 5: // transfer

						break;

					case 6: // apply for new acct

						break;

					case 7: // log out
						System.out.println("You have been logged out.");
						System.exit(0);

						break;
					}
				}

				break;

			case 4: // exit
				System.out.println("Thank you for using Kiana's Bank!");
				System.exit(0);

				break;

			default:

				break;
			}

		}

		sc.close();

	}

}
