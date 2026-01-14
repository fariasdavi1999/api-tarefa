package edu.davi.api.tarefa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("test")
class TarefaApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> {
			// Verifica se o contexto da aplicação carrega corretamente
		});
	}

	@Test
	void mainMethodRuns() {
		assertDoesNotThrow(() -> TarefaApplication.main(new String[]{}));
	}

}