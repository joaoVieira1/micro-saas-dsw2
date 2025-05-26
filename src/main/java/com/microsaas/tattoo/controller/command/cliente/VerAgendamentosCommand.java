package com.microsaas.tattoo.controller.command.cliente;

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
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VerAgendamentosCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		
		List<Agenda> horariosAgendados = new ArrayList<>();
		List<Prestador> prestadores = new ArrayList<>(); 
		
		try {
			Connection connection = DatabaseConnection.getConnection();
			ClienteDao dao = new ClienteDao(connection);
			
			horariosAgendados = dao.retornarAngendamentos(usuarioLogado.getRefId());
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		System.out.print("usuario Id: " + usuarioLogado.getRefId() + "\n");
		//System.out.print("horariosAgendados: " + horariosAgendados.get(0) + "\n");

	    prestadores = (List<Prestador>)session.getAttribute("prestadores"); 
		
		request.setAttribute("horariosAgendados", horariosAgendados);
		request.setAttribute("prestadores", prestadores);
		
		return "verHorariosAgendados.jsp";

		
	}

}
