package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao {

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		 
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		return false;
		
		/*iterators allow for transversal (scanning) of a collection and selective removal of an object
		 * 
		 * May need:
		 * static void filter(Collection<?> c) {
    			for (Iterator<?> it = c.iterator(); it.hasNext(); )
        			if (!cond(it.next()))
            			it.remove();
			} */
	}

}
