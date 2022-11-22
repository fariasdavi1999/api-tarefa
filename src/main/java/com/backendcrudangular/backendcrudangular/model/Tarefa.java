package com.backendcrudangular.backendcrudangular.model;

import java.time.LocalDateTime;
import java.util.Objects;

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

@Entity
@Table(name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_tarefa", nullable = false)
	private String nomeTarefa;

	@Column(name = "tarefa_descricao", nullable = false)
	private String descricao;

	@Column(name = "tarefa_feito")
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTarefa() {
		return nomeTarefa;
	}

	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFeito() {
		return feito;
	}

	public void setFeito(Boolean feito) {
		this.feito = feito;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}

}
