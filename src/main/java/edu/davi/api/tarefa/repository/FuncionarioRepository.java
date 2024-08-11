package edu.davi.api.tarefa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.davi.api.tarefa.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

//	filtro achar por cpf 
	Optional<Funcionario> findByCpfFuncionario(String cpfFuncionario);

}
