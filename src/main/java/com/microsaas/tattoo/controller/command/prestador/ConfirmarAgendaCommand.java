package com.microsaas.tattoo.controller.command.prestador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.ClienteDao;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.AgendaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ConfirmarAgendaCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int horarioId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

		boolean salvo = false;
		String mensagem = "";
		List<Agenda> horariosAgendados = new ArrayList<>();
		System.out.print("horarioId[Confirmar]" + horarioId + "/n");
		
		try {
			AgendaService agendaService = new AgendaService();
			salvo = agendaService.confirmarAgenda(horarioId, true);
			Connection connection = DatabaseConnection.getConnection();
			PrestadorDao dao = new PrestadorDao(connection);
			horariosAgendados = dao.retornarAngendamentos(usuarioLogado.getRefId());
			mensagem = "Horario Confirmado com sucesso!";
			request.setAttribute("mensagem", mensagem);
		}catch (SQLException | ServletException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("horariosAgendados", horariosAgendados);

		return "homePrestador.jsp";
	}

}
