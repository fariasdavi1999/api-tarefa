package com.backendcrudangular.backendcrudangular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.backendcrudangular.backendcrudangular.model.Tarefa;
import com.backendcrudangular.backendcrudangular.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public List<Tarefa> listarTarefas() {

		return tarefaRepository.findAll();

	}

	public List<Tarefa> findByFeito(Boolean feito){
		return tarefaRepository.findByFeito(feito);
	}
	
	
	public Optional<Tarefa> listarPorId(Long id) {

		return Optional.ofNullable(tarefaRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

	}

	public Tarefa salvar(Tarefa tarefa) {

		return tarefaRepository.save(tarefa);

	}

	public void deletar(Long id) {
		tarefaRepository.deleteById(id);
	}

	public Tarefa alterar(Long id, Tarefa tarefa) {

		tarefa.setId(id);
		return tarefaRepository.save(tarefa);

	}

}
