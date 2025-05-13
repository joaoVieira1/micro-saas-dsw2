package com.microsaas.tattoo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.controller.command.login.CadastroClienteCommand;
import com.microsaas.tattoo.controller.command.login.CadastroPrestadorCommand;
import com.microsaas.tattoo.controller.command.login.GetCadastroClienteCommand;
import com.microsaas.tattoo.controller.command.login.GetCadastroPrestadorCommand;
import com.microsaas.tattoo.controller.command.login.GetIndexCommand;
import com.microsaas.tattoo.controller.command.login.LoginCommand;
import com.microsaas.tattoo.controller.command.login.LogoutCommand;
import com.microsaas.tattoo.controller.command.prestador.GetPaginaPrestadorCommand;


@WebServlet("/login.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 10)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		Command command = null;
		
		if(action.equals("getCadastroCliente")) {
			command = new GetCadastroClienteCommand();
		}else if(action.equals("getCadastroPrestador")) {
			command = new GetCadastroPrestadorCommand();
		}else if(action.equals("cadastroCliente")) {
			command = new CadastroClienteCommand();
		}else if(action.equals("cadastroPrestador")) {
			command = new CadastroPrestadorCommand();
		}else if(action.equals("login")) {
			command = new LoginCommand();
		}else if(action.equals("getPaginaPrestador")) {
			command = new GetPaginaPrestadorCommand();
		}else if(action.equals("getIndex")) {
			command = new GetIndexCommand();
		}else if(action.equals("logout")) {
			command = new LogoutCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
