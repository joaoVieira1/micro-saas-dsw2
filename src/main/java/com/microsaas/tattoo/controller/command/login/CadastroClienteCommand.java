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
import com.microsaas.tattoo.model.service.CadastroService;
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
		CadastroService cadastroService = new CadastroService();
		
		if(senha.equals(confirmarSenha)) {
			try {
				Cliente novoCliente = new Cliente(nome, CPF, endereco, contato);
				String senhaCriptografada = CriptografiaUtils.criptografarSenha(senha);
				Usuario novoUsuario = new Usuario(email, senhaCriptografada, TipoUsuario.CLIENTE, 0);
				
				salvo = cadastroService.cadastrarCliente(novoUsuario, novoCliente);
				mensagem = "Cadastro realizado com sucesso!";
				
			}catch(SQLException e) {
				mensagem = cadastroService.tratarErroCadastroCliente(e);
			}
		}else {
			mensagem = "Certifique-se de confirmar a senha corretamente";
		}
		
		request.setAttribute("mensagem", mensagem);
		request.setAttribute("salvo", salvo);
		
		return "cliente.do?action=getHomeCliente";
	}

}
