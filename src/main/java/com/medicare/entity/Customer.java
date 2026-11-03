package com.medicare.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	private Integer id;
	private String name;
	private String gender;
	private LocalDate dob;
	private String email;
	private String password;
	private long mobileNumber;
	public Object getRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
