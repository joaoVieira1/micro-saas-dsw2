package com.microsaas.tattoo.controller.command.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Prestador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetHomeClienteCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Prestador> prestadores = new ArrayList<>();
		Connection connection = null;
		
		try {
			connection = DatabaseConnection.getConnection();
			PrestadorDao dao = new PrestadorDao(connection);
			
			prestadores = dao.retornarPrestadores("");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("prestadores", prestadores);
		
		return "homeCliente.jsp";
	}

}

