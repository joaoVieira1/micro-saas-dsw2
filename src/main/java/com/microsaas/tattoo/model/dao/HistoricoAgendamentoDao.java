package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.entity.HistoricoAgendamento;

public class HistoricoAgendamentoDao {

	private final Connection connection;

	public HistoricoAgendamentoDao(Connection connection) {
		this.connection = connection;
	}
	
	
	 private static final String BUSCAR_HISTORICO = "SELECT data_alteracao, data_agendamento, status FROM historico_agendamento WHERE prestador_id = ?";
	
	 
	 public List<HistoricoAgendamento> buscarPorAgendamento(int prestadorId) {
	        List<HistoricoAgendamento> historicos = new ArrayList<>();

	        try (var stmt = connection.prepareStatement(BUSCAR_HISTORICO)) {
	            stmt.setInt(1, prestadorId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    LocalDateTime dataAlteracao = rs.getTimestamp("data_alteracao").toLocalDateTime();
	                    LocalDateTime dataAgendamento = rs.getTimestamp("data_agendamento").toLocalDateTime();
	                    String status = rs.getString("status");

	                    HistoricoAgendamento historico = new HistoricoAgendamento();
	                    historico.setDataAgendamento(dataAgendamento);
	                    historico.setDataAlteracao(dataAlteracao);
	                    historico.setStatus(status);
	                    historicos.add(historico);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return historicos;
	    }

	
	
	
	
}
