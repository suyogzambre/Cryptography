package com.crypto.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="querry_data")
public class SupportForm {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String issue;
	private String time;
	private String email;
	private long mobileNo;
	private String message;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SupportForm(long id, String issue, String time, String email,long mobileNo, String message) {
		super();
		this.id = id;
		this.issue = issue;
		this.time = time;
		this.email = email;
		this.mobileNo = mobileNo;
		this.message = message;
	}
	
	public SupportForm() {
		
	}
	
	
}
