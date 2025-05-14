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
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.utils.StatusAgenda;

public class PrestadorDao {

	private final Connection connection;

	public PrestadorDao(Connection connection) {
		this.connection = connection;
	}

	private static final String RETORNAR_PRESTADOR_POR_NOME_FANTASIA = "select * from prestador where id = ?";
	private static final String RETORNAR_HORARIOS_PRESTADOR = "SELECT * FROM agendamento WHERE prestador_id = ?";
	private static final String TOTAL_TATUADORES = "SELECT COUNT(id) AS totalTatuadores FROM prestador";
	private int paginacao = 3;
	
	public int getTotalTatuadores() throws SQLException {
		int totalTatuadores = 0;
		
		try(var stmt = connection.prepareCall(TOTAL_TATUADORES)){
			ResultSet rs = stmt.executeQuery();
			rs.next();
			totalTatuadores = rs.getInt("totalTatuadores");
			totalTatuadores /= paginacao;
		}
		
		return totalTatuadores;
	}
	
	public Prestador retornarPrestadorPeloIdDoUsuarioLogado(int id) throws SQLException {
		Prestador prestador = null;

		if (id != 0) {
			try (var stmt = connection.prepareStatement(RETORNAR_PRESTADOR_POR_NOME_FANTASIA)) {
				stmt.setInt(1, id);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					prestador = new Prestador(rs.getString("nome_fantasia"), rs.getString("nome_completo"),
							rs.getString("foto_perfil"), rs.getString("endereco"),
							rs.getString("descricao"), rs.getString("cidade_prestador"));
				}
			}
		}

		return prestador;
	}
	
	public List<Prestador> retornarPrestadores(String cidadeFiltro)throws SQLException{
		List<Prestador> prestadores = new ArrayList<>();
		
		String sql = "SELECT * FROM prestador";
		
		if (cidadeFiltro != null && !cidadeFiltro.isEmpty()) {
			sql += " WHERE cidade_prestador = ?";
		}
		
		
		try(var stmt = connection.prepareStatement(sql)){
			if (cidadeFiltro != null && !cidadeFiltro.isEmpty()) {
				stmt.setString(1, cidadeFiltro);
			}
			
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
