package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsaas.tattoo.model.entity.Prestador;

public class PrestadorDao {

	private final Connection connection;

	public PrestadorDao(Connection connection) {
		this.connection = connection;
	}

	private static final String RETORNAR_PRESTADOR_POR_NOME_FANTASIA = "select * from prestador where id = ?";

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

}
