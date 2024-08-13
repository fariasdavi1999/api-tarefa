package edu.davi.api.tarefa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tarefa")
public class Tarefa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome_tarefa", nullable = false)
	private String nomeTarefa;

	@Column(name = "tarefa_descricao", nullable = false)
	private String descricao;

	@Column(name = "tarefa_feito", nullable = false)
	private Boolean feito;

//	data de cadastro da tarefa
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

//	data de finalizacao da tarefa
	@Column(name = "dt_conclusao")
	private LocalDateTime dataConclusao;

//	varias tarefas para um funcionario
//	@ManyToOne
//	@JoinColumn(name = "funcionario_id")
//	private Funcionario funcionario;

	// setar a data de cadastro da tarefa para cadastrar data automático assim que
	// for salvo
	@PrePersist
	public void beforeSave() {
		setDataCadastro(LocalDateTime.now());
	}

	// setar a data de conclusao da tarefa quando getFeito for true
	@PreUpdate
	public void afterDone() {
		if (getFeito()) {
			setDataConclusao(LocalDateTime.now());
		} else {
			setDataConclusao(null);
		}
	}

}
