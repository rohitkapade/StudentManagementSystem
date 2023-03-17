package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepository;
import com.example.demo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StudentRepository stuRepo;

	
	
	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
				
	
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		Student student = new Student();
		
		model.addAttribute("student", student);
			
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		
		student.setPercentage((student.getEnglishMarks()+student.getScienceMarks()+student.getMathMarks())/3);
		
		student.setLastName(passwordEncoder.encode(student.getLastName()));
		
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(passwordEncoder.encode(student.getLastName()));
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/getTopperStudent")
	public String getTopperStudent(Model model) {
		
		Student student = studentService.getTopperStudent();
		
		model.addAttribute("topperStudent", student);
		
		return "marksheet";
	}
	
	@GetMapping("/students/seeMarksheet/{id}")
	public String seeMarkssheet(@PathVariable Long id, Model model) {
		
		Student student = studentService.getStudentById(id);
		
		model.addAttribute("topperStudent", student);
		
		return "marksheet";
	}
	
	@GetMapping("/students/seeMarksheet")
	public String seeMarkssheetOAfterLogin(Authentication auth,Model model) {
		
		
		Optional<Student> stu = stuRepo.findByEmail(auth.getName());
		
		
		model.addAttribute("topperStudent", stu.get());
		
		return "marksheet";
	}
	
	@GetMapping("/StudentMarksheet")
	public String seeMarkssheetOAfterLogin1(Authentication auth,Model model) {
		
		
		Optional<Student> stu = stuRepo.findByEmail(auth.getName());
		
		
		model.addAttribute("topperStudent", stu.get());
		
		return "StudentMarksheet";
	}
	
	
	
	
	
	
}
