package com.example.demo.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepository;



@Controller
public class LoginController {

	@Autowired
	private StudentRepository customerRepository;
	
	
	// Custom login page
	@GetMapping("/login")
	public  String getLoggedInCustomerDetailsHandler(){
		
		System.out.println("it comes here");

		 return "signIn";
		 
	

	}
	
	

	
}
