package com.crypto.project.auth.handler;

import static com.crypto.project.auth.SecurityConstants.HEADER_STRING;
import static com.crypto.project.auth.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.crypto.project.auth.CustomSpringUser;
import com.crypto.project.auth.TokenService;
import com.crypto.project.controller.UsersController;
import com.crypto.project.model.CustomResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
	@Autowired
	private UsersController userCtrl;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private TokenService tokenService=new TokenService();
	     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
 
//        userCtrl.store(authToken, Long.valueOf(userNumId));
         
       // super.onAuthenticationSuccess(request, response, authentication);
    }
 
}