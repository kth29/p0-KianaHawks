package com.revature.dao;

import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to files
 */
public class AccountDaoFile implements AccountDao {
	// use this file location to persist the data to
	public static String fileLocation = "";

	@Override
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> getPendingAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
