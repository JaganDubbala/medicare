package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.entity.Admin;
import com.medicare.service.AdminService;
import com.medicare.utils.SuccessResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Admin admin)
	{
		return adminService.saveAdmin(admin);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateAdmin(@RequestBody Admin admin)
	{
		return adminService.updateAdmin(admin);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteAdmin(@RequestParam int id)
	{
		return adminService.deleteAdmin(id);
	}
	@GetMapping("/fetch")
	public ResponseEntity<SuccessResponse> fetchAdminById(@RequestParam int id)
	{
		return adminService.fetchAdminById(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> loginAdmin(@RequestParam String email,
														@RequestParam String password)
	{
		return adminService.adminLogin(email, password);
	}
}
