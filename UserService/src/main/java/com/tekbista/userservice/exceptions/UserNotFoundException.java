package com.tekbista.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

}
