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
	private static final String OCUPAR_HORARIO = "UPDATE agendamento SET cliente_id = ?, status = 'OCUPADO' WHERE id = ?";
	private static final String DESOCUPAR_HORARIO = "UPDATE agendamento SET cliente_id = null, status = 'DESOCUPADO' WHERE id = ?";
	private static final String VERIFICAR_CONFLITO = "SELECT COUNT(*) FROM agendamento WHERE prestador_id = ? AND data_hora = ?";
	private static final String CONFIRMAR_AGENDA = "UPDATE agendamento SET isAceito = ? WHERE id = ?";

	
	public boolean inserirHorario(String horario, int prestadorID) throws SQLException {
	    int rows = 0;

	    if (horario != null) {
	        try (var checkStmt = connection.prepareStatement(VERIFICAR_CONFLITO)) {
	            checkStmt.setInt(1, prestadorID);
	            checkStmt.setString(2, horario);

	            try (ResultSet rs = checkStmt.executeQuery()) {
	                if (rs.next() && rs.getInt(1) > 0) {
	                    return false;
	                }
	            }
	        }

	        try (var statement = connection.prepareStatement(INSERIR_HORARIO)) {
	            statement.setInt(1, prestadorID);
	            statement.setString(2, horario);
	            rows = statement.executeUpdate();
	        }
	    }

	    return rows > 0;
	}
	
	public boolean confirmarAgenda(int horarioId, boolean isAceito) {
		int rows = 0;
		
		try(var statement = connection.prepareStatement(CONFIRMAR_AGENDA)){
			
			statement.setBoolean(1, isAceito);
			statement.setInt(2, horarioId);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows > 0;
	}

	
	public boolean ocuparHorario(int horarioId, int clienteId) {
        int rows = 0;
        
        try (var statement = connection.prepareStatement(OCUPAR_HORARIO)) {

            statement.setInt(1, clienteId);
            statement.setInt(2, horarioId);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rows > 0;
    }
	
	public boolean desocuparHorario(int horarioId) {
        int rows = 0;
        
        try (var statement = connection.prepareStatement(DESOCUPAR_HORARIO)) {

            statement.setInt(1, horarioId);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rows > 0;
    }
}
