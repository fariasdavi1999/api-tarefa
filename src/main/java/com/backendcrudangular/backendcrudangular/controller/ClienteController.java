package com.backendcrudangular.backendcrudangular.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backendcrudangular.backendcrudangular.model.Cliente;
import com.backendcrudangular.backendcrudangular.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@CrossOrigin(origins = "*")
	@GetMapping("/listar-clientes")
	public List<Cliente> listarTodos() {

		return clienteService.listarTodos();

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@Valid @PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.listarPorId(id);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/cpf/{cpfCliente}")
	public ResponseEntity<Cliente> findByCpfCliente(@Valid @PathVariable String cpfCliente) {
		Optional<Cliente> cliente = clienteService.findByCpfCliente(cpfCliente);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente) {

		Cliente clienteSalvo = clienteService.salvar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {

		Optional<Cliente> clienteSalvo = clienteService.listarPorId(id);

		if (!clienteSalvo.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		clienteSalvo.get().setNomeCliente(cliente.getNomeCliente());
		Cliente clienteEditar = clienteService.salvar(clienteSalvo.get());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteEditar);

	}

//	public ResponseEntity<Usuario> alterar(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
//
//		Optional<Usuario> usuarioSalvo = usuarioService.getById(id);
//
//		if (!usuarioSalvo.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		usuarioSalvo.get().setNome(usuario.getNome());
//		Usuario usuarioEditar = usuarioService.PersistirUsuario(usuarioSalvo.get());
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioEditar);
//	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		clienteService.deletar(id);

	}

}
