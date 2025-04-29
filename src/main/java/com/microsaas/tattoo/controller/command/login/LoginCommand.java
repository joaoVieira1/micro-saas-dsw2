package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.utils.CriptografiaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		
		Connection connection = null;
		String view = null;
		
		if(email != null && !email.isEmpty()) {
			UsuarioDao dao = new UsuarioDao();
			
			try {
				connection = DatabaseConnection.getConnection();
				
				Usuario usuario = dao.retornarUsuarioPeloEmail(email, connection);
				
				if(usuario != null) {
					if(CriptografiaUtils.verificarSenha(senha, usuario.getSenha()) && email.equals(usuario.getEmail())) {
						var session = request.getSession(true);
						session.setAttribute("usuario", usuario);
						session.setMaxInactiveInterval(24 * 60 * 60);
						session.setAttribute("cadastrado", "cadastrado");
						
						if(usuario.getTipoUsuario() == TipoUsuario.CLIENTE) {
							view = "login.do?action=getPaginaCliente";
						}else {
							view = "login.do?action=getPaginaPrestador";
						}
						
					}else {
						request.setAttribute("mensagem", "Email ou senha inválidos");
						view = "index.jsp";
					}
				}else {
					request.setAttribute("mensagem", "Este email não está cadastrado");
					view = "index.jsp";
				}

			}catch(SQLException e) {
				request.setAttribute("mensagem", "Erro ao efetuar login");
				view = "index.jsp";
				e.printStackTrace();
			}
			
			
		}
		
		
		return view;
	}

}
