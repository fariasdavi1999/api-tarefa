package edu.davi.api.tarefa.exception.handler;


import edu.davi.api.tarefa.exception.BusinessException;
import edu.davi.api.tarefa.exception.detail.ExceptionDetail;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDetail> handleBusinessException(BusinessException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
		                     .body(new ExceptionDetail("Bad Request! Consult the documentation",
		                                               LocalDateTime.now(),
		                                               HttpStatus.BAD_REQUEST.value(),
		                                               ex.getClass().toString(),
		                                               Map.of("Cause", ex.getMessage())));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionDetail> handleEntityNotFoundException(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                     .body(new ExceptionDetail("Not Found",
		                                               LocalDateTime.now(),
		                                               HttpStatus.NOT_FOUND.value(),
		                                               ex.getClass().toString(),
		                                               Map.of("Cause", ex.getMessage())));
	}
}
