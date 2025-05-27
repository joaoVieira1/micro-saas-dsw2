package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoricoAgendamentoDao {

	private final Connection connection;

	public HistoricoAgendamentoDao(Connection connection) {
		this.connection = connection;
	}
	
	
	private static final String INSERIR_HISTORICO = "INSERT INTO historico_agendamento(agendamento_id,prestador_id,data_agendamento) VALUES(?,?,?)";
	
	
	
	private boolean inserirHistorico(int agendamentoId, int prestadorId, String horario) throws SQLException {
		int rows = 0;
		
		if (horario != null) {
			try(var statement = connection.prepareStatement(INSERIR_HISTORICO)){
				statement.setInt(1, agendamentoId);
				statement.setInt(2, prestadorId);
				statement.setString(3, horario);
				rows = statement.executeUpdate();
			}
		}
		
		return rows > 0;
	}
	

	
	
	
	
}
