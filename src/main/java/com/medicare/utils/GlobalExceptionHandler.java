package com.medicare.utils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlICVE(SQLIntegrityConstraintViolationException e)
	{
		SuccessResponse data=SuccessResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value())
				.dateTime(LocalDateTime.now())
				.message("You can not perform this operation")
				.data(e.getMessage())
				.build();
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

}
