package edu.davi.api.tarefa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "funcionario")
public class Funcionario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_funcionario", nullable = false)
	private String nomeFuncionario;

	@Schema(description = "CPF do funcionário", example = "12345678900", minLength = 11)
	@Column(name = "cpf_funcionario", nullable = false, unique = true, length = 11)
	private String cpfFuncionario;

	@Schema(description = "Data de Nascimento", example = "1999-05-04")
	@Column(name = "data_nasc", nullable = false)
	private LocalDate dataNasc;

	//um funcionario para varias tarefas
//	 TODO tentar trocar por many to one por problemas de performance
//	https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
	@OneToMany(targetEntity = Tarefa.class)
	@JoinColumn(name = "tarefa_id")
	private Set<Tarefa> tarefas;

}
