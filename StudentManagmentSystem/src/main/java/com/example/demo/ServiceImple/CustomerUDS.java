package com.example.demo.ServiceImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepository;


@Service
public class CustomerUDS implements UserDetailsService {
	
	@Autowired
	private StudentRepository cr;
	
	

	@Bean
	PasswordEncoder passwordEncoder1() {

		return new BCryptPasswordEncoder();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		if(username.equals("admin@gmail.com")) {
			
			List<GrantedAuthority> g1 = new ArrayList<>();
			
			g1.add(new SimpleGrantedAuthority("ADMIN"));
			
			
			return new User("admin@gmail.com",  passwordEncoder1().encode("admin") ,g1);
			
		}

		System.out.println("it is comming in lubun");
		
		Optional<Student> c = cr.findByEmail(username);
		
		List<GrantedAuthority> g = new ArrayList<>();
		
		g.add(new SimpleGrantedAuthority("STUDENT"));
		

		System.out.println(cr.findAll());
		
	
		System.out.println("Not comming at last of lubun"+ c.get().getEmail()+" "+ c.get().getLastName());
		
		return new User(c.get().getEmail(),c.get().getLastName(),g);
	}

}


