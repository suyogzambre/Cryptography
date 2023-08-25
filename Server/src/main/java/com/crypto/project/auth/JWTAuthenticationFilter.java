package com.crypto.project.auth;
import static com.crypto.project.auth.SecurityConstants.HEADER_STRING;
import static com.crypto.project.auth.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crypto.project.auth.handler.LoginSuccessHandler;
import com.crypto.project.controller.UsersController;
import com.crypto.project.model.ApplicationUser;
import com.crypto.project.model.CustomResponseBody;
import com.crypto.project.service.ApplicationUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
	   private AuthenticationManager authenticationManager;
		private ApplicationUserRepository applicationUserRepository;
		
	    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,AuthenticationSuccessHandler successHandler) {
	        this.authenticationManager = authenticationManager;
	       // this.setAuthenticationSuccessHandler(successHandler);
	    }
	    
	    private ObjectMapper objectMapper = new ObjectMapper();
	    private TokenService tokenService=new TokenService();
	    
	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	        	
	        	System.out.println("authenticating");
	            ApplicationUser creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), ApplicationUser.class);
	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getUsername(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response,
	                                            FilterChain chain,
	                                            Authentication authentication) throws IOException, ServletException {

	    	System.out.println("In JWTAuthenticationFilter");
	    	String token =  tokenService.generateNewToken(authentication);
	        String userRole = ((User) authentication.getPrincipal()).getAuthorities().iterator().next().getAuthority().toString();
	        String userId = ((User) authentication.getPrincipal()).getUsername();
	        String userNumId = ((CustomSpringUser) authentication.getPrincipal()).getUserNumId().toString();
	        String authToken = TOKEN_PREFIX + token;
	        
	        //Add token to response header part
	        response.addHeader("UserRole", userRole);
	        response.addHeader("userId", userId);
	        response.addHeader("userNumId", userNumId);
	        response.addHeader(HEADER_STRING, authToken);
	        response.setContentType("application/json");

	         //Add token to response body part
	         Map<String, Object> jsonObj = new HashMap<>();  
	         jsonObj.put("status", "success");
	         jsonObj.put(HEADER_STRING, authToken);
	         jsonObj.put("UserRole", userRole);
	         jsonObj.put("userNumId", userNumId);
	         CustomResponseBody cm= new CustomResponseBody(200, "Login Successful","success", jsonObj);
	         response.getWriter().print(objectMapper.writeValueAsString(cm));
	        
	    }
	    
	    @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	    		AuthenticationException failed) throws IOException, ServletException {
	    	// TODO Auto-generated method stub
	    	//super.unsuccessfulAuthentication(request, response, failed);
	    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	         CustomResponseBody cm= new CustomResponseBody(400, "Login UnSuccessful","failure", "");
	         response.getOutputStream().println(objectMapper.writeValueAsString(cm));
	    	//
	    }
}
