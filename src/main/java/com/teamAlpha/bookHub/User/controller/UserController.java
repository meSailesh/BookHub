package com.teamAlpha.bookHub.User.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.teamAlpha.bookHub.User.Entities.User;
import com.teamAlpha.bookHub.User.Repository.UserDatabaseHandler;




@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserDatabaseHandler userdatabase;
	
	@PostMapping("/create")
	ResponseEntity<?> createUser(@RequestBody()User user){
		HashMap<String,Object> data=new HashMap<String,Object>();
		try {
		userdatabase.saveUser(user);
		data.put("data",user);
		Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users");
		data.put("self-link", link);
		return new ResponseEntity(data, HttpStatus.OK);
		}catch(Exception e) {
			data.put("data","Error in saving data to database" +e);
			return new ResponseEntity(data,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	//,@RequestBody()User user
	@PutMapping("/update/{id}")
	ResponseEntity<?> updateUser(@PathVariable(value="id")Long id,@RequestBody()User user){
		HashMap<String,Object> data=new HashMap<String,Object>();
		HttpStatus status;
	
		User tempData=userdatabase.getUserById(id);
		if(tempData==null) {
			status=HttpStatus.NOT_FOUND;
			data.put("msg", "No user Found for given Id");
		}else {
			user.setUserId(id);
			userdatabase.saveUser(user);
			status=HttpStatus.OK;
			data.put("msg", "Update Completed");
			Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser(id)).withRel("getUserById");
			data.put("self-link", link);
		}
		
		
		
		return new ResponseEntity(data, status);
		
	}
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteUser(@PathVariable(value="id")Long id){
		HashMap<String,Object> data=new HashMap<String,Object>();
		HttpStatus status;
		User user=userdatabase.getUserById(id);
		if(user==null) {
			status=HttpStatus.NOT_FOUND;
			data.put("msg", "No User Found With Given Id");
		}else {
			userdatabase.deleteUserById(id);
			status=HttpStatus.OK;
			data.put("msg", "User Has Been Deleted");
			data.put("id", id);
		}
	
		return new ResponseEntity(data, HttpStatus.OK);
	}
	
	@PutMapping("/block/{id}")
	ResponseEntity<?> blockUser(@PathVariable(value="id")Long id){
		
		HashMap<String,Object> data=new HashMap<String,Object>();
		User user=userdatabase.getUserById(id);
		HttpStatus status;
		if(user==null) {
			data.put("msg", "No user Found for given Id");
			data.put("id", id);
			status=HttpStatus.NOT_FOUND;
		}else {
			user.setIsBlocked(true);
			userdatabase.saveUser(user);
			data.put("msg", "User blocked for given Id:"+id);
			status=HttpStatus.OK;
			
			
		}
		
		return new ResponseEntity(data, status);
	}
	//Waste of endpoint discuss with sir...we can resuse the same endpoint
	@PutMapping("/unblock/{id}")
	ResponseEntity<?> unblockUser(@PathVariable(value="id")Long id){
		
		HashMap<String,Object> data=new HashMap<String,Object>();
		User user=userdatabase.getUserById(id);
		HttpStatus status;
		if(user==null) {
			data.put("msg", "No user Found for given Id");
			data.put("id", id);
			status=HttpStatus.NOT_FOUND;
		}else {
			user.setIsBlocked(false);
			userdatabase.saveUser(user);
			data.put("msg", "User unblocked for given Id:"+id);
			Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser(id)).withRel("getUserById");
			data.put("self-link", link);
		
			status=HttpStatus.OK;
			
			
		}
		
		return new ResponseEntity(data, status);
	}
	
	@GetMapping("/getAll")
	ResponseEntity<?> getAllUsers(){
		
		HashMap<String,Object> data=new HashMap<String,Object>();
		List<User> users=userdatabase.getAllUser();
		data.put("msg", "All Users");
		data.put("data",users);
		Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser(null)).withRel("getUserById");
		data.put("self-link", link);
		
		return new ResponseEntity(data, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	ResponseEntity<?> getUser(@PathVariable(value="id")Long id){
		
		HashMap<String,Object> data=new HashMap<String,Object>();
		HttpStatus status;
		User user=userdatabase.getUserById(id);
		if(user==null) {
			data.put("msg", "No User Found For Given Id:"+id);
			data.put("data",user);
			status=HttpStatus.NOT_FOUND;
		}else {
			data.put("msg", "User Detail");
			data.put("data",user);
			status=HttpStatus.NOT_FOUND;
			Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("getAllUsers");
			data.put("self-link", link);
		}
		
		
		
		return new ResponseEntity(data,status);
	}
	
	
}
