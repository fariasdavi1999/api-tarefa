package edu.davi.api.tarefa.repository;

import edu.davi.api.tarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	//filtro achar por feito true
	Set<Tarefa> findByFeito(Boolean feito);


}