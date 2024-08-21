package edu.davi.api.tarefa.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_tarefa", nullable = false)
	private String nomeTarefa;

	@Column(name = "tarefa_descricao", nullable = false, columnDefinition = "TEXT")
	private String descricao;

	@Column(name = "tarefa_feito", nullable = false)
	private Boolean feito;

	//	data de cadastro da tarefa
	@Schema(description = "Data de cadastro", example = "1999-05-04")
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	//	data de finalizacao da tarefa
	@Schema(description = "Data de conclusão", example = "1999-05-04")
	@Column(name = "dt_conclusao")
	private LocalDateTime dataConclusao;


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
