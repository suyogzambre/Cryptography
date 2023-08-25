package com.crypto.project.auth;

import static com.crypto.project.auth.SecurityConstants.HEADER_STRING;
import static com.crypto.project.auth.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.crypto.project.controller.UsersController;
import com.crypto.project.model.CustomResponseBody;
import com.crypto.project.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	private UsersController userCtrl;
	
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
    
    private ObjectMapper objectMapper = new ObjectMapper();
    private TokenService tokenService=new TokenService();
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    	//System.out.println(req.getHeader());
        String header = req.getHeader(HEADER_STRING);
        //System.out.println(header);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        try
        {
        UsernamePasswordAuthenticationToken authentication = tokenService.getAuthentication(req);
        //customValidation(authentication, header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
        }catch (ExpiredJwtException ex) {
        	
				req.setAttribute("exception", ex);
				System.out.println(ex.getMessage());
				res.setContentType(MediaType.APPLICATION_JSON_VALUE);
				res.setStatus(HttpStatus.FORBIDDEN.value());
				CustomResponseBody cm= new CustomResponseBody(403, "Session has expired/logout. Login again","failure", ex.getMessage());
		        
				res.getOutputStream().println(objectMapper.writeValueAsString(cm));
		} catch (BadCredentialsException ex) {
			req.setAttribute("exception", ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}
    }

	private void customValidation(UsernamePasswordAuthenticationToken authentication, String token) {
		// TODO Auto-generated method stub
		Map<String,Object> header=new HashMap<String,Object>();
		Optional<Users> user = userCtrl.retriveUser( authentication.getPrincipal().toString());
		if(user.get()!=null) {
			Users userD = user.get();
			header.put("Authorization", authentication.getPrincipal());
			if(userD.getToken() == null || (userD.getToken()!= null && !(userD.getToken().equals(token))))
				throw new ExpiredJwtException((Header)header,null,"Session has expired/logout. Login again");
		}
	}
}