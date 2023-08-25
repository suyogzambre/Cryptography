package com.crypto.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login_info")
public class LoginInfo {

	@Id
	String user_id;
	Long id;
	String password;
	String jwt_toekn;
	String referesh_token;
	String login_attempt;
	Boolean blocked;
	Date login_date_time;
	String role;
	Boolean loginFlag;
	
	public Boolean getLoginFlag() {
		return loginFlag;
	}



	public void setLoginFlag(Boolean loginFlag) {
		this.loginFlag = loginFlag;
	}


	public LoginInfo() {
		super();
	}
	public LoginInfo(Long id, String user_id, String password, String jwt_toekn, String referesh_token,
			String login_attempt, Boolean blocked, Date login_date_time, String role,Boolean loginFlag) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.password = password;
		this.jwt_toekn = jwt_toekn;
		this.referesh_token = referesh_token;
		this.login_attempt = login_attempt;
		this.blocked = blocked;
		this.login_date_time = login_date_time;
		this.role = role;
		this.loginFlag = loginFlag;
	}

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJwt_toekn() {
		return jwt_toekn;
	}
	public void setJwt_toekn(String jwt_toekn) {
		this.jwt_toekn = jwt_toekn;
	}
	public String getReferesh_token() {
		return referesh_token;
	}
	public void setReferesh_token(String referesh_token) {
		this.referesh_token = referesh_token;
	}
	public String getLogin_attempt() {
		return login_attempt;
	}
	public void setLogin_attempt(String login_attempt) {
		this.login_attempt = login_attempt;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	public Date getLogin_date_time() {
		return login_date_time;
	}
	public void setLogin_date_time(Date login_date_time) {
		this.login_date_time = login_date_time;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
