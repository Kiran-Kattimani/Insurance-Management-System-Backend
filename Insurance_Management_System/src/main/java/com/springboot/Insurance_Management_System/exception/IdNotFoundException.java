package com.springboot.Insurance_Management_System.exception;

@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException{
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public IdNotFoundException(String msg) {
		super();
		this.msg = msg;
	}
}
