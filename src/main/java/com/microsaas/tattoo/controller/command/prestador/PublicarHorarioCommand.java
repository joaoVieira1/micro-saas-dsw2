package com.microsaas.tattoo.controller.command.prestador;

import java.io.IOException;
import java.sql.SQLException;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.AgendaService;
import com.microsaas.tattoo.model.service.ImagemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PublicarHorarioCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mensagem = "";
		boolean salvo = false;
		String dataHoraStr = request.getParameter("data_hora");

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null && dataHoraStr != null) {

			try {
				AgendaService agendaService = new AgendaService();
				salvo = agendaService.postarHorario(dataHoraStr, usuario.getRefId());
				mensagem = "Cadastro realizado com sucesso!";
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("mensagem", mensagem);
			request.setAttribute("salvo", salvo);

			return "homePrestador.jsp";

		}

		return "cadastrarAgenda.jsp";
	}

}
