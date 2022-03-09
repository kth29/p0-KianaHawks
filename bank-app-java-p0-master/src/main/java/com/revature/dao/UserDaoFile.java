package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;

/**
 * Implementation of UserDAO that reads and writes to a file
 */
public class UserDaoFile implements UserDao {

	public static String fileLocation = "/bank-app-java-p0-master/userList.txt";

	ArrayList<User> userlist = new ArrayList<User>();

	// perform operations on ArrayList, then write ArrayList to .txt file

	@Override
	public User addUser(User user) {
		userlist.add(user);
		return user;
	}

	@Override
	public User getUser(Integer userId) {

		return null;
	}

	@Override
	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
