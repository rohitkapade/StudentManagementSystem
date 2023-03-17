package com.example.demo.Service;

import java.util.List;
import com.example.demo.Entity.*;



public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);
	
	Student getTopperStudent();
}
