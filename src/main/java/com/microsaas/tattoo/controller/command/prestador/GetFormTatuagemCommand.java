package com.microsaas.tattoo.controller.command.prestador;

import java.io.IOException;

import com.microsaas.tattoo.controller.command.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetFormTatuagemCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "formPostarTatuagem.jsp";
	}

}
