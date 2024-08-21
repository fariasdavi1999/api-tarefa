package edu.davi.api.tarefa.controller;

import edu.davi.api.tarefa.model.Funcionario;
import edu.davi.api.tarefa.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping("/listar-funcionarios")
	public List<Funcionario> listarTodos() {
		return funcionarioService.listarTodos();
	}


	@GetMapping("/{id}")
	public ResponseEntity<Optional<Funcionario>> listarPorId(@Valid @PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioService.listarPorId(id);
		return new ResponseEntity<>(funcionario, HttpStatus.ACCEPTED);
	}


	@GetMapping("/cpf/{cpfFuncionario}")
	public ResponseEntity<Optional<Funcionario>> findByCpffuncionario(@Valid @PathVariable String cpfFuncionario) {
		Optional<Funcionario> funcionario = funcionarioService.findByCpfFuncionario(cpfFuncionario);
		return new ResponseEntity<>(funcionario, HttpStatus.ACCEPTED);
	}


	@PostMapping
	public ResponseEntity<Funcionario> salvar(@Valid @RequestBody Funcionario funcionario) {

		Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario);

		return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> alterar(@PathVariable Long id,
	                                           @Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioEditado = funcionarioService.alterar(id, funcionario);
		return new ResponseEntity<>(funcionarioEditado, HttpStatus.ACCEPTED);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		funcionarioService.deletar(id);
	}

}
