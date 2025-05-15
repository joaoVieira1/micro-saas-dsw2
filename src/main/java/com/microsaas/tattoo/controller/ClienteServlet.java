package com.microsaas.tattoo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.controller.command.cliente.FiltrarTatuadoresCommand;
import com.microsaas.tattoo.controller.command.cliente.VerHorariosCommand;
import com.microsaas.tattoo.controller.command.cliente.VerMaisCommand;
import com.microsaas.tattoo.controller.command.prestador.GetFormHorarioAgendamentoCommand;
import com.microsaas.tattoo.controller.command.prestador.GetFormTatuagemCommand;
import com.microsaas.tattoo.controller.command.prestador.GetHorarioCommand;
import com.microsaas.tattoo.controller.command.prestador.GetPortfolioCommand;
import com.microsaas.tattoo.controller.command.prestador.PostarTatuagemCommand;
import com.microsaas.tattoo.controller.command.prestador.PublicarHorarioCommand;


@WebServlet("/cliente.do")
public class ClienteServlet extends HttpServlet {
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
		
		if(action.equals("filtrarTatuadores")) {
			command = new FiltrarTatuadoresCommand();
		}else if(action.equals("verMais")) {
			command = new VerMaisCommand();
		}else if(action.equals("verHorarios")) {
			command = new VerHorariosCommand();
		}
		
		String view = command.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
}
