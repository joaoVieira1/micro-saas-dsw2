package com.microsaas.tattoo.controller.command.login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.microsaas.tattoo.controller.command.Command;
import com.microsaas.tattoo.model.dao.UsuarioDao;
import com.microsaas.tattoo.model.dao.connection.DatabaseConnection;
import com.microsaas.tattoo.model.entity.Prestador;
import com.microsaas.tattoo.model.entity.TipoUsuario;
import com.microsaas.tattoo.model.entity.Usuario;
import com.microsaas.tattoo.model.service.CadastroService;
import com.microsaas.tattoo.model.utils.CriptografiaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class CadastroPrestadorCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dados usuario
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String confirmarSenha = request.getParameter("confirmarSenha");
		
		//dados prestador
		String nome_fantasia = request.getParameter("nome_fantasia");
		String nome_completo = request.getParameter("nome_completo");
		Part foto = request.getPart("foto");
		String contentType = foto.getContentType();
		String endereco = request.getParameter("endereco");
		String cidade = request.getParameter("cidade") != null ? request.getParameter("cidade") : "";
		String descricao = request.getParameter("descricao");
		
		//auxiliar
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");
		final String URL_UPLOADS = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\uploads";
		String mensagem = "";
		Boolean salvo = false;
		CadastroService cadastroService = new CadastroService();
	
		
		if(!tiposPermitidos.contains(contentType)) {
			mensagem = "Selecione uma foto do tipo png ou jpeg";
			request.setAttribute("mensagem", mensagem);
			request.setAttribute("salvo", false);
			
			return "cadastroPrestador.jsp";
		}
		
		String nomeArquivo = UUID.randomUUID().toString() + "_" + Paths.get(foto.getSubmittedFileName()).getFileName().toString();
		String uploadPath = URL_UPLOADS;
		Files.createDirectories(Paths.get(uploadPath));	
		
		if(senha.equals(confirmarSenha)) {
			try {
				Prestador novoPrestador = new Prestador(nome_fantasia, nome_completo, nomeArquivo, endereco, descricao, cidade);
				String senhaCriptografada = CriptografiaUtils.criptografarSenha(senha);
				Usuario novoUsuario = new Usuario(email, senhaCriptografada, TipoUsuario.PRESTADOR, 0);
				
				salvo = cadastroService.cadastrarPrestador(novoUsuario, novoPrestador);
				
				foto.write(uploadPath + File.separator + nomeArquivo);	
				mensagem = "Cadastro realizado com sucesso!";
				
			}catch(SQLException e) {
				mensagem = cadastroService.tratarErroCadastroPrestador(e);
			}
		}else {
			mensagem = "Certifique-se de confirmar a senha corretamente";
		}
		
		request.setAttribute("mensagem", mensagem);
		request.setAttribute("salvo", salvo);
		
		return "cadastroPrestador.jsp";
	}

}
