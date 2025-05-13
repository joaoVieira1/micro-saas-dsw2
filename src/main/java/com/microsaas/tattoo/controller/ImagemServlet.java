package com.microsaas.tattoo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.microsaas.tattoo.model.entity.Prestador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/imagem")
public class ImagemServlet extends HttpServlet {

    private static final String CAMINHO_UPLOADS = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\uploads";
    private static final String URL_SERVICES = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\services";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo"); // "perfil" ou "servico"
        String nomeImagem = request.getParameter("nome");

        if (tipo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'tipo' obrigatório (perfil ou servico).");
            return;
        }

        File arquivoImagem;

        if ("perfil".equalsIgnoreCase(tipo)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("prestadorLogado") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado.");
                return;
            }

            Prestador prestador = (Prestador) session.getAttribute("prestadorLogado");

            nomeImagem = prestador.getFoto(); // nome do arquivo salvo
            if (nomeImagem == null || nomeImagem.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem de perfil não encontrada.");
                return;
            }

            arquivoImagem = new File(CAMINHO_UPLOADS, nomeImagem);

        } else if ("servico".equalsIgnoreCase(tipo)) {
            if (nomeImagem == null || nomeImagem.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'nome' obrigatório para imagem de serviço.");
                return;
            }

            arquivoImagem = new File(URL_SERVICES, nomeImagem);

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo inválido. Use 'perfil' ou 'servico'.");
            return;
        }

        if (!arquivoImagem.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem não encontrada.");
            return;
        }

        String contentType = Files.probeContentType(arquivoImagem.toPath());
        response.setContentType(contentType != null ? contentType : "image/jpeg");
        response.setContentLength((int) arquivoImagem.length());
        
        try (FileInputStream fis = new FileInputStream(arquivoImagem);
             OutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
