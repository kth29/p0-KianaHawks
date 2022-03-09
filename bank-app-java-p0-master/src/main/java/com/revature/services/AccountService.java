package com.revature.services;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.UnauthorizedException;

/**
 * This class should contain the business logic for performing operations on
 * Accounts
 */
public class AccountService {

	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;

	Scanner sc = new Scanner(System.in);
	double amount;
	double balance;
	int acctno;
	Account a = actDao.getAccount(acctno);

	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}

	/**
	 * Withdraws funds from the specified account
	 * 
	 * @throws OverdraftException            if amount is greater than the account
	 *                                       balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {
		if (!a.isApproved()) {
			throw new UnsupportedOperationException();
		}

		System.out.println("Enter Account Number :");
		a = actDao.getAccount(sc.nextInt());
		System.out.println("Enter Withdrawl Amount :");
		amount = sc.nextDouble();

		if (amount != 0) {
			balance = balance - amount;
			System.out.println("You have withdrawn " + amount);
			System.out.println("New balance = " + balance);

			// add updateAccount method
			// add to transaction list
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Deposit funds to an account
	 * 
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) {
		if (!a.isApproved()) {
			throw new UnsupportedOperationException();
		}

		System.out.println("Enter account number :");
		a = actDao.getAccount(sc.nextInt());
		System.out.println("Enter amount to deposit :");
		amount = sc.nextDouble();
		if (amount != 0 && !<0) {
			balance = balance + amount;
			System.out.println("You have deposited " + amount);
			System.out.println("New balance = " + balance);

			// add updateAccount method
			// add to transaction list
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Transfers funds between accounts
	 * 
	 * @throws UnsupportedOperationException if amount is negative or the
	 *                                       transaction would result in a negative
	 *                                       balance for either account or if either
	 *                                       account is not approved
	 * @param fromAct the account to withdraw from
	 * @param toAct   the account to deposit to
	 * @param amount  the monetary value to transfer
	 */
	public void transfer(Account fromAct, Account toAct, double amount) {
		int fa;
		int ta;
		
		System.out.println("Transfer FROM Account Number :");
		fa = sc.nextInt();
		fromAct = actDao.getAccount(fa);
		
		System.out.println("Transfer TO Account Number :");
		ta = sc.nextInt();
		toAct = actDao.getAccount(ta);
		
		System.out.println("Enter Amount to Transfer :");
		amount = sc.nextDouble();
		
		if

		// add updateAccount method
		// add to transaction list

	}

	/**
	 * Creates a new account for a given User
	 * 
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
		UserDaoDB udao = new UserDaoDB();

		udao.getUser(null);
		actDao.addAccount(null);
		return null;
	}

	/**
	 * Approve or reject an account.
	 * 
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Employee
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(Account a, boolean approval) {

		return false;
	}
}
