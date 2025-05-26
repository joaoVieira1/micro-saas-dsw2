package com.microsaas.tattoo.controller.command.cliente;

import java.io.IOException;
import java.sql.SQLException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.AgendaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DesocuparHorarioCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		int horarioId = Integer.parseInt(request.getParameter("id"));
		boolean salvo = false;
		String mensagem = "";
		
		try {			
			
			AgendaService agendaService = new AgendaService();
			salvo = agendaService.desocuparHorario(horarioId);
			mensagem = "Horario Cancelado com sucesso!";
			request.setAttribute("mensagem", mensagem);
//			System.out.print("salvo: " + salvo + "\n");
//			System.out.print("horarioId: " + request.getParameter("id") + "\n");
//			System.out.print("clienteId: " + usuarioLogado.getRefId() + "\n");
			
		} catch (SQLException | ServletException e) {
			e.printStackTrace();
		}
		
		
		return "homeCliente.jsp";
	}

}
