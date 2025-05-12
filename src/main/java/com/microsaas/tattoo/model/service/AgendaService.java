package com.microsaas.tattoo.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.dao.AgendaDao;
import com.microsaas.tattoo.model.dao.ImagemServicoDao;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.entity.ImagemServico;

import jakarta.servlet.ServletException;

public class AgendaService {
	
	public List<Agenda> retornarAgendamentos(int prestador_id) throws SQLException, ServletException{
		List<Agenda> agendamentos = new ArrayList<>();
		
		Connection connection = DatabaseConnection.getConnection();
		PrestadorDao dao = new PrestadorDao(connection);
		
		agendamentos = dao.retornarAngendamentos(prestador_id);
		
		return agendamentos;
	}
	
	public boolean postarHorario(String horario, int prestadorID)throws ServletException, SQLException {
		
		
		Connection connection = null;
		Boolean salvo = false;
		
		try{
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);
			
			AgendaDao dao = new AgendaDao(connection);
			
			salvo = dao.inserirHorario(horario, prestadorID);
			
			connection.commit();
			
			return salvo;
		}catch(SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw e;
		}finally{
			if(connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}		
	}

}
