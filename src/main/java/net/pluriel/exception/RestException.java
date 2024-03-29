package net.pluriel.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class RestException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;
	
	public RestException() {
		super();
	}
	public RestException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public RestException(String message) {
		super(message);
		this.message = message;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
}

