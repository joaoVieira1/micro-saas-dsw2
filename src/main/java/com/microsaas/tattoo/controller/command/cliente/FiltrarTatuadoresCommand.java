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

public class FiltrarTatuadoresCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cidadeFiltro = request.getParameter("cidade") != null ? request.getParameter("cidade") : "";
		Connection connection = null;
		List<Prestador> prestadores = new ArrayList<>(); 
		
		int pagina = 0;
		int totalPaginas = 0;
		String paginaStr = request.getParameter("pagina");
		
		if (paginaStr != null) {
	        try {
	            pagina = Integer.parseInt(paginaStr);
	        } catch (NumberFormatException e) {
	            pagina = 0; // fallback
	        }
	    }
		
		try {
			connection = DatabaseConnection.getConnection();
			
			PrestadorDao dao = new PrestadorDao(connection);
			prestadores = dao.retornarPrestadores(cidadeFiltro, pagina);
			totalPaginas = dao.getTotalTatuadores(cidadeFiltro);
			
			request.setAttribute("pagina", pagina);
			request.setAttribute("totalPaginas", totalPaginas);
			request.setAttribute("prestadores", prestadores);
			request.setAttribute("cidadeSelecionada", cidadeFiltro);
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		return "homeCliente.jsp";
	}

}
