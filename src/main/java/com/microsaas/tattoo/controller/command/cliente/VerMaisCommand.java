package com.microsaas.tattoo.controller.command.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.AgendaDao;
import com.microsaas.tattoo.model.dao.ImagemServicoDao;
import com.microsaas.tattoo.model.dao.PrestadorDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Agenda;
import com.microsaas.tattoo.model.entity.ImagemServico;
import com.microsaas.tattoo.model.entity.Prestador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerMaisCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		int idTatuador = Integer.parseInt(id);
		Prestador prestador = null;
		List<ImagemServico> imagens = new ArrayList<>();
		List<Agenda> horarios = new ArrayList<>();
		Connection connection = null;
		
		try {
			connection = DatabaseConnection.getConnection();
			ImagemServicoDao daoImagem = new ImagemServicoDao(connection);
			PrestadorDao daoPrestador = new PrestadorDao(connection);
			
			imagens = daoImagem.listarImagensPorPrestador(idTatuador);
			horarios = daoPrestador.retornarAngendamentosDesocupados(idTatuador);
			prestador = daoPrestador.retornarPrestadorPeloIdDoUsuarioLogado(idTatuador);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("imagens", imagens);
		request.setAttribute("horarios", horarios);
		request.setAttribute("prestador", prestador);
		
		return "verMais.jsp";
	}

}
