package com.revature.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.TransactionDaoDB;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.AccountService;
import com.revature.services.UserService;
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
		UserDaoDB udao = new UserDaoDB();
		AccountDaoDB adao = new AccountDaoDB();
		TransactionDaoDB tdao = new TransactionDaoDB();
		UserService userv = new UserService(udao, adao);
		AccountService aserv = new AccountService(adao);
		Account actobj = new Account();
		Account actobj2 = new Account();

		String firstName;
		String lastName;
		String username;
		String password;
		int toAcct;
		int fromAcct;
		double amount;

		System.out.println(
				"+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
		System.out.println(
				"+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
		System.out.println("+=+=+=+=+=+=+=+=+=+=+= \t\t\t\t                         \t\t\t +=+=+=+=+=+=+=+=+=+=+=+=");
		System.out.println("+=+=+=+=+=+=+=+=+=+=+= \t\t\t\t Welcome to Kiana's Bank \t\t\t +=+=+=+=+=+=+=+=+=+=+=+=");
		System.out.println("+=+=+=+=+=+=+=+=+=+=+= \t\t\t\t                         \t\t\t +=+=+=+=+=+=+=+=+=+=+=+=");
		System.out.println(
				"+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
		System.out.println(
				"+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice < 4) {

			System.out.println("\n\n1. Register New User");
			System.out.println("2. Employee Login");
			System.out.println("3. Customer Login");
			System.out.println("4. Exit Application");
			System.out.println();
			System.out.print("Enter choice [1-4] :");
			choice = sc.nextInt();

			switch (choice) {

			case 1: // reg new user
				int result;
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
				System.out.println("If you are an Employee, Enter 1. If you are a Customer, Enter 2 :");
				result = sc.nextInt();
				switch (result) {
				case 1:
					user.setUserType(UserType.EMPLOYEE);
					break;

				case 2:
					user.setUserType(UserType.CUSTOMER);
					break;
				}

				udao.addUser(user);

				System.out.println("\n New User Has Been Registered! \n");

				System.out.println(
						"========================================================================================================================");
				System.out.println("\t\t\t SIGN IN TO GET YOUR USER ID!!!");

				System.out.println("\n\nChoose from the options below [1-4] :");
				System.out.println(
						"======================================================================================================================== \n\n");

				break;
			case 2: // emp login

				int option = 0;

				System.out.print("Enter Employee Username :");
				username = sc.next();
				user.setUsername(username);
				System.out.print("Enter Employee Password :");
				password = sc.next();
				user.setPassword(password);

				User logUser = userv.login(username, password);

				if (logUser.getUserType().toString() != "EMPLOYEE") {
					System.out.println(
							"========================================================================================================================");
					System.out.println("\t\t\t\t\t Invalid Credentials!");
					System.out.println(
							"========================================================================================================================");
					throw new InvalidCredentialsException();
				}

				System.out.println("\n You Are Logged In : " + logUser);

				while (logUser != null) {

					System.out.println();
					System.out.println();
					System.out.print("Login Successful! \n\n");
					SessionCache.setCurrentUser(logUser);

					System.out.print("\n Welcome " + logUser.getFirstName() + " ! \n\n");
					System.out.println(
							"========================================================================================================================");
					System.out.println("\t\t\t\t\t\t **YOUR INFO**");
					System.out.println("\t\t\t Name: " + logUser.getFirstName() + " " + logUser.getLastName()
							+ "\t Username: " + logUser.getUsername() + "\t User ID: " + logUser.getId());
					System.out.println(
							"========================================================================================================================");
					while (option <= 6) {
						System.out.print("\n\n What would you like to do? \n");

						System.out.println("1. Update Personal Information");
						System.out.println("2. Review Accounts Pending Approval");
						System.out.println("3. View All Accounts");
						System.out.println("4. View All Tranaction History");
						System.out.println("5. Delete Customer Account");
						System.out.println("6. Log out");
						System.out.println();
						System.out.print("Enter option [1-6] :");
						option = sc.nextInt();

						switch (option) {
						case 1: // update personal info

							int choice2;

							System.out.print("\n\n Confirm Your User ID :");
							toAcct = sc.nextInt();

							System.out.println("\n\n Which would you like to update?");
							System.out.println("1. Name");
							System.out.println("2. Username");
							System.out.println("3. Password");
							System.out.println("4. Back to Main Page \n");
							System.out.print("Enter Choice : ");
							choice2 = sc.nextInt();

							if (choice2 <= 4) {
								switch (choice2) {
								case 1: // update name
									System.out.print("\n Update First Name : ");
									firstName = sc.next();
									logUser.setFirstName(firstName);

									System.out.print("\n Update Last Name : ");
									lastName = sc.next();
									logUser.setLastName(lastName);

									udao.updateUser(logUser);
									System.out.println("\n Name Has Been Updated!");
									break;

								case 2: // update username
									System.out.print("\n Update Username : ");
									username = sc.next();
									logUser.setUsername(username);

									udao.updateUser(logUser);
									System.out.println("\n Username Has Been Updated!");
									break;

								case 3: // update password
									System.out.println("\n Update Password : ");
									password = sc.next();
									logUser.setPassword(password);
									udao.updateUser(logUser);
									System.out.println("\n Password Has Been Updated!");

									break;

								case 4: // back to main page
									System.exit(0);
									break;
								}
							}

							break;

						case 2: // review accounts pending approval

							System.out.println(
									"========================================================================================================================");
							System.out.println("List of Pending Accounts");
							adao.getPendingAccounts().forEach(System.out::println);
							System.out.println(
									"========================================================================================================================");

							System.out.print(
									"\n\n Would you like to approve any accounts? Select [1] for Yes or [2] for No.");
							int yon = sc.nextInt();

							if (yon == 1) {
								System.out.print("\n\n Enter an account number :");
								int actno = sc.nextInt();

								System.out.println();

								Account act1 = new Account();
								act1.setId(actno);

								System.out.print("\n\nYou want to APPROVE this account? Enter TRUE or FALSE :");
								boolean approval = sc.nextBoolean();

								aserv.approveOrRejectAccount(act1, approval);

							} else if (yon == 2) {
								// System.exit(0);
							}

							break;

						case 3: // view all accounts
							System.out.println(
									"========================================================================================================================");
							System.out.println("List of All Accounts");
							adao.getAccounts().forEach(System.out::println);
							System.out.println(
									"========================================================================================================================");

							// System.out.println("\n\n Would you like to update or delete any accounts?");
							break;

						case 4: // View All Transaction history
							System.out.println(
									"========================================================================================================================");
							System.out.println("List of All Transactions");
							tdao.getAllTransactions().forEach(System.out::println);
							System.out.println(
									"========================================================================================================================");
							break;

						case 5: // delete user
							System.out.println(
									"========================================================================================================================");
							System.out.println("List of All Users");
							udao.getAllUsers().forEach(System.out::println);
							System.out.println(
									"========================================================================================================================");
							System.out.print("\n\n Enter the ID to Deleter the User : ");
							int uid = sc.nextInt();
							User u = new User();
							u.setId(uid);
							udao.removeUser(u);

							break;

						case 6: // log out
							SessionCache.setCurrentUser(null);
							System.out.println("You have been logged out.");
							System.exit(0);
							break;

						default:
							System.out.println("Choose An Option [1-6]");
							break;
						}
					}

					break;
				}

			case 3: // cust login

				// int option2 = 0;

				System.out.print("Enter Username :");
				username = sc.next();
				user.setUsername(username);
				System.out.print("Enter Password :");
				password = sc.next();
				user.setPassword(password);

				User logUser1 = userv.login(username, password);

				if (logUser1.getUserType().toString() != "CUSTOMER") {
					System.out.println(
							"========================================================================================================================");
					System.out.println("\t\t\t\t Invalid Credentials!");
					System.out.println(
							"========================================================================================================================");
					throw new InvalidCredentialsException();
				}

				System.out.println("You Are Logged In : " + logUser1);

				while (logUser1 != null) {

					System.out.println();
					System.out.println();
					System.out.print("Login Successful! \n\n");
					SessionCache.setCurrentUser(logUser1);
					System.out.println(
							"========================================================================================================================");
					System.out.print("Welcome " + logUser1.getFirstName() + " ! \n\n");

					System.out.println("\t\t\t\t\t\t **YOUR INFO**");
					System.out.println("\t\t\t Name: " + logUser1.getFirstName() + " " + logUser1.getLastName()
							+ "\t Username: " + logUser1.getUsername() + "\t User ID: " + logUser1.getId());
					System.out.println(
							"========================================================================================================================");

					System.out.print("\n\n What would you like to do? \n\n");

					System.out.println("1. Update Personal Information");
					System.out.println("2. View Accounts & Balances");
					System.out.println("3. Make a Deposit");
					System.out.println("4. Make a Withdrawl");
					System.out.println("5. Make a Transfer");
					System.out.println("6. Apply for New Checking or Savings Account");
					System.out.println("7. Log Out");
					System.out.println();
					System.out.print("Enter option [1-7] :");
					option = sc.nextInt();

					while (option < 7) {
						switch (option) {
						case 1: // update personal info

							int choice2;

							System.out.print("\n\n Confirm Your User ID :");
							toAcct = sc.nextInt();

							System.out.println("\n\n Which would you like to update? \n");
							System.out.println("1. Name");
							System.out.println("2. Username");
							System.out.println("3. Password");
							System.out.println("4. Back to Main Page \n");
							System.out.print("Enter Choice : ");
							choice2 = sc.nextInt();

							if (choice2 < 4) {
								switch (choice2) {
								case 1: // update name
									System.out.print("\n Update First Name : ");
									firstName = sc.next();

									System.out.print("\n Update Last Name : ");
									lastName = sc.next();

									udao.updateUser(logUser1);
									System.out.println("\n Name Has Been Updated!");
									break;

								case 2: // update username
									System.out.print("\n Update Username : ");
									username = sc.next();
									udao.updateUser(logUser1);
									System.out.println("\n Username Has Been Updated!");
									break;

								case 3: // update password
									System.out.println("\n Update Password : ");
									password = sc.next();
									udao.updateUser(logUser1);
									System.out.println("\n Password Has Been Updated!");

									break;

								case 4: // back to main page
									break;
								}
							}

							break;

						case 2: // view accts & balances
							System.out.println(
									"========================================================================================================================");
							System.out.println("\t\t\t\t\t List of Accounts");
							adao.getAccountsByUser(logUser1).forEach(System.out::println);
							System.out.println(
									"========================================================================================================================");

							break;

						case 3: // deposit
							System.out.println(
									"========================================================================================================================");

							System.out.println("\t\t\t\t\t ***Make A Deposit***");

							System.out.println("Available Accounts");
							adao.getAccountsByUser(logUser1).forEach(System.out::println);

							System.out.print("\n\n Enter Account Number : ");
							toAcct = sc.nextInt();
							System.out.print("Enter Amount : $");
							amount = sc.nextDouble();

							actobj = adao.getAccount(toAcct);

							aserv.deposit(actobj, amount);

							break;

						case 4: // withdraw
							System.out.println("\t\t\t\t\t ***Make A Withdraw***");

							System.out.print("Enter Account Number : ");
							toAcct = sc.nextInt();
							System.out.print("Enter Amount : $");
							amount = sc.nextDouble();

							actobj = adao.getAccount(toAcct);

							aserv.withdraw(actobj, amount);

							System.out.println(actobj);

							break;

						case 5: // transfer

							System.out.println("\t\t\t\t\t ***Make a Transfer***");

							System.out.print("Enter Sender Account Number : ");
							fromAcct = sc.nextInt();
							System.out.print("Enter Recipient Account Number : ");
							toAcct = sc.nextInt();
							System.out.print("Enter Amount : $");
							amount = sc.nextDouble();

							actobj = adao.getAccount(toAcct);

							actobj2 = adao.getAccount(fromAcct);

							aserv.transfer(actobj2, actobj, amount);

							System.out.println(actobj2);
							System.out.println(actobj);

							break;

						case 6: // apply for new acct

							System.out.println("\t\t\t\t\t ***Apply for New Account***");
							System.out.print("\n Enter [1] for Checking or [2] for Savings :");
							int actType = sc.nextInt();
							System.out.print("\n Enter Initial Deposit Amount : $");
							double sbal = sc.nextDouble();
							Account acct1 = new Account();

							if (actType == 1) {
								acct1.setType(AccountType.CHECKING);
							} else if (actType == 2) {
								acct1.setType(AccountType.SAVINGS);
							}
							acct1.setBalance(sbal);
							acct1.setOwnerId(logUser1.getId());

							List<Account> acctList = new ArrayList<Account>();
							acctList.add(acct1);
							logUser1.setAccounts(acctList);
							adao.addAccount(acct1);

							break;

						case 7: // log out
							System.out.println("You have been logged out.");
							break;

						default:
							break;

						}
					}

					break;

				}

			case 4: // exit
				System.out.println("Thank you for using Kiana's Bank!");

				break;

			default:
				System.exit(0);

				break;
			}

		}

		sc.close();

	}

}
