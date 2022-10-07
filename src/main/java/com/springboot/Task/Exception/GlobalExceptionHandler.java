package com.springboot.Task.Exception;

import java.util.ArrayList;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springboot.Task.Dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		ErrorResponseDto errorDetails = new ErrorResponseDto(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handling global exception

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		ErrorResponseDto errorDetails = new ErrorResponseDto(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponseDto handleDataIntegrityViolationException(
			final DataIntegrityViolationException exception) {

		ErrorResponseDto errorDetails = new ErrorResponseDto("Data Already Exist", "DataIntegrityViolation");
		return errorDetails;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto handleValidationException(final MethodArgumentNotValidException exception) {

		List<String> details = new ArrayList<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage(details.get(0).split("\\*", 2)[0]);
		errorResponseDto.setMsgKey(details.get(0).split("\\*", 2)[1]);
		return errorResponseDto;

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorResponseDto handleHttpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage("Method Does Not Supported");
		errorResponseDto.setMsgKey("Please Check Your Method Type");
		return errorResponseDto;

	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorResponseDto handleAccessDeniedException(final AccessDeniedException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Access Denied");
		error.setMsgKey("accessDenied");
		return error;

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponseDto noHandlerFoundException(final NoHandlerFoundException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("URL not Found, Please check URL");
		error.setMsgKey("URLNotFound");
		return error;

	}
}