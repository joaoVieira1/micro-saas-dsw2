package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.LoginService;
import com.microsaas.tattoo.model.utils.CriptografiaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		LoginService loginService = new LoginService();
		
		if(email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
			request.setAttribute("mensagem", "Insira todos os campos");
			return "login.do?action=getIndex";
		}
		
		try {
			String view = loginService.loginUsuario(request, email, senha);
			return view;
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("mensagem", "Erro ao efetuar login");
			return "login.do?action=getIndex";
		}
	}

}
