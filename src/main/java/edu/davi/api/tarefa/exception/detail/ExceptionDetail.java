package edu.davi.api.tarefa.exception.detail;

import java.time.LocalDateTime;

public record ExceptionDetail(
		LocalDateTime timestamp,
		String status,
		int statusCode,
		String message
) {
}
