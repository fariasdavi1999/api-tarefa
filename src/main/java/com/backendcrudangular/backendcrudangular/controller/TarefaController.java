package com.backendcrudangular.backendcrudangular.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backendcrudangular.backendcrudangular.model.Tarefa;
import com.backendcrudangular.backendcrudangular.service.TarefaService;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@CrossOrigin(origins = "*")
	@GetMapping("/listar-tarefas")
	public List<Tarefa> listarTarefas() {

		return tarefaService.listarTarefas();

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/feito/{feito}")
	public List<Tarefa> findByFeito(@Valid @PathVariable Boolean feito) {

		return tarefaService.findByFeito(feito);

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> listarPorId(@Valid @PathVariable Long id) {

		Optional<Tarefa> tarefa = tarefaService.listarPorId(id);
		return tarefa.isPresent() ? ResponseEntity.ok(tarefa.get()) : ResponseEntity.notFound().build();

	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<Tarefa> salvar(@Valid @RequestBody Tarefa tarefa) {

		Tarefa tarefaSalva = tarefaService.salvar(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);

	}

	@CrossOrigin(origins = "*")
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

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		tarefaService.deletar(id);

	}

}
