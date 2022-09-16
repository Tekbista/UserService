package com.tekbista.userservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tekbista.userservice.entities.Address;
import com.tekbista.userservice.entities.User;
import com.tekbista.userservice.exceptions.UserNotFoundException;
import com.tekbista.userservice.repositories.AddressRepository;
import com.tekbista.userservice.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public User getUserById(Long id) {
		if(!userRepository.existsById(id)) {
			log.error("Error: User with id: " + id + " not found.");
			throw new UserNotFoundException("User not found");
		}
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		if(!userRepository.existsById(id)) {
			log.error("Error: User with id: " + id + " not found.");
			throw new UserNotFoundException("User not found");
		}
		
		userRepository.deleteById(id);
		
	}

	@Override
	public User updateUserAddress(Address address, Long userId) {
		// If user not found in the database throw user not found exception
		if(!userRepository.existsById(userId)) {
			log.error("Error: User with id: " + userId + " not found.");
			throw new UserNotFoundException("User not found");
		}
		
		// Get the user from database
		User dbUser = userRepository.findById(userId).get();
		// If the address exist for the user get the address id
		if(dbUser.getAddress().getAddressId() != null) {
			// Set the address id so that the address get updated instead of save.
			address.setAddressId(dbUser.getAddress().getAddressId());
		}
		
		// Update the address for the user
		Address newAddress = addressRepository.save(address);
		dbUser.setAddress(newAddress);
		
		return dbUser;
	}

	@Override
	public boolean disableUser(Long id) {
		// If user not found in the database throw user not found exception
		if(!userRepository.existsById(id)) {
			log.error("Error: User with id: " + id + " not found.");
			throw new UserNotFoundException("User not found");
		}
		
		User user = userRepository.findById(id).get();
		user.setEnabled(false);
		User newUser = userRepository.save(user);
		
		if(newUser != null) return true;
		
		return false;
	}

}
