package edu.davi.api.tarefa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.davi.api.tarefa.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	//filtro achar por feito true
	List<Tarefa> findByFeito(Boolean feito);


}
