package com.example.demo.ServiceImple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepository;
import com.example.demo.Service.StudentService;



@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

	@Override
	public Student getTopperStudent() {
		List<Student> allStudent = studentRepository.findAll();
		
		int max = Integer.MIN_VALUE;
		
		Student ansStu = new Student();
		
		for(Student s:allStudent) {
			if(s.getPercentage()>max) {
				ansStu=s;
				max=s.getPercentage();
			}
		}
		
		return ansStu;
	}

}
