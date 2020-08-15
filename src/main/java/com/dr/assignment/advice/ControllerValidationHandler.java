package com.dr.assignment.advice;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.dr.assignment.exception.ResourceNotFoundException;
import com.dr.assignment.model.MessageDto;
import com.dr.assignment.model.MessageDto.MessageType;

@ControllerAdvice
@ResponseBody
public class ControllerValidationHandler {
	@Autowired
	private MessageSource msgSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MessageDto processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		FieldError error = result.getFieldError();

		return processFieldError(error);
	}

	@ExceptionHandler(InternalServerError.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MessageDto internalServerError(InternalServerError ex) {

		return new MessageDto(MessageType.ERROR, ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MessageDto notFoundError(InternalServerError ex) {

		return new MessageDto(MessageType.ERROR, ex.getLocalizedMessage());
	}

	private MessageDto processFieldError(FieldError error) {
		MessageDto message = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			message = new MessageDto(MessageDto.MessageType.ERROR, msg);
		}
		return message;
	}
}
