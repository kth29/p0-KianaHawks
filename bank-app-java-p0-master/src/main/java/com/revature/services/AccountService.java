package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDaoDB;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.UnauthorizedException;

/**
 * This class should contain the business logic for performing operations on
 * Accounts
 */
public class AccountService {

	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;

	public TransactionDaoDB tDao;

	Scanner sc = new Scanner(System.in);
	double amount;
	double balance;
	double newBal;
	int acctno;
	Account a = actDao.getAccount(acctno);

	Transaction tran = new Transaction();
	List<Transaction> tranList = new ArrayList<Transaction>();

	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}

	private static Connection conn;
	private static PreparedStatement pst;
	private static Statement st;
	private static ResultSet rs;

	public static void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0mac", "root", "mysqlroot123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public AccountService (TransactionDao tdao) { this.tDao = (TransactionDaoDB)
	 * tdao; }
	 */

	/**
	 * Withdraws funds from the specified account
	 * 
	 * @throws OverdraftException            if amount is greater than the account
	 *                                       balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {

		if (!a.isApproved()) {
			System.out.println("Your Account is Not Approved for Transactions!");
			throw new UnsupportedOperationException();
		} else if (amount > a.getBalance()) {
			System.out.println("Insufficient Funds!");
			throw new OverdraftException();
		} else if (amount < 0) {
			System.out.println("Cannot Withdraw a Negative Amount!");
			throw new UnsupportedOperationException();
		} else {
			a.setBalance(a.getBalance() - amount);

			System.out.println(
					"=============================================================================================================================================");
			System.out.println("\n Withdraw Successful! \n");
			System.out.println("Your New Balance is $" + a.getBalance());
			System.out.println(
					"=============================================================================================================================================");
		}
		tran.setSender(a);
		tran.setType(TransactionType.WITHDRAWAL);
		tran.setAmount(amount);
		tran.setTimestamp();

		tranList.add(tran);
		a.setTransactions(tranList);

		tDao.addTransaction(tran);

	}

	/**
	 * Deposit funds to an account
	 * 
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) {

		if (!a.isApproved()) {
			System.out.println("Your Account is Not Approved for Transactions!");
			throw new UnsupportedOperationException();
		} else if (amount <= 0) {
			System.out.println("Cannot Deposit This Amount!");
			throw new UnsupportedOperationException();
		} else {
			a.setBalance(a.getBalance() + amount);

			System.out.println(
					"=============================================================================================================================================");
			System.out.println("\n Deposit Successful! \n");
			System.out.println("Your New Balance is $" + a.getBalance());
			System.out.println(
					"=============================================================================================================================================");
		}
		tran.setSender(a);
		tran.setType(TransactionType.DEPOSIT);
		tran.setAmount(amount);
		tran.setTimestamp();

		tranList.add(tran);
		a.setTransactions(tranList);

		tDao.addTransaction(tran);

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

		if (amount > fromAct.getBalance()) {
			System.out.println("Insufficient Funds!");
			throw new UnsupportedOperationException();
		} else if (amount < 0.0) {
			System.out.println("Amount Cannot Be Negative!");
			throw new UnsupportedOperationException();
		} else if (!fromAct.isApproved()) {
			System.out.println("Your Account is Not Approved for Transactions!");
			throw new UnsupportedOperationException();
		} else if (!toAct.isApproved()) {
			System.out.println("Recipient Account is Not Approved for Transactions!");
			throw new UnsupportedOperationException();
		} else {
			fromAct.setBalance(fromAct.getBalance() - amount);
			toAct.setBalance(toAct.getBalance() + amount);

			System.out.println(
					"=============================================================================================================================================");
			System.out.println("Transfer Successful! \n");
			System.out.println("$" + amount + " has been transferred from Account" + fromAct.getId() + " to Account "
					+ toAct.getId());
			System.out.println(
					"=============================================================================================================================================");
		}

		tran.setSender(fromAct);
		tran.setRecipient(toAct);
		tran.setType(TransactionType.TRANSFER);
		tran.setAmount(amount);
		tran.getTimestamp();

		if (toAct.getTransactions() == null) {
			tranList.add(tran);
		} else {
			tranList = toAct.getTransactions();
			tranList.add(tran);
		}
		toAct.setTransactions(tranList);

		if (fromAct.getTransactions() == null) {
			tranList.add(tran);
		} else {
			tranList = fromAct.getTransactions();
			tranList.add(tran);
		}
		fromAct.setTransactions(tranList);
		tDao.addTransaction(tran);
		actDao.updateAccount(toAct);
		actDao.updateAccount(fromAct);

	}

	/**
	 * Creates a new account for a given User
	 * 
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
		try (Scanner sc = new Scanner(System.in)) {
			int x;
			Account act = new Account();

			System.out.println("Create a default account for the customer.");
			System.out.println(
					"What type of account would you like to create? Select [1] for Checking or [2] for Savings :");
			x = sc.nextInt();

			switch (x) {
			case 1:
				act.setType(AccountType.CHECKING);
				break;

			case 2:
				act.setType(AccountType.SAVINGS);
				break;
			}

			act.setId(0);
			act.setOwnerId(u.getId());
			act.setBalance(STARTING_BALANCE);
			act.isApproved();

			getConnection();
			String query = "insert into p0mac.accounts (account_id, type, balance, status) values (?,?,?,?) where owner_id=?";

			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, 0);
				pst.setInt(5, u.getId());
				pst.setString(2, a.getType().toString());
				pst.setDouble(3, a.getBalance());
				pst.setBoolean(4, a.isApproved());
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return act;
		}
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
