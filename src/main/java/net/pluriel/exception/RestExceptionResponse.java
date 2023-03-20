package net.pluriel.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestExceptionResponse {
	private String title;
	private int errorNumber;
	private LocalDateTime date;
    private String message;
	//private Map<String, Object> errors = new HashMap<>();
}

