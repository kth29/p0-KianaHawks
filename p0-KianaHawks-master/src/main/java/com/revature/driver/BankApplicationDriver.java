package com.revature.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.User;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {

	public static void main(String[] args) {
		// your code here...
		
		
		
		int choice =0;
		Scanner input = new Scanner(System.in);
		
		while(choice>4) {
			System.out.println("*********************************************************************************************");
			System.out.println("*********************************************************************************************");
			System.out.println("*****\t\t\t\t\t\t\t\t\t\t\t*****");
			System.out.println("*****\t\t\t\t WELCOME TO KIANA'S BANKING APP! \t\t\t\t*****");
			System.out.println("*****\t\t\t\t\t\t\t\t\t\t\t*****");
			System.out.println("*********************************************************************************************");
			System.out.println("*********************************************************************************************");
			System.out.println("*****\t\t\t\tChoose From Options Below \t\t\t\t*****");
			System.out.println("");
			System.out.println("\t\t\t\t 1. Employee Login");
			System.out.println("\t\t\t\t 2. Customer Login");
			System.out.println("\t\t\t\t 3. Register New User");
			System.out.println("\t\t\t\t 4. Exit");
			System.out.println(" ");
			System.out.print("Enter Choice [1-2] :");
			choice= input.nextInt();
			
			switch (choice) {
			
			case 1:		//employee login
				Scanner regNewUser = new Scanner(System.in);
				
				String firstName = null;
				String lastName = null;
				String userName = null;
				String password = null;
				
				System.out.print("Enter First Name:");
				firstName = regNewUser.next();
				System.out.print("Enter Last Name:");
				lastName = regNewUser.next();
				System.out.print("Create Username:");
				userName = regNewUser.next();
				System.out.print("Create Password:");
				password = regNewUser.next();
				
				System.out.println();
				System.out.println("Your Account Has Been Created!");
				System.out.println("Full Name: " + firstName + " " + lastName);
				System.out.println("Username: " +userName + "\t Password: " +password);
				
				regNewUser.close();
				break;
			
			case 2:		//customer login
				
				break;
				
			case 3:		//reg new user
				
				/*User newUser = new User();
				System.out.println("Enter Last Name: ");
				newUser.setLastName(input.nextLine()); */
	
				
		
				break;
				
			case 4:		//Exit
				System.out.println("Thanks for visiting!");
				System.exit(0);
				break;
				
			default:
				break;
			}
		}
		
		input.close();
		}


}
