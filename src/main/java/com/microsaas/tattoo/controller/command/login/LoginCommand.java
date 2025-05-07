package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.ImagemServicoDao;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.ImagemServico;
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
		
		Connection connection;
		
		
		if(email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
			request.setAttribute("mensagem", "Insira todos os campos");
			return "login.do?action=getIndex";
		}
		
		try {
			String view = loginService.loginUsuario(request, email, senha);
			
			try {
				connection = DatabaseConnection.getConnection();
				UsuarioDao dao = new UsuarioDao(connection);
				ImagemServicoDao imagemDao = new ImagemServicoDao(connection);
				
				Usuario usuario = dao.retornarUsuarioPeloEmail(email);
				
				List<ImagemServico> imagens = (ArrayList<ImagemServico>) imagemDao.listarImagensPorPrestador(usuario.getRefId());
		        
				request.setAttribute("imagens", imagens);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			
			
			return view;
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("mensagem", "Erro ao efetuar login");
			return "login.do?action=getIndex";
		}
	}

}
