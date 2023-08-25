package com.crypto.project.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.crypto.project.controller.UsersController;

public class CustomLogoutSuccessHandler extends 
SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {


	@Autowired
	UsersController userCtrl;

  @Override
  public void onLogoutSuccess(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Authentication authentication) 
    throws IOException, ServletException {

      String refererUrl = request.getHeader("Referer");
      userCtrl.logout(Long.valueOf(request.getHeader("UserNumId") == null ? null : request.getHeader("UserNumId") ));
      //auditService.track("Logout from: " + refererUrl);
      response.setStatus(HttpServletResponse.SC_OK);
//      super.onLogoutSuccess(request, response, authentication);
  }
}