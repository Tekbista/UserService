package com.tekbista.userservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	@NotEmpty
	@Size(min = 2, max = 100)
	private String street;
	@NotEmpty
	@Size(min = 2, max = 100)
	private String city;
	@NotEmpty
	@Size(min = 2, max = 100)
	private String state;
	@NotEmpty
	@Size(min = 2, max = 100)
	private String zipCode;
}
