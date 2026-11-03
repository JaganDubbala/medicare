package com.medicare.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.entity.Admin;
import com.medicare.exceptionhandling.NotFoundException;
import com.medicare.repository.AdminRepository;
import com.medicare.utils.SuccessResponse;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	//save admin
	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin)
	{
		Admin saveAdmin=adminRepository.save(admin);
		
		SuccessResponse data = SuccessResponse.builder().statusCode(HttpStatus.CREATED.value())
		.dateTime(LocalDateTime.now())
				.message("Admin Saved Succesfully")
				.data(saveAdmin)
				.build();
		
		return new ResponseEntity<>(data,HttpStatus.CREATED);
	}
	
	//Update Admin
	public ResponseEntity<SuccessResponse> updateAdmin(Admin admin)
	{
		Optional<Admin> opAdmin=adminRepository.findById(admin.getId());
		
		if(opAdmin.isPresent())
		{
			Admin dbAdmin=opAdmin.get();
			if(admin.getEmail()!=null) {
				dbAdmin.setEmail(admin.getEmail());
			}
			if(admin.getPassword()!=null)
			{
				dbAdmin.setPassword(admin.getPassword());
			}
			if(admin.getRole()!=null)
			{
				dbAdmin.setRole(admin.getRole());
			}
			Admin updateAdmin =adminRepository.save(dbAdmin);
		
		SuccessResponse data = SuccessResponse.builder().statusCode(HttpStatus.OK.value())
				.dateTime(LocalDateTime.now())
				.message("Admin Updated Succesfully")
				.data(updateAdmin)
				.build();
		
		return new ResponseEntity<>(data,HttpStatus.CREATED);
		}
		
		throw new NotFoundException("Admin details not found"+admin.getId());
	}
	
	//Delete Admin
	
	public ResponseEntity<SuccessResponse> deleteAdmin(int id)
	{
		Optional<Admin> opAdmin=adminRepository.findById(id);
		
		if(opAdmin.isPresent())
		{
			Admin admin=opAdmin.get();
			
			adminRepository.deleteById(id);
			
			SuccessResponse data=SuccessResponse.builder().statusCode(HttpStatus.OK.value())
					.dateTime(LocalDateTime.now())
					.message("Admin Deleted Successfully")
					.data(admin)
					.build();
			return new ResponseEntity<>(data,HttpStatus.OK);
		}
		
		throw new NotFoundException("Admin details not found"+id);
	}
	
	//Admin login
	
	public ResponseEntity<SuccessResponse> adminLogin(String email,String Password)
	{
		Optional<Admin> opAdmin=adminRepository.findByEmail(email);
		if(opAdmin.isPresent())
		{
			Admin admin=opAdmin.get();
			
			if(admin.getPassword().equals(Password))
			{
				SuccessResponse data=SuccessResponse.builder().statusCode(HttpStatus.OK.value())
						.dateTime(LocalDateTime.now())
						.message("Admin Login Successfully")
						.data(admin)
						.build();
				return new ResponseEntity<>(data,HttpStatus.OK);
			}
			throw new NotFoundException("Please check Password"+admin.getPassword());
		}
		
		throw new NotFoundException("Admin details not found with this mail"+email);
		
	}
	
	//Fetch Admin By ID
	
	public ResponseEntity<SuccessResponse> fetchAdminById(int id){
		
		Optional<Admin> opAdmin=adminRepository.findById(id);
		
		if(opAdmin.isPresent())
		{
			
			Admin admin=opAdmin.get();
			SuccessResponse data=SuccessResponse.builder().statusCode(HttpStatus.OK.value())
					.dateTime(LocalDateTime.now())
					.message("Admin Details Fetched Successfully")
					.data(admin)
					.build();
			
			return new ResponseEntity<>(data,HttpStatus.OK);
		}
		
		throw new NotFoundException("Admin details not found with this id : "+id);
	}

}
