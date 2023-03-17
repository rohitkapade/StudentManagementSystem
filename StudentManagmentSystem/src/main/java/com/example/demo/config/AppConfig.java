package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AppConfig {
	
	
	 






	private UserDetailsService customUserDetailsService;
	
	
	 public AppConfig(UserDetailsService customUserDetailsService) {
			super();
			this.customUserDetailsService = customUserDetailsService;
		}
	 
	 

//	@Bean
//	SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
//
////		http.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/y").permitAll().anyRequest()
////				.authenticated().and().csrf().disable().formLogin().and().httpBasic();
//		
//		
//		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/home").permitAll().anyRequest()
//		.authenticated().and().csrf().disable().formLogin().loginPage("/login").permitAll(); 
//
//		return http.build();
//
//	}

	@Bean
	SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
		
		System.out.println("I also come here sometime ************/////");

	
		 
		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/getTopperStudent")
            .permitAll()
            .anyRequest()
			.authenticated()
			.and()
			.csrf()
			.disable()
			.formLogin()
			.loginPage("/login")
			.successHandler(successHandler())
			
			
			.permitAll();

		return http.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	
	
	

	 
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.authenticationProvider(daoAuthProvider());
	        auth.inMemoryAuthentication()
	            .withUser("admin@gmail.com")
	            .password("admin")
	            .roles("ADMIN");
	    }
	    
	    
	    
	    @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User
	            .withUsername("admin@gmail.com")
	            .password("admin")
	            .roles("ADMIN")
	            .build());
	        return manager;
	    }
	    
	    
	    
	
		
	
	
	@Bean
	DaoAuthenticationProvider daoAuthProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(customUserDetailsService);
		dao.setPasswordEncoder(passwordEncoder());
		
		return dao;
	}
	
	
	
	private AuthenticationSuccessHandler successHandler() {
		
		
		
		
		
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String targetUrl = determineTargetUrl(authentication);
                getRedirectStrategy().sendRedirect(request, response, targetUrl);
            }

            protected String determineTargetUrl(Authentication authentication) {
            	
            	if(authentication.getName().equals("admin@gmail.com")) {
            		Collection<GrantedAuthority> auth = new ArrayList<>();
            		auth.add(new SimpleGrantedAuthority("ADMIN"));
            		System.out.println("It can be Done as Is coming in determinTaggsds=============");
//            		authentication.getAuthorities().add(new SimpleGrantedAuthority("ADMIN"));
            	}
            	
            	
            	System.out.println(authentication+" "+"rrrrrrrrrrrrrrrrrrr");
            	
                String defaultUrl = "StudentMarksheet";
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority grantedAuthority : authorities) {
                    if (grantedAuthority.getAuthority().equals("STUDENT")) {
                    	System.out.println("1");
                        return defaultUrl;
                    } else if (grantedAuthority.getAuthority().equals("ADMIN")) {
                    	System.out.println("2");
                        return "/home";
                    }
                }
                return defaultUrl;
            }
        };
    }
	
	
	


}
