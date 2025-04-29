package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Cliente;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;

import jakarta.servlet.ServletException;

public class UsuarioDao {
	
	private static final String INSERIR_CLIENTE = "INSERT INTO cliente (nome, CPF, endereco, contato) VALUES (?, ?, ?, ?)";
	private static final String INSERIR_PRESTADOR = "INSERT INTO prestador (nome_fantasia, nome_completo, foto_perfil, endereco, descricao, cidade_prestador) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String INSERIR_USUARIO = "INSERT INTO usuario (email, senha, tipo_usuario, ref_id) VALUES (?, ?, ?, ?)";
	private static final String RETORNAR_USUARIO_PELO_EMAIL = "SELECT * FROM usuario WHERE email = ?";
	
	public Usuario retornarUsuarioPeloEmail(String email, Connection connection) throws SQLException {
		Usuario usuario = null;
		
		if(email != null) {
			try(var statement = connection.prepareStatement(RETORNAR_USUARIO_PELO_EMAIL)){
				statement.setString(1, email);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					usuario = new Usuario();
					usuario.setEmail(result.getString("email"));
					usuario.setSenha(result.getString("senha"));
					String tipoUsuarioString = result.getString("tipo_usuario");
					TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioString);
					usuario.setTipoUsuario(tipoUsuario);
					usuario.setRefId(result.getInt("ref_id"));
				}
			}
		}
		
		return usuario;
	}
	
	public boolean inserirUsuario(Usuario usuario, Connection connection) throws ServletException, SQLException{
		int rows = 0;
		
		if(usuario != null) {
			try(var statement = connection.prepareStatement(INSERIR_USUARIO)){
				statement.setString(1, usuario.getEmail());
				statement.setString(2, usuario.getSenha());
				statement.setString(3, usuario.getTipoUsuario().name());
				statement.setInt(4, usuario.getRefId());
					
				rows = statement.executeUpdate();
			}
			
		}
		
		return rows > 0;
	}
	
	public int inserirCliente(Cliente cliente, Connection connection) throws ServletException, SQLException {
		int idCliente = 0;
		
		if(cliente != null) {
			try(var statement = connection.prepareStatement(INSERIR_CLIENTE, Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, cliente.getNome());
				statement.setString(2, cliente.getCPF());
				statement.setString(3, cliente.getEndereco());
				statement.setString(4, cliente.getContato());
					
				statement.executeUpdate();
				
				try(ResultSet generatedKey = statement.getGeneratedKeys()){
					if(generatedKey.next()) {
						idCliente = generatedKey.getInt(1);
					}
					
				}
	
			};	
		}
		
		return idCliente;
	}
	
	public int inserirPrestador(Prestador prestador, Connection connection) throws ServletException, SQLException {
		int idPrestador = 0;
		
		if(prestador != null) {
			try(var statement = connection.prepareStatement(INSERIR_PRESTADOR, Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, prestador.getNomeFantasia());
				statement.setString(2, prestador.getNomeCompleto());
				statement.setString(3, prestador.getFoto());
				statement.setString(4, prestador.getEndereco());
				statement.setString(5, prestador.getDescricao());
				statement.setString(6, prestador.getCidadePrestador());
					
				statement.executeUpdate();
					
				try(ResultSet generatedKey = statement.getGeneratedKeys()){
					if(generatedKey.next()){
						idPrestador = generatedKey.getInt(1);
					}
					
				}
			
			};
				
		}
		
		return idPrestador;
	}
	
	
}
