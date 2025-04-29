package com.microsaas.tattoo.controller.command.login;

import java.io.IOException;

import com.microsaas.tattoo.controller.command.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetPaginaClienteCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "homeCliente.jsp";
	}

}
