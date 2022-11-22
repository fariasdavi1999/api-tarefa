package com.backendcrudangular.backendcrudangular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendcrudangular.backendcrudangular.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	//filtro achar por feito true
	List<Tarefa> findByFeito(Boolean feito);


}
