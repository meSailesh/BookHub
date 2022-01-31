package com.teamAlpha.bookHub.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.teamAlpha.bookHub.Entities.User;
import com.teamAlpha.bookHub.Repository.UserDatabaseHandler;
import com.teamAlpha.bookHub.Repository.UserRepository;

@Service
public class UserDataService {
	@Autowired
	UserDatabaseHandler userRepo;


	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepo.saveUser(user);
	}


	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		return userRepo.getAllUser();
	}


	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.getUserById(id);
	}


	public boolean updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return userRepo.updateUser(id, user);
	}


	public boolean deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.deleteUserById(id);
	}
	
	
}
