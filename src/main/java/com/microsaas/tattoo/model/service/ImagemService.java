package com.microsaas.tattoo.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsaas.tattoo.model.dao.ImagemServicoDao;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.ImagemServico;

import jakarta.servlet.ServletException;

public class ImagemService {
	
	public boolean postarImagem(ImagemServico imagem, int prestadorID)throws ServletException, SQLException {
		
		
		Connection connection = null;
		Boolean salvo = false;
		
		try{
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);
			
			ImagemServicoDao dao = new ImagemServicoDao(connection);
			
			
			
			salvo = dao.inserirImagem(imagem,prestadorID);
			
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
