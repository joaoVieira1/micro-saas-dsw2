package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.ImagemServicoDao;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.ImagemServico;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.ImagemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class PostarTatuagemCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mensagem = "";
		boolean salvo = false;

		Part foto = request.getPart("fotoTatuagem");
		String descricao = request.getParameter("descricao");
		
		if (!descricao.isEmpty() && foto != null) {
		    String contentType = foto.getContentType();
		    List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");
		    final String URL_SERVICES = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\services";

		    if (!tiposPermitidos.contains(contentType)) {
		        mensagem = "Selecione uma foto do tipo png ou jpeg";
		        request.setAttribute("mensagem", mensagem);
		        request.setAttribute("salvo", false);
		        return "formPostarTatuagem.jsp";
		    }

		    String nomeArquivo = UUID.randomUUID().toString() + "_" + Paths.get(foto.getSubmittedFileName()).getFileName().toString();
		    String uploadPath = URL_SERVICES;
		    Files.createDirectories(Paths.get(uploadPath));

		    foto.write(uploadPath + java.io.File.separator + nomeArquivo);

		    ImagemServico tatoo = new ImagemServico(nomeArquivo, descricao);
		    HttpSession session = request.getSession();
		    Usuario usuario = (Usuario) session.getAttribute("usuario");

		    try {
		        ImagemService imagemService = new ImagemService();
		        salvo = imagemService.postarImagem(tatoo, usuario.getRefId());
		        mensagem = "Cadastro realizado com sucesso!";
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    try {
				Connection connection = DatabaseConnection.getConnection();
				UsuarioDao dao = new UsuarioDao(connection);
				ImagemServicoDao imagemDao = new ImagemServicoDao(connection);

				List<ImagemServico> imagens = (ArrayList<ImagemServico>) imagemDao.listarImagensPorPrestador(usuario.getRefId());
		        session.setAttribute("imagens", imagens);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		    
		    request.setAttribute("mensagem", mensagem);
		    request.setAttribute("salvo", salvo);

		    return "homePrestador.jsp";
		}

		
		
		return "formPostarTatuagem.jsp";

	}

}
