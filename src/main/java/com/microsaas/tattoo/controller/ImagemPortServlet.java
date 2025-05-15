package com.microsaas.tattoo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


	
@WebServlet("/imagensPort.do")
public class ImagemPortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private static final String IMAGEM_DIR = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\services";
	       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String nomeArquivo = request.getParameter("nomeFoto");
			
		if(nomeArquivo == null || nomeArquivo.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome da imagem nãofornecido.");
			return;
		}
		
		File imagem = new File(IMAGEM_DIR, nomeArquivo);
			
		if (!imagem.exists() || imagem.isDirectory()){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem não encontrada.");
			return;
		}
			
		String contentType = getServletContext().getMimeType(imagem.getName());
			
		if (contentType == null){
			contentType = "application/octet-stream";
		}
			
		response.setContentType(contentType);
		response.setContentLengthLong(imagem.length());
			
		try (FileInputStream in = new FileInputStream(imagem);
				ServletOutputStream out = response.getOutputStream()) {
				
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
		}
			
	}
}

