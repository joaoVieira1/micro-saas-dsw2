package com.microsaas.tattoo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Cliente;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.Usuario;

public class UsuarioDao {
	
	private static final String INSERIR_CLIENTE = "INSERT INTO cliente (nome, CPF, endereco, contato) VALUES (?, ?, ?, ?)";
	private static final String INSERIR_PRESTADOR = "INSERT INTO prestador (nome_fatasia, nome_completo, foto_perfil, endereco, descricao) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERIR_USUARIO = "INSERT INTO usuario (email, senha, tipo, ref_id) VALUES (?, ?, ?, ?)";
	

	public void registrarUsuario(Usuario usuario){
		if(usuario != null) {
			try(var connection = DatabaseConnection.getConnection();
					var statement = connection.prepareStatement(INSERIR_USUARIO)){
				
				statement.setString(1, usuario.getEmail());
				statement.setString(2, usuario.getSenha());
				statement.setString(3, usuario.getTipoUsuario().name());
				statement.setInt(4, usuario.getRefId());
				
				statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int inserirCliente(Cliente cliente) {
		int idCliente = 0;
		
		if(cliente != null) {
			try(var connection = DatabaseConnection.getConnection();
					var statement = connection.prepareStatement(INSERIR_CLIENTE, Statement.RETURN_GENERATED_KEYS)){
				
				statement.setString(1, cliente.getNome());
				statement.setString(2, cliente.getCPF());
				statement.setString(3, cliente.getEndereco());
				statement.setString(4, cliente.getContato());
				
				statement.executeUpdate();
				
				ResultSet generatedKey = statement.getGeneratedKeys();
				
				if(generatedKey.next()) {
					idCliente = generatedKey.getInt(1);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return idCliente;
	}
	
	public int inserirPrestador(Prestador prestador) {
		int idPrestador = 0;
		
		if(prestador != null) {
			try(var connection = DatabaseConnection.getConnection();
					var statement = connection.prepareStatement(INSERIR_PRESTADOR, Statement.RETURN_GENERATED_KEYS)){
				
				statement.setString(1, prestador.getNomeFantasia());
				statement.setString(2, prestador.getNomeCompleto());
				statement.setString(3, prestador.getFoto());
				statement.setString(4, prestador.getEndereco());
				statement.setString(5, prestador.getDescricao());
				
				statement.executeUpdate();
				
				ResultSet generatedKey = statement.getGeneratedKeys();
				
				if(generatedKey.next()){
					idPrestador = generatedKey.getInt(1);
				}
		
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return idPrestador;
	}
	
	
}
