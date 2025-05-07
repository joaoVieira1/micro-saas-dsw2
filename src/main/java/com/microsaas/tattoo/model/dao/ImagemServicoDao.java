package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.model.entity.ImagemServico;
import com.microsaas.tattoo.model.entity.Prestador;

public class ImagemServicoDao {

	private final Connection connection;

	public ImagemServicoDao(Connection connection) {
		this.connection = connection;
	}
	
	private static final String LISTAR_IMAGEM_POR_PRESTADOR = "SELECT * FROM imagem_servico WHERE prestador_id = ?";
	private static final String INSERIR_IMAGEM_SERVICO = "insert into imagem_servico(foto,descricao,prestador_id) values(?, ?, ?)";

	
	public List<ImagemServico> listarImagensPorPrestador(int id) throws SQLException {
        List<ImagemServico> imagens = new ArrayList<>();

		if (id != 0) {
			try (var statement = connection.prepareStatement(LISTAR_IMAGEM_POR_PRESTADOR)) {
				statement.setInt(1, id);

				ResultSet rs = statement.executeQuery();
				 while (rs.next()) {
		                ImagemServico img = new ImagemServico();
		                img.setFoto(rs.getString("foto"));
		                img.setDescricao(rs.getString("descricao"));
		                imagens.add(img);
		            }

			}
		}

		return imagens;
	}
	
	public boolean inserirImagem(ImagemServico imagem, int prestadorID) throws SQLException {
		int rows = 0;
		
		if(imagem != null) {
			try(var statement = connection.prepareStatement(INSERIR_IMAGEM_SERVICO)){
				statement.setString(1, imagem.getFoto());
				statement.setString(2, imagem.getDescricao());
				statement.setInt(3, prestadorID);
					
				rows = statement.executeUpdate();
			}
			
		}
		
		return rows > 0;
	}

}
