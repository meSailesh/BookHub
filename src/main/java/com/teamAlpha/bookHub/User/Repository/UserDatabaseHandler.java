package com.teamAlpha.bookHub.User.Repository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAlpha.bookHub.User.Entities.User;





public interface UserDatabaseHandler {
	public void saveUser(User user);
	public List<User> getAllUser();
	public User getUserById(Long id);
	public boolean updateUser(Long id,User user);
	public boolean deleteUserById(Long id);
	
}
