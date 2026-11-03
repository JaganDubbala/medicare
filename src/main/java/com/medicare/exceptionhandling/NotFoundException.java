package com.medicare.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
	
	String message;

}
