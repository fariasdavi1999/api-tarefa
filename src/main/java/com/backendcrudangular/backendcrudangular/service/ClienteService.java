package com.backendcrudangular.backendcrudangular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcrudangular.backendcrudangular.model.Cliente;
import com.backendcrudangular.backendcrudangular.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarTodos() {

		return clienteRepository.findAll();

	}

	public Optional<Cliente> listarPorId(Long id) {

		return clienteRepository.findById(id);

	}
	
	public Optional<Cliente> findByCpfCliente(String cpfCliente){
		return clienteRepository.findByCpfCliente(cpfCliente);
	}

	public Cliente salvar(Cliente cliente) {

		return clienteRepository.save(cliente);

	}

	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente alterar(Long id, Cliente cliente) {

		cliente.setId(id);
		return clienteRepository.save(cliente);

	}

}
