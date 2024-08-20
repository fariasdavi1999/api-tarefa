package edu.davi.api.tarefa.service;

import edu.davi.api.tarefa.model.Tarefa;
import edu.davi.api.tarefa.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TarefaService {

	private final TarefaRepository tarefaRepository;

	public TarefaService(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}

	public List<Tarefa> listarTarefas() {
		List<Tarefa> result;
		result = tarefaRepository.findAll();
		if (result.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		return result;
	}

	public Set<Tarefa> findByFeito(Boolean feito) {
		Set<Tarefa> result;
		result = tarefaRepository.findByFeito(feito);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		return result;
	}

	public Optional<Tarefa> listarPorId(Long id) {
		Optional<Tarefa> result;
		result = tarefaRepository.findById(id);
		if (result.isPresent()) {
			return result;
		}
		throw new EntityNotFoundException("Not found");
	}

	public Tarefa salvar(Tarefa tarefa) {

		return tarefaRepository.save(tarefa);
	}

	public void deletar(Long id) {
		tarefaRepository.deleteById(id);
	}

	public Tarefa alterar(Long id, Tarefa tarefa) {

		Optional<Tarefa> tarefaSalva = tarefaRepository.findById(id);

		if (tarefaSalva.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		tarefaSalva.get().setNomeTarefa(tarefa.getNomeTarefa());
		tarefaSalva.get().setDescricao(tarefa.getDescricao());
		tarefaSalva.get().setFeito(tarefa.getFeito());
		tarefaSalva.get().setDataConclusao(tarefa.getDataConclusao());

		return tarefaRepository.save(tarefa);
	}

}
