package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {

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

	@Override
	public Account addAccount(Account a) {
		getConnection();
		int status = 0;
		String query = "insert into p0mac.accounts (account_id, owner_id,type, balance, approved) values (?,?,?,?,?)";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, 0);
			pst.setInt(2, a.getOwnerId());
			pst.setString(3, a.getType().toString());
			pst.setDouble(4, a.getBalance());
			pst.setString(5, "0");
			status = pst.executeUpdate();

			if (status > 0) {
				System.out.println("\n Application for Account Submitted! Application is pending approval. \n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Account getAccount(Integer actId) {

		getConnection();
		String query = "select * from p0mac.accounts where account_id=" + actId;
		Account act = new Account();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			if (rs.next()) {
				act.setId(rs.getInt("account_id"));
				act.setOwnerId(rs.getInt("owner_id"));
				act.setType(AccountType.valueOf(rs.getString("type")));
				act.setBalance(rs.getDouble("balance"));
				act.setApproved(rs.getBoolean("approved"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return act;
	}

	@Override
	public List<Account> getAccounts() {
		getConnection();
		String query = "select * from p0mac.accounts";
		List<Account> actList = new ArrayList<Account>();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Account act = new Account();
				act.setId(rs.getInt("account_id"));
				act.setOwnerId(rs.getInt("owner_id"));
				act.setType(AccountType.valueOf(rs.getString("type")));
				act.setBalance(rs.getDouble("balance"));
				act.setApproved(rs.getBoolean("approved"));
				actList.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actList;
	}

	@Override
	public List<Account> getAccountsByUser(User u) {
		getConnection();
		String query = "select * from p0mac.accounts where owner_id=" + u.getId();
		List<Account> ownerList = new ArrayList<Account>();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Account act = new Account();
				act.setId(rs.getInt("account_id"));
				act.setOwnerId(rs.getInt("owner_id"));
				act.setType(AccountType.valueOf(rs.getString("type").toUpperCase()));
				act.setBalance(rs.getDouble("balance"));
				act.setApproved(rs.getBoolean("approved"));

				ownerList.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ownerList;
	}

	@Override
	public Account updateAccount(Account a) {
		getConnection();
		String query = "update p0mac.accounts set balance=?, approved=? where account_id=?";

		try {
			pst = conn.prepareStatement(query);
			pst.setDouble(1, a.getBalance());
			pst.setBoolean(2, a.isApproved());
			pst.setInt(3, a.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public boolean removeAccount(Account a) {
		getConnection();
		String query = "delete from p0mac.accounts where account_id=";
		try {
			st = conn.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Account> getPendingAccounts() {
		getConnection();
		String query = "select * from p0mac.accounts where approved= '0'";
		List<Account> actList = new ArrayList<Account>();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Account act = new Account();
				act.setId(rs.getInt("account_id"));
				act.setOwnerId(rs.getInt("owner_id"));
				act.setType(AccountType.valueOf(rs.getString("type").toUpperCase()));
				act.setBalance(rs.getDouble("balance"));
				actList.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actList;
	}

}
