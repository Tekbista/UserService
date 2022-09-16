package com.tekbista.userservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekbista.userservice.entities.Address;
import com.tekbista.userservice.entities.User;
import com.tekbista.userservice.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id){
		User user = userService.getUserById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserAddress(@Valid @RequestBody Address address, @PathVariable("id") Long userId){
		User user = userService.updateUserAddress(address, userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
	}
	
	@GetMapping("/disable/{id}")
	public ResponseEntity<String> disableUser(@PathVariable("id") Long id){
		if(userService.disableUser(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("User disabled successfully.");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not disabled");
	}
	
}
