package com.crypto.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login_cred")
public class ApplicationUser {
	  	@Id
	    @GeneratedValue
	    private long id;
	    private String username;
	    private String password;
	    private String role;
	    
	    
	    public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public void setId(long id) {
			this.id = id;
		}
		public ApplicationUser(long id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
		}
		public ApplicationUser() {
			super();
			// TODO Auto-generated constructor stub
		}
		public long getId() {
	        return id;
	    }
	    public String getUsername() {
	        return username;
	    }
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
}
