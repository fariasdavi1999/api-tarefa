package com.backendcrudangular.backendcrudangular.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

	@Column(name = "tarefa_descricao", nullable = false)
	private String descricao;

	@Column(name = "tarefa_feito", nullable = false )
	private Boolean feito;

//	data de cadastro da tarefa
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

//	data de finalizacao da tarefa
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "dt_conclusao")
	private LocalDateTime dataConclusao;

//	varias tarefas para um cliente
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	// setar a data de cadastro da tarefa para cadastrar data automático assim que
	// for salvo
	@PrePersist
	public void beforeSave() {
		setDataCadastro(LocalDateTime.now());
	}

	// setar a data de conclusao da tarefa quando getFeito for true
	@PreUpdate
	public void depoisDeFeito() {
		if (getFeito() == true) {
			setDataConclusao(LocalDateTime.now());
		} else {
			setDataConclusao(null);
		}
	}



}
