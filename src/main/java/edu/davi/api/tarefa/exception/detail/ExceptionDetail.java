package edu.davi.api.tarefa.exception.detail;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionDetail(
		String title,
		LocalDateTime timestamp,
		int statusCode,
		String message,
		Map<String, String> details
) {
}
