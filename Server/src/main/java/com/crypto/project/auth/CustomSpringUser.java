package com.crypto.project.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomSpringUser extends org.springframework.security.core.userdetails.User {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Long userNumId;

public CustomSpringUser(String username, String password, Collection<? extends GrantedAuthority> authorities,Long userNumId) {
    super(username, password, authorities);
    this.userNumId=userNumId;
}

public Long getUserNumId() {
	return this.userNumId;
}

public void SetUserNumId(Long id) {
	this.userNumId=id;
}

}