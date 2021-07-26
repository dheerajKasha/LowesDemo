package com.lowes.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LowesException extends Exception{
	
	private String errorMessage;
	
	public LowesException() {
		
	}

	public LowesException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
