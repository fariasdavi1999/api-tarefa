package edu.davi.api.tarefa.controller;

import edu.davi.api.tarefa.model.Tarefa;
import edu.davi.api.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

	private final TarefaService tarefaService;

	@Autowired
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}

	@GetMapping("/listar-tarefas")
	public List<Tarefa> listarTarefas() {

		return tarefaService.listarTarefas();
	}


	@GetMapping("/feito/{feito}")
	public Set<Tarefa> findByFeito(@Valid @PathVariable Boolean feito) {

		return tarefaService.findByFeito(feito);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> listarPorId(@Valid @PathVariable Long id) {

		Optional<Tarefa> tarefa = tarefaService.listarPorId(id);
		return tarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PostMapping
	public ResponseEntity<Tarefa> salvar(@Valid @RequestBody Tarefa tarefa) {
		// TODO não usar tarefa pura, trocar por tarefaDTO
		Tarefa tarefaSalva = tarefaService.salvar(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> alterar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		Tarefa tarefaEditada = tarefaService.alterar(id, tarefa);

		return new ResponseEntity<>(tarefaEditada, HttpStatus.ACCEPTED);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		tarefaService.deletar(id);
	}

}
