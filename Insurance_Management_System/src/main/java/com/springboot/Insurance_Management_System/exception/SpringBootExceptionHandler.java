package com.springboot.Insurance_Management_System.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.Insurance_Management_System.model.ResponseStructure;

@RestControllerAdvice
public class SpringBootExceptionHandler {

	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(value =IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noIdFound(IdNotFoundException exception){
		
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMsg(exception.getMsg());
		responseStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure ,HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));

	}
}
