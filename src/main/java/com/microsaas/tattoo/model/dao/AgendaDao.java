package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.entity.ImagemServico;

public class AgendaDao {

	private final Connection connection;

	public AgendaDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String INSERIR_HORARIO = "insert into agendamento (prestador_id,data_hora) values (?,?)";

	
	public boolean inserirHorario(String horario, int prestadorID) throws SQLException {
		int rows = 0;
		
		if(horario != null) {
			try(var statement = connection.prepareStatement(INSERIR_HORARIO)){
				statement.setInt(1, prestadorID);
				statement.setString(2, horario);
					
				rows = statement.executeUpdate();
			}
			
		}
		
		return rows > 0;
	}
	
}
