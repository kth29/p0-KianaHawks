package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.User.UserType;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao {
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
	public User addUser(User user) {
		getConnection();

		String query = "insert into p0mac.user (user_id, first_name, last_name, username, password, user_type) values (?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user.getId());
			pst.setString(2, user.getFirstName());
			pst.setString(3, user.getLastName());
			pst.setString(4, user.getUsername());
			pst.setString(5, user.getPassword());
			pst.setString(6, user.getUserType().toString());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUser(Integer userId) {

		getConnection();
		String query = "select * from p0mac.user where id=" + userId;
		User u = new User();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			if (rs.next()) {
				u.setId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public User getUser(String username, String password) {

		getConnection();
		String query = "select * from p0mac.user where username='" + username + "'and password ='" + password + "'";
		User u = new User();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			if (rs.next()) {
				u.setId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setUserType(UserType.valueOf(rs.getString("user_type")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public List<User> getAllUsers() {

		getConnection();
		List<User> userList = new ArrayList<User>();

		String query = "select * from p0mac.user";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setUserType(UserType.valueOf(rs.getString("user_type")));
				userList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public User updateUser(User u) {
		getConnection();
		String query = "update p0mac.user set first_name=?, last_name=?, username=?, password=? where user_id=?";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(5, u.getId());
			pst.setString(1, u.getFirstName());
			pst.setString(2, u.getLastName());
			pst.setString(3, u.getUsername());
			pst.setString(4, u.getPassword());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			u.setId(rs.getInt("user_id"));
			u.setFirstName(rs.getString("first_name"));
			u.setLastName(rs.getString("last_name"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setUserType(UserType.valueOf(rs.getString("user_type")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean removeUser(User u) {
		getConnection();
		String query = "delete from p0mac.user where id=" + u.getId();
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

}
