package edu.davi.api.tarefa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.davi.api.tarefa.model.Funcionario;
import edu.davi.api.tarefa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> listarTodos() {

		return funcionarioRepository.findAll();

	}

	public Optional<Funcionario> listarPorId(Long id) {

		return funcionarioRepository.findById(id);

	}

	public Optional<Funcionario> findByCpfFuncionario(String cpfFuncionario) {
		return funcionarioRepository.findByCpfFuncionario(cpfFuncionario);
	}

	public Funcionario salvar(Funcionario funcionario) {

		return funcionarioRepository.save(funcionario);

	}

	public void deletar(Long id) {
		funcionarioRepository.deleteById(id);
	}

	public Funcionario alterar(Long id, Funcionario funcionario) {

		funcionario.setId(id);
		return funcionarioRepository.save(funcionario);

	}

}
