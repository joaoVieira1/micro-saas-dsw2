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
import com.microsaas.tattoo.controller.command.login.GetCadastroClienteCommand;
import com.microsaas.tattoo.controller.command.login.GetCadastroPrestadorCommand;
import com.microsaas.tattoo.controller.command.prestador.ConfirmarAgendaCommand;
import com.microsaas.tattoo.controller.command.prestador.DesconfirmarAgendaCommand;
import com.microsaas.tattoo.controller.command.prestador.GetFormHorarioAgendamentoCommand;
import com.microsaas.tattoo.controller.command.prestador.GetFormTatuagemCommand;
import com.microsaas.tattoo.controller.command.prestador.GetHorarioCommand;
import com.microsaas.tattoo.controller.command.prestador.GetPaginaPrestadorCommand;
import com.microsaas.tattoo.controller.command.prestador.GetPortfolioCommand;
import com.microsaas.tattoo.controller.command.prestador.PostarTatuagemCommand;
import com.microsaas.tattoo.controller.command.prestador.PublicarHorarioCommand;

@WebServlet("/prestador.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 10)
public class PrestadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PrestadorServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		Command command = null;
		
		if(action.equals("postarTatuagem")) {
			command = new PostarTatuagemCommand();
		}else if(action.equals("getFormTatuagem")) {
			command = new GetFormTatuagemCommand();
		}else if(action.equals("getFormHorario")) {
			command = new GetFormHorarioAgendamentoCommand();
		}else if(action.equals("publicarHorario")) {
			command = new PublicarHorarioCommand();
		}else if(action.equals("getPortfolio")) {
			command = new GetPortfolioCommand();
		}else if(action.equals("getHorario")){
			command = new GetHorarioCommand();
		}else if(action.equals("getPaginaPrestador")) {
			command = new GetPaginaPrestadorCommand();
		}else if(action.equals("confirmarAgenda")) {
			command = new ConfirmarAgendaCommand();
		}else if(action.equals("desconfirmarAgenda")) {
			command = new DesconfirmarAgendaCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
}
