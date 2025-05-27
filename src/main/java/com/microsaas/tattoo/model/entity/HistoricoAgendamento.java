package com.microsaas.tattoo.model.entity;

import java.time.LocalDateTime;

public class HistoricoAgendamento {
	
	private int id;
	private int agendamentoId;
	private int prestadorId;
	private LocalDateTime dataAgendamento;
	private LocalDateTime dataAlteracao;
	
	 public HistoricoAgendamento() {super();}
	
	public HistoricoAgendamento(int id, int agendamentoId, int prestadorId, LocalDateTime dataAgendamento,
			LocalDateTime dataAlteracao) {
		super();
		this.id = id;
		this.agendamentoId = agendamentoId;
		this.prestadorId = prestadorId;
		this.dataAgendamento = dataAgendamento;
		this.dataAlteracao = dataAlteracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgendamentoId() {
		return agendamentoId;
	}

	public void setAgendamentoId(int agendamentoId) {
		this.agendamentoId = agendamentoId;
	}

	public int getPrestadorId() {
		return prestadorId;
	}

	public void setPrestadorId(int prestadorId) {
		this.prestadorId = prestadorId;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "HistoricoAgendamento [id=" + id + ", agendamentoId=" + agendamentoId + ", prestadorId=" + prestadorId
				+ ", dataAgendamento=" + dataAgendamento + ", dataAlteracao=" + dataAlteracao + "]";
	}
	
	

}
