package edu.davi.api.tarefa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.davi.api.tarefa.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//	filtro achar por cpf 
	Optional<Cliente> findByCpfCliente(String cpfCliente);

}
