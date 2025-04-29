package com.microsaas.tattoo.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;

public class DatabaseConnection {

	private static final String RESOURCE = "java:/comp/env/jdbc/MicroSaas";
	
	public static Connection getConnection() throws SQLException, ServletException{
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(RESOURCE);
			return dataSource.getConnection();
		}catch(NamingException e) {
			throw new ServletException("Erro na conexão JNDI", e);
		}
	}
	
}
