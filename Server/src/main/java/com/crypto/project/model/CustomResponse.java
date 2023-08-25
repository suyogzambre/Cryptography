package com.crypto.project.model;

public class CustomResponse {

	Integer statusCode;
	String message;
	Object obj;
	public CustomResponse(int i, String string, Object object){
		super();
		this.statusCode=i;
		this.message= string;
		this.obj = object;
	}
	public String getStatus() {
		return message;
	}
	public void setStatus(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
