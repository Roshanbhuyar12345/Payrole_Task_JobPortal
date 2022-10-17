package com.jobportal.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jobportal.dto.ErrorResponceDto;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());
		return error;

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponceDto handleDataIntegrityViolationException(
			final DataIntegrityViolationException exception) {

		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("Data is present already !!");
		error.setErrorkey("Data_Redendency");
		return error;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponceDto handleValidatioinException(final MethodArgumentNotValidException exception) {

		List<String> details = new ArrayList<>();

		for (ObjectError error : exception.getBindingResult().getAllErrors()) {

			details.add(error.getDefaultMessage());

		}

		ErrorResponceDto error = new ErrorResponceDto();
		error.setError(details.get(0).split("\\*", 2)[0]);
		error.setErrorkey(details.get(0).split("\\*", 2)[1]);
		return error;

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorResponceDto handleHttpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {

		ErrorResponceDto errorResponseDto = new ErrorResponceDto();
		errorResponseDto.setError("Method does not supported ");
		errorResponseDto.setErrorkey("Please check your method type");

		return errorResponseDto;

	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponceDto handleAccessDeniedException(final AccessDeniedException exception) {

		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("Access Denied");
		error.setErrorkey("accessDenied");
		return error;

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponceDto noHandlerFoundException(final NoHandlerFoundException exception) {

		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("URL not Found, Please check URL");
		error.setErrorkey("URLNotFound");
		return error;

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponceDto handleException(final Exception exception, HttpServletRequest request)
			throws IOException {

		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("Something went wrong. ");
		error.setErrorkey("something went wrong.");
		return error;

	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponceDto handledMaxUploadSizeExceededException(
			final MaxUploadSizeExceededException exceededException) {
		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("Please decrease your file size");
		error.setErrorkey("File size should be below 20MB");
		return error;

	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponceDto handleJwtExpiredException(final ExpiredJwtException jwtExpired) {
		ErrorResponceDto error = new ErrorResponceDto();
		error.setError("JWT expired");
		error.setErrorkey("jwtExpired");
		return error;
	}
}
