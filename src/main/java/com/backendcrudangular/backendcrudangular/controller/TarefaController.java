package com.backendcrudangular.backendcrudangular.controller;

import com.backendcrudangular.backendcrudangular.model.Tarefa;
import com.backendcrudangular.backendcrudangular.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	
	@GetMapping("/listar-tarefas")
	public List<Tarefa> listarTarefas() {

		return tarefaService.listarTarefas();

	}

	
	@GetMapping("/feito/{feito}")
	public List<Tarefa> findByFeito(@Valid @PathVariable Boolean feito) {

		return tarefaService.findByFeito(feito);

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> listarPorId(@Valid @PathVariable Long id) {

		Optional<Tarefa> tarefa = tarefaService.listarPorId(id);
		return tarefa.isPresent() ? ResponseEntity.ok(tarefa.get()) : ResponseEntity.notFound().build();

	}

	
	@PostMapping
	public ResponseEntity<Tarefa> salvar(@Valid @RequestBody Tarefa tarefa) {

		Tarefa tarefaSalva = tarefaService.salvar(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);

	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> alterar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {

		Optional<Tarefa> tarefaSalva = tarefaService.listarPorId(id);

		if (!tarefaSalva.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		tarefaSalva.get().setNomeTarefa(tarefa.getNomeTarefa());
		tarefaSalva.get().setDescricao(tarefa.getDescricao());
		tarefaSalva.get().setFeito(tarefa.getFeito());
		tarefaSalva.get().setDataConclusao(tarefa.getDataConclusao());
		tarefaSalva.get().setCliente(tarefa.getCliente());
		

		Tarefa tarefaEditar = tarefaService.salvar(tarefaSalva.get());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarefaEditar);

	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		tarefaService.deletar(id);

	}

}
