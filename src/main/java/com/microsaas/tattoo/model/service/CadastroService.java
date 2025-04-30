package com.microsaas.tattoo.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Cliente;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.Usuario;

import jakarta.servlet.ServletException;

public class CadastroService {
	
	public boolean cadastrarCliente(Usuario usuario, Cliente cliente) throws ServletException, SQLException {
		Connection connection = null;
		Boolean salvo = false;
		
		try{
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);
			
			UsuarioDao dao = new UsuarioDao(connection);
			
			int idCliente = dao.inserirCliente(cliente);
			usuario.setRefId(idCliente);
			
			salvo = dao.inserirUsuario(usuario);
			
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
	
	public boolean cadastrarPrestador(Usuario usuario, Prestador prestador) throws ServletException, SQLException {
		Connection connection = null;
		boolean salvo = false;
		
		try {
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);
			
			UsuarioDao dao = new UsuarioDao(connection);
			
			int idPrestador = dao.inserirPrestador(prestador);
			usuario.setRefId(idPrestador);
			
			salvo = dao.inserirUsuario(usuario);
			
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
	
	public String tratarErroCadastroCliente(SQLException e) {
		if(e.getErrorCode() == 1062) {
			String error = e.getMessage();
			
			if(error.contains("cpf")) return "CPF já cadastrado!";
			if(error.contains("email")) return "Email já cadastrado!"; 
		}
		
		return "Erro ao cadastrar usuário!";
	}
	
	public String tratarErroCadastroPrestador(SQLException e) {
		if(e.getErrorCode() == 1062) {
			String error = e.getMessage();
			
			if(error.contains("nome_fantasia")) return "Apelido já cadastrado!";
			if(error.contains("email")) return "Email já cadastrado!"; 
		}
		
		return "Erro ao cadastrar usuário!";
	}
}
