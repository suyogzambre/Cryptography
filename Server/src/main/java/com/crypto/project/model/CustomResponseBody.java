package com.crypto.project.model;

public class CustomResponseBody {

	Integer statusCode;
	String message;
	String status;
	Object data;
	public CustomResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomResponseBody(Integer statusCode, String message, String status, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.status = status;
		this.data = data;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
