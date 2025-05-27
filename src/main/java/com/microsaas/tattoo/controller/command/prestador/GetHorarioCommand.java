package com.microsaas.tattoo.controller.command.prestador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.AgendaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetHorarioCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AgendaService service = new AgendaService();
		
		List<Agenda> agendamentos = new ArrayList<>();
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		try {
			agendamentos = service.retornarAgendamentos(usuario.getRefId());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("agendamentos", agendamentos);
		session.setAttribute("agendamentos", agendamentos);
		
		return "horariosPrestador.jsp";
	}

}
