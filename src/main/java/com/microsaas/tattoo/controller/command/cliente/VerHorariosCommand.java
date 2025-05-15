package com.microsaas.tattoo.controller.command.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Agenda;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerHorariosCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Integer idInt = Integer.parseInt(id);

		List<Agenda> horarios = new ArrayList<>();
		
		try {
			Connection connection = DatabaseConnection.getConnection();
			PrestadorDao dao = new PrestadorDao(connection);
			
			horarios = dao.retornarAngendamentosDesocupados(idInt);
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		request.setAttribute("horarios", horarios);
		
		return "verHorariosTatuador.jsp";
	}

}
