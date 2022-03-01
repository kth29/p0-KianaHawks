package com.revature.dao;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {
	Account acct = new Account();
	Scanner acctScan = new Scanner(System.in);

	public Account addAccount(Account a) {
		System.out.print("Enter Account Number : ");
		int accountId = acctScan.nextInt();
		System.out.print("Enter Account Type :");
		//need AccountType enum variable. Read up on this
		//AccountType type =acctScan.next(); that must return type a
		System.out.print("Enter Owner ID :");
		int ownerId = acctScan.nextInt();
	
	
	}	

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
