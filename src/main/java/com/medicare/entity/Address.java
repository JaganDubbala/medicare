package com.medicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	@Id
	private int id;
	private String street;
	private String city;
	private String state;
	private String country;
	private int pincode;
}
