package com.infy.todo.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.todo.dto.ErrorDTO;

@RestControllerAdvice
public class ExceptionHandlerAspect {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidEx(MethodArgumentNotValidException e){
		ErrorDTO error = new ErrorDTO();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String errorText = e.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				         .collect(Collectors.joining("|"));
		error.setErrorMessage(errorText);
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDTO> handlePathVarValidn(ConstraintViolationException e){
		ErrorDTO error = new ErrorDTO();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String errorText = e.getConstraintViolations().stream().map(x -> x.getMessage())
				.collect(Collectors.joining(", "));
		error.setErrorMessage(errorText);
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
	} 
	
//	@ExceptionHandler({Exception.class})
//	public ResponseEntity<ErrorDTO> handleEx(Exception e){
//		ErrorDTO error = new ErrorDTO();
//		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		error.setErrorMessage(e.getMessage());
//		return new ResponseEntity<ErrorDTO>(error,HttpStatus.BAD_REQUEST);
//	}

}
