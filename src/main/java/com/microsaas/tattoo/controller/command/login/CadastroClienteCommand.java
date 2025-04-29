package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Cliente;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.utils.CriptografiaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroClienteCommand implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dados usuário
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String confirmarSenha = request.getParameter("confirmarSenha");
		
		//dados cliente
		String nome = request.getParameter("nome");
		String CPF = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String contato = request.getParameter("contato");
		
		//auxiliares
		String mensagem = "";
		Boolean salvo = false; 
		Connection connection = null;
		
		if(senha.equals(confirmarSenha)) {
			UsuarioDao dao = new UsuarioDao();
			
			try {
				connection = DatabaseConnection.getConnection();
				connection.setAutoCommit(false);
				
				//persiste o cliente
				Cliente novoCliente = new Cliente(nome, CPF, endereco, contato);
				int idCliente = dao.inserirCliente(novoCliente, connection);
				
				//criptografa e persiste o usuário utilizando o id gerado do cliente como referência
				String senhaCriptografada = CriptografiaUtils.criptografarSenha(senha);
				Usuario novoUsuario = new Usuario(email, senhaCriptografada, TipoUsuario.CLIENTE, idCliente);
				salvo = dao.inserirUsuario(novoUsuario, connection);
				
				//commit da persistência
				connection.commit();
				
				mensagem = "Cadastro realizado com sucesso!";
				
			}catch(SQLException e) {
				//caso estore uma exceção damos um rollback para manter a integridada e não criar um usuário sem referência
				if(connection != null) {
					try {
						connection.rollback();
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				
				//número de erro para duplicação de dados
				if(e.getErrorCode() == 1062) {
					String error = e.getMessage();
					
					if(error.contains("cpf")) {
						mensagem = "CPF já cadastrado!";
					}
					
					if(error.contains("email")) {
						mensagem = "Email já cadastrado!";
					}
				}
			}finally {
				if(connection != null) {
					try {
						connection.setAutoCommit(true);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
			
		}else {
			mensagem = "Certifique-se de confirmar a senha corretamente";
		}
		
		request.setAttribute("mensagem", mensagem);
		request.setAttribute("salvo", salvo);
		
		return "cadastroCliente.jsp";
	}

}
