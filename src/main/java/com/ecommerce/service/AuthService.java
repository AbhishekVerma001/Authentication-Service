package com.ecommerce.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.UserCredential;
import com.ecommerce.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {
    	System.out.println("Service add");
    	UserCredential user2=repository.findByEmail(credential.getEmail()).orElse(null);
    	
		 if(user2!=null) {
		 System.out.println("user2 mail: "+user2);

//		 if(user2.getEmail()==credential.getEmail()) {
//			 System.out.println("user exists");
//			 //throw new RuntimeException("Email already exists");
//		 }
		 throw new RuntimeException("Email already exists");
		 }
		 else {
			 System.out.println("saving user");
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential=repository.save(credential);
        return credential;
		 }
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
