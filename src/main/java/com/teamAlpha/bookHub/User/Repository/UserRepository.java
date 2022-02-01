package com.teamAlpha.bookHub.User.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.teamAlpha.bookHub.User.Entities.User;



@Repository
@Primary
public class UserRepository implements UserDatabaseHandler{
	@Autowired
	UserDatabase userdb;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		try {
			userdb.save(user);
			System.out.println("User data saved to the database");
		}catch(Exception e) {
			throw new Error("UserDatabaseError"+e);
		}
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users;
		try {
			users=(List<User>)userdb.findAll();
		}catch(Exception e) {
			throw new Error("UserDatabaseError");
		}
		return users;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		User user;
		
		try {
			User userdata=userdb.findById(id).get();
			user=(userdata==null)?null:userdata;
		}catch(NoSuchElementException e) {
			return null;
		}catch(Exception e) {
			throw new Error("UserDatabaseError"+e);
		}
		return user;
	}

	@Override
	public boolean updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		if(userdb.findById(id).get()==null) {
			return false;
		}
		try {
			userdb.save(user);
		}catch(Exception e) {
			throw new Error("UserDatabaseError");
			
		}
		return true;
	}

	@Override
	public boolean deleteUserById(Long id) {
		// TODO Auto-generated method stub
		if(userdb.findById(id).get()==null) {
			return false;
		}
		try {
			userdb.delete(userdb.findById(id).get());
		}catch(Exception e) {
			throw new Error("UserDatabaseError");
		}
		return true;
	}
	
}
