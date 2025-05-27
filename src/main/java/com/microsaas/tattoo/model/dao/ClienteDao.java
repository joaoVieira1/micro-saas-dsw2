package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.utils.StatusAgenda;

public class ClienteDao {
	
	private final Connection connection;

	public ClienteDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String RETORNAR_HORARIOS_AGENDADOS = "SELECT * FROM agendamento WHERE cliente_id = ?";
	
	public List<Agenda> retornarAngendamentos(int clienteId) throws SQLException{
		List<Agenda> agendamentos = new ArrayList<>();
		
		if(clienteId != 0) {
			try(var stmt = connection.prepareStatement(RETORNAR_HORARIOS_AGENDADOS)){
				stmt.setInt(1, clienteId);
				
				
				ResultSet rs = stmt.executeQuery();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
				
				while(rs.next()) {
					Agenda a = new Agenda();
					
					a.setId(rs.getInt("id"));
					a.setClienteId(rs.getInt("cliente_id"));
					a.setPrestadorId(rs.getInt("prestador_id"));
					a.setAceito(rs.getBoolean("isAceito"));
					
					String statusString = rs.getString("status");
					StatusAgenda status = StatusAgenda.valueOf(statusString);
					a.setStatus(status);
					
					Timestamp timestamp = rs.getTimestamp("data_hora");
					LocalDateTime dataHora = timestamp.toLocalDateTime();
					a.setDataHora(dataHora);
					
					String dataFormatada = dataHora.format(formatter);
				    a.setDataHoraFormatada(dataFormatada);
					
					agendamentos.add(a);
				}
				
				rs.close();
			}
		}
		
		return agendamentos;
	}


}
