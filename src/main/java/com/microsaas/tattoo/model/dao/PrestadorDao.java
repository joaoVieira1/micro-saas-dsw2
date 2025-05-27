package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.utils.StatusAgenda;

public class PrestadorDao {

	private final Connection connection;

	public PrestadorDao(Connection connection) {
		this.connection = connection;
	}

	private static final String RETORNAR_PRESTADOR_POR_NOME_FANTASIA = "select * from prestador where id = ?";
	private static final String RETORNAR_HORARIOS_PRESTADOR = "SELECT * FROM agendamento WHERE prestador_id = ?";
	private static final String RETORNAR_HORARIOS_DESOCUPADOS = "SELECT * FROM agendamento WHERE prestador_id = ? AND status = 'DESOCUPADO'";
	private static final String RETORNAR_PRESTADOR_ID = "SELECT * FROM prestador WHERE id = ?";
	private int tatuadoresPorPagina = 3;
	
	public int getTotalTatuadores(String cidadeFiltro) throws SQLException {
		int totalTatuadores = 0;
		String sql = "SELECT COUNT(id) AS totalTatuadores FROM prestador";
		
		if (cidadeFiltro != null && !cidadeFiltro.isEmpty()) {
			sql += " WHERE cidade_prestador = ?";
		}
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        if (cidadeFiltro != null && !cidadeFiltro.isEmpty()) {
	            stmt.setString(1, cidadeFiltro);
	        }
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            totalTatuadores = rs.getInt("totalTatuadores");
	        }
	        
	        rs.close();
	    }
		
		return (int) Math.ceil((double) totalTatuadores / tatuadoresPorPagina);
	}
	
	public Prestador retornarPrestadorPeloIdDoUsuarioLogado(int id) throws SQLException {
		Prestador prestador = null;

		if (id != 0) {
			try (var stmt = connection.prepareStatement(RETORNAR_PRESTADOR_POR_NOME_FANTASIA)) {
				stmt.setInt(1, id);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					prestador = new Prestador();
					prestador.setId(rs.getInt("id"));
					prestador.setNomeCompleto(rs.getString("nome_completo"));
					prestador.setNomeFantasia(rs.getString("nome_fantasia"));
					prestador.setDescricao(rs.getString("descricao"));
					prestador.setFoto(rs.getString("foto_perfil"));
					prestador.setCidadePrestador(rs.getString("cidade_prestador"));
					prestador.setEndereco(rs.getString("endereco"));
				}
				
				rs.close();
			}
		}

		return prestador;
	}
	
	public List<Prestador> retornarPrestadores(String cidadeFiltro, int pagina)throws SQLException{
		List<Prestador> prestadores = new ArrayList<>();
		
		String sql = "SELECT * FROM prestador";
		boolean temFiltro = cidadeFiltro != null && !cidadeFiltro.isEmpty();
		
		if (temFiltro) {
			sql += " WHERE cidade_prestador = ?";
		}
		
		sql += " ORDER BY nome_fantasia LIMIT ? OFFSET ?";
		
		try(var stmt = connection.prepareStatement(sql)){
			int i = 1;
	        if (temFiltro) {
	            stmt.setString(i++, cidadeFiltro);
	        }

	        stmt.setInt(i++, tatuadoresPorPagina); // LIMIT
	        stmt.setInt(i, pagina * tatuadoresPorPagina);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Prestador p = new Prestador();
				
				p.setId(rs.getInt("id"));
				p.setNomeFantasia(rs.getString("nome_fantasia"));
				p.setNomeCompleto(rs.getString("nome_completo"));
				p.setFoto(rs.getString("foto_perfil"));
				p.setDescricao(rs.getString("descricao"));
				p.setCidadePrestador(rs.getString("cidade_prestador"));
				
				prestadores.add(p);
			}
			
			rs.close();
		}
		
		return prestadores;
	}
	
	public List<Agenda> retornarAngendamentos(int prestadorId) throws SQLException{
		List<Agenda> agendamentos = new ArrayList<>();
		
		if(prestadorId != 0) {
			try(var stmt = connection.prepareStatement(RETORNAR_HORARIOS_PRESTADOR)){
				stmt.setInt(1, prestadorId);
				
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
	
	public List<Agenda> retornarAngendamentosDesocupados(int prestadorId) throws SQLException{
		List<Agenda> agendamentos = new ArrayList<>();
		
		if(prestadorId != 0) {
			try(var stmt = connection.prepareStatement(RETORNAR_HORARIOS_DESOCUPADOS)){
				stmt.setInt(1, prestadorId);
				
				
				ResultSet rs = stmt.executeQuery();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
				
				while(rs.next()) {
					Agenda a = new Agenda();
					
					a.setId(rs.getInt("id"));
					a.setClienteId(rs.getInt("cliente_id"));
					a.setPrestadorId(rs.getInt("prestador_id"));
					
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
