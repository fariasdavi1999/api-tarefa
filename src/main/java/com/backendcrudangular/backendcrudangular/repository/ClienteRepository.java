package com.backendcrudangular.backendcrudangular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendcrudangular.backendcrudangular.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
//	filtro achar por cpf 
	Optional<Cliente> findByCpfCliente(String cpfCliente);
	

}
