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
public class Drug {
	@Id
	  private int id;
		private String name;
		private String company;
		private String type;
		private double price;
		private int quantity;
		private boolean banned;
	
}
