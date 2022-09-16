package com.tekbista.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tekbista.userservice.entities.Address;
import com.tekbista.userservice.entities.User;

@Service
public interface UserService {

	User getUserById(Long id);
	List<User> getAllUsers();
	void deleteUser(Long id);
	User updateUserAddress(Address address, Long id);
	boolean disableUser(Long id);
}
