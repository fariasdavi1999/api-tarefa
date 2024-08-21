package edu.davi.api.tarefa.service;

import edu.davi.api.tarefa.exception.BusinessException;
import edu.davi.api.tarefa.model.Funcionario;
import edu.davi.api.tarefa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;

	@Autowired
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public List<Funcionario> listarTodos() {

		List<Funcionario> result = funcionarioRepository.findAll();

		if (result.isEmpty()) {
			throw new BusinessException("Não existem funcionários cadastrados");
		}
		return result;
	}

	public Optional<Funcionario> listarPorId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

		if (funcionario.isEmpty()) {
			throw new EntityNotFoundException("Funcionário não encontrado");
		}
		return funcionario;
	}

	public Optional<Funcionario> findByCpfFuncionario(String cpfFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByCpfFuncionario(cpfFuncionario);
		if (funcionario.isEmpty()) {
			throw new EntityNotFoundException("CPF não encontrado");
		}
		return funcionario;
	}

	public Funcionario salvar(Funcionario funcionario) {
		if (findByCpfFuncionario(funcionario.getCpfFuncionario()).isPresent()) {
			throw new BusinessException("CPF já cadastrado");
		} else if (funcionario.getCpfFuncionario().isEmpty())
			throw new BusinessException("CPF é obrigatório");
		return funcionarioRepository.save(funcionario);
	}

	public void deletar(Long id) {
		if (listarPorId(id).isPresent())
			funcionarioRepository.deleteById(id);
		throw new IllegalStateException("Não foi possivel deletar");
	}

	public Funcionario alterar(Long id, Funcionario funcionario) {

		Optional<Funcionario> funcionarioParaEditar = listarPorId(id);

		if (funcionarioParaEditar.isPresent()) {
			funcionarioParaEditar.get().setNomeFuncionario(funcionario.getNomeFuncionario());
			funcionarioParaEditar.get().setCpfFuncionario(funcionario.getCpfFuncionario());
			funcionarioParaEditar.get().setDataNasc(funcionario.getDataNasc());
			return funcionarioRepository.save(funcionarioParaEditar.get());
		}
		throw new BusinessException("Falha ao editar funcionario");
	}

}
