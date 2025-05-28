package com.microsaas.tattoo.controller.command.prestador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.HistoricoAgendamentoDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.HistoricoAgendamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetLogHorarioCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int prestadorId = Integer.parseInt(request.getParameter("idHorario"));
		List<HistoricoAgendamento> listaHistorico = new ArrayList<>();
		
		Connection connection;
		try {
			connection = DatabaseConnection.getConnection();
			HistoricoAgendamentoDao historicoDao = new HistoricoAgendamentoDao(connection);
			listaHistorico = historicoDao.buscarPorAgendamento(prestadorId);
		} catch (SQLException | ServletException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("listaHistorico", listaHistorico);
	

		return "historico.jsp";
	}

}
