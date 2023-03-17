package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Student;



public interface StudentRepository extends JpaRepository<com.example.demo.Entity.Student, Long>{

	
	Optional<Student> findByEmail( String username);
}
