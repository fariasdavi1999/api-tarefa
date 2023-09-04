package com.backendcrudangular.backendcrudangular.controller;

import com.backendcrudangular.backendcrudangular.model.Cliente;
import com.backendcrudangular.backendcrudangular.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	
	@GetMapping("/listar-clientes")
	public List<Cliente> listarTodos() {

		return clienteService.listarTodos();

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@Valid @PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.listarPorId(id);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	
	@GetMapping("/cpf/{cpfCliente}")
	public ResponseEntity<Cliente> findByCpfCliente(@Valid @PathVariable String cpfCliente) {
		Optional<Cliente> cliente = clienteService.findByCpfCliente(cpfCliente);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente) {

		Cliente clienteSalvo = clienteService.salvar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {

		Optional<Cliente> clienteSalvo = clienteService.listarPorId(id);

		if (!clienteSalvo.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		clienteSalvo.get().setNomeCliente(cliente.getNomeCliente());
		clienteSalvo.get().setCpfCliente(cliente.getCpfCliente());
		clienteSalvo.get().setDataNasc(cliente.getDataNasc());
		
		Cliente clienteEditar = clienteService.salvar(clienteSalvo.get());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteEditar);

	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		clienteService.deletar(id);

	}

}
