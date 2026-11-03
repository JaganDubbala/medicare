package com.medicare.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessResponse {
	
	private int statusCode;
	private String message;
	private LocalDateTime dateTime;
	private Object data;
	

}
