package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "first_name", nullable = false)
	private String firstName;
	
//	@Column(name = "last_name")
	private String lastName;
	
	@Column(unique = true)
//	@Column(name = "email")
	private String email;
	
//	@Column(name= "maths")
	private Integer mathMarks;
	
//	@Column(name= "science")
	private Integer scienceMarks;
	
//	@Column(name= "english")
	private Integer englishMarks;
	
//	@Column(name= "percentage")
	private Integer percentage;
	
	
	

		
	
	
}
