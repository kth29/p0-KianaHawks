package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;

public class TransactionDaoDB implements TransactionDao {

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
	public List<Transaction> getAllTransactions() {
		getConnection();
		List<Transaction> tranList = new ArrayList<Transaction>();
		String query = "select * from p0mac.transactions";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Transaction tran = new Transaction();
				tran.setSender(rs.getInt("from_account"));
				tran.setRecipient(rs.getInt("from_account"));
				tran.setType(TransactionType.valueOf(rs.getString("type")));
				tran.setAmount(rs.getDouble("amount"));
				tran.setTimestamp(rs.getTimestamp("time_stamp"));
				tranList.add(tran);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tranList;
	}

	public void addTransaction(Transaction t) {
		getConnection();
		String query = "insert into p0mac.transactions (from_account, to_account, type, amount, timestamp) values (?,?,?,?,?)";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, t.getSender().getId());
			pst.setInt(2, t.getRecipient().getId());
			pst.setString(3, t.getType().toString());
			pst.setDouble(4, t.getAmount());
			pst.setTimestamp(5, Timestamp.from(Instant.now()));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
