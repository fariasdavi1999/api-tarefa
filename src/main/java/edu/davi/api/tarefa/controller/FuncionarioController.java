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

	@Autowired
	private FuncionarioService funcionarioService;

	
	@GetMapping("/listar-funcionarios")
	public List<Funcionario> listarTodos() {

		return funcionarioService.listarTodos();

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> listarPorId(@Valid @PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioService.listarPorId(id);
		return funcionario.isPresent() ? ResponseEntity.ok(funcionario.get()) : ResponseEntity.notFound().build();
	}

	
	@GetMapping("/cpf/{cpffuncionario}")
	public ResponseEntity<Funcionario> findByCpffuncionario(@Valid @PathVariable String cpffuncionario) {
		Optional<Funcionario> funcionario = funcionarioService.findByCpfFuncionario(cpffuncionario);
		return funcionario.isPresent() ? ResponseEntity.ok(funcionario.get()) : ResponseEntity.notFound().build();
	}

	
	@PostMapping
	public ResponseEntity<Funcionario> salvar(@Valid @RequestBody Funcionario funcionario) {

		Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> alterar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {

		Optional<Funcionario> funcionarioSalvo = funcionarioService.listarPorId(id);

		if (!funcionarioSalvo.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		funcionarioSalvo.get().setNomeFuncionario(funcionario.getNomeFuncionario());
		funcionarioSalvo.get().setCpfFuncionario(funcionario.getCpfFuncionario());
		funcionarioSalvo.get().setDataNasc(funcionario.getDataNasc());
		
		Funcionario funcionarioEditar = funcionarioService.salvar(funcionarioSalvo.get());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(funcionarioEditar);

	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		funcionarioService.deletar(id);

	}

}
