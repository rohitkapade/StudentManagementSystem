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
	

	private String firstName;
	

	private String lastName;
	
	@Column(unique = true)
	private String email;
	

	private Integer mathMarks;
	

	private Integer scienceMarks;
	

	private Integer englishMarks;
	

	private Integer percentage;
	
	
	

		
	
	
}
