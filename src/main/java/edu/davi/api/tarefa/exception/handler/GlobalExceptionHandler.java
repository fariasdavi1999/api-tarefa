package edu.davi.api.tarefa.exception.handler;

import edu.davi.api.tarefa.exception.BusinessException;
import edu.davi.api.tarefa.exception.detail.ExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDetail> businessException(BusinessException ex) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new ExceptionDetail(
						LocalDateTime.now(),
						ex.getMessage(),
						HttpStatus.CONFLICT.value(),
						ex.getClass().getName()
				));

	}
}
