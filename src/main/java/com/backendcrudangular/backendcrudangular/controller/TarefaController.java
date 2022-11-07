package com.backendcrudangular.backendcrudangular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/{id}")
	public Optional<Tarefa> listarPorId(@PathVariable Long id) {

		return tarefaService.listarPorId(id);

	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public Tarefa salvar(@RequestBody Tarefa tarefa) {

		return tarefaService.salvar(tarefa);
	}
	
	

}
