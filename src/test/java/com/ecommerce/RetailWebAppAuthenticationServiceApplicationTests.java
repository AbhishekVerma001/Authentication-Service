package com.ecommerce;

import static org.junit.Assert.assertNotNull;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.controller.AuthController;
import com.ecommerce.dto.AuthRequest;
import com.ecommerce.dto.JwtResponse;
import com.ecommerce.entity.UserCredential;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class RetailWebAppAuthenticationServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	AuthRequest auth =new AuthRequest();
	AuthController controller=new AuthController();
	String token;
	@Autowired
	TestRestTemplate restTemplate;
	

//	@Test
//	void contextLoads() {
//	}
//	@Test
//	void LoginTest() {
//		auth.setUsername("abhishek@gmail.com");
//		auth.setPassword("A1234567");
//		//given()
//	}
	
	@Test
	@Order(1)
    public void testAddNewUser() throws URISyntaxException 
    {
        final String baseUrl = "/auth/register";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        UserCredential u=new UserCredential();
        Random random = new Random();   
        int y = random.nextInt(1000);   
        u.setEmail("dasg"+y+"@gmail.com");
        u.setFname("Fname");
        u.setLname("Lname");
        u.setId(346);
        u.setMobile(1234567890);
        u.setPassword("A1234567");
        
        //HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
        HttpEntity<UserCredential> request = new HttpEntity<>(u, headers);
         
        ResponseEntity<UserCredential> result = this.restTemplate.postForEntity(uri, request, UserCredential.class);
         
        //Verify bad request and missing header
        Assertions.assertEquals(200, result.getStatusCode().value());
        //assertEquals(200, result.getStatusCodeValue());
        //assertEquals(true, result.getBody().contains("Missing request header"));
    }
	
	@Test
	@Order(2)
    public void testGetToken() throws URISyntaxException 
    {
		
        final String baseUrl = "/auth/token";
        URI uri = new URI(baseUrl);
        auth.setUsername("Abc@gmail.com");
		auth.setPassword("A1234567");
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        
        
        
        HttpEntity<AuthRequest> request = new HttpEntity<>(auth, headers);
         
        ResponseEntity<JwtResponse> result = this.restTemplate.postForEntity(uri, request, JwtResponse.class);
         
        System.out.println(result.getBody().getToken());
        token=result.getBody().getToken();
       
        Assertions.assertEquals(200, result.getStatusCode().value());
        assertNotNull(result);
        
    }
	
	@Test
	@Order(3)
    public void testValidateToken() throws URISyntaxException 
    {
//		final String baseUrl = "/auth/token";
//        URI uri = new URI(baseUrl);
//		auth.setUsername("Abc@gmail.com");
//		auth.setPassword("A1234567");
//		token=controller.getToken(auth).getToken();
		//controller.validateToken(token);
        //final String baseUrl = "/auth/validate?token="+token;
		 final String baseUrl = "/auth/token";
	        URI uri = new URI(baseUrl);
	        auth.setUsername("Abc@gmail.com");
			auth.setPassword("A1234567");
	         
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/json");
	        
	        
	        
	        HttpEntity<AuthRequest> request = new HttpEntity<>(auth, headers);
	         
	        ResponseEntity<JwtResponse> result = this.restTemplate.postForEntity(uri, request, JwtResponse.class);
	        token=result.getBody().getToken();
		
		
		HttpHeaders headers2 = new HttpHeaders();
        headers2.set("Content-Type", "application/json");
        
        
        
        HttpEntity<AuthRequest> request2 = new HttpEntity<>(auth, headers2);
         
//        ResponseEntity<JwtResponse> result2 = this.restTemplate.postForEntity(uri, request2, JwtResponse.class);
//         token=result2.getBody().getToken();
        final String url = "/auth/validate?token="+token;
        		//"/auth/validate?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBYmNAZ21haWwuY29tIiwiaWF0IjoxNjgwNDE3MDQwLCJleHAiOjE2ODA0MTg4NDB9.c5pn_HdzJDuZRBk3UceNnNsTkfVEs2ZKJWuqnw0ir9U";
        URI validateUri = new URI(url);
        
         
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
        
        
        
        //HttpEntity<AuthRequest> request = new HttpEntity<>( headers);
         
        ResponseEntity<String> res = this.restTemplate.exchange(
        		validateUri,
        		HttpMethod.GET ,request2, String.class);
         
        System.out.println(res);
       // token=result.getBody().getToken();
       
        Assertions.assertEquals(200, res.getStatusCode().value());
        assertNotNull(res);
        
    }
	
	
 

}
