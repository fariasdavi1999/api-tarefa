package com.backendcrudangular.backendcrudangular.model;

import java.math.BigDecimal;

public class RelatorioTarefa {

	private TipoRelatorio tipo;

	private Tarefa tarefa;

	private BigDecimal total;

	public RelatorioTarefa(TipoRelatorio tipo, Tarefa tarefa, BigDecimal total) {
		this.tipo = tipo;
		this.tarefa = tarefa;
		this.total = total;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public TipoRelatorio getTipo() {
		return tipo;
	}

	public void setTipo(TipoRelatorio tipo) {
		this.tipo = tipo;
	}

}
