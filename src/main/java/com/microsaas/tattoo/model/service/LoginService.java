package com.microsaas.tattoo.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.utils.CriptografiaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {
	
	public String loginUsuario(HttpServletRequest request, String email, String senha) throws ServletException, SQLException {
		Connection connection = null;
		
		try {
			connection = DatabaseConnection.getConnection();
			UsuarioDao dao = new UsuarioDao(connection);
			Usuario usuario = dao.retornarUsuarioPeloEmail(email);
				
			if(usuario == null) {
				request.setAttribute("mensagem", "Este email não está cadastrado");
				return "login.do?action=getIndex";
			}
			
			if(!CriptografiaUtils.verificarSenha(senha, usuario.getSenha())){
				request.setAttribute("mensagem", "Email ou senha invalidos");
				return "login.do?action=getIndex";
			}
			
			HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            session.setMaxInactiveInterval(24 * 60 * 60); 
            session.setAttribute("cadastrado", "cadastrado");
            
            return usuario.getTipoUsuario() == TipoUsuario.CLIENTE
                    ? "login.do?action=getPaginaCliente"
                    : "login.do?action=getPaginaPrestador";
				
		}finally {
			if(connection != null) {
				connection.close();
			}
		}
		
	}
	
}
