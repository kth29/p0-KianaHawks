package com.revature.dao;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {
	Account acct = new Account();
	Scanner aa = new Scanner(System.in);

	public Account addAccount(Account a) {
		System.out.print("Enter Account Number : ");
		accountId = aa.next();
		System.out.print("Enter Account Type :");
		type = aa.next();
		System.out.print("Enter Owner ID :");
		ownerId = aa.close();
	
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
