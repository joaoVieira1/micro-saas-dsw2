package com.microsaas.tattoo.model.entity;

import java.time.LocalDateTime;

import com.microsaas.tattoo.model.utils.StatusAgenda;

public class Agenda {
    private int id;
    private int prestadorId;
    private Integer clienteId;
    private LocalDateTime dataHora;
    private StatusAgenda status; // "OCUPADO" ou "DESOCUPADO"
    private String descricao;
    private String dataHoraFormatada;

    public Agenda() {super();}

    public Agenda(int id, int prestadorId, Integer clienteId, LocalDateTime dataHora, StatusAgenda status, String descricao) {
        this.id = id;
        this.prestadorId = prestadorId;
        this.clienteId = clienteId;
        this.dataHora = dataHora;
        this.status = status;
        this.descricao = descricao;
    }
    
    public String getDataHoraFormatada() {
        return dataHoraFormatada;
    }

    public void setDataHoraFormatada(String dataHoraFormatada) {
        this.dataHoraFormatada = dataHoraFormatada;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(int prestadorId) {
        this.prestadorId = prestadorId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusAgenda getStatus() {
        return status;
    }

    public void setStatus(StatusAgenda status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", prestadorId=" + prestadorId + ", clienteId=" + clienteId + ", dataHora="
				+ dataHora + ", status=" + status + ", descricao=" + descricao + ", dataHoraFormatada="
				+ dataHoraFormatada + "]";
	}
    
    
}
