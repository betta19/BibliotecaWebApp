package it.dstech.bibliotecawebapp.amministratore;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.dstech.bibliotecawebapp.connessione.Database;

@WebServlet(name = "login", urlPatterns = {"/login"}, initParams = {
		@WebInitParam(name = "username", value = "admin"), @WebInitParam(name = "password", value = "1234") })

public class Login extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String azione = req.getParameter("action");
		Database db;
		
		if (azione.equalsIgnoreCase("Accedi")) {
			try {
		
				db = new Database();
			
			if (db.checkAccessoUtente(username, password)) {
				
					req.setAttribute("username", username);

			req.getRequestDispatcher("paginaCliente.jsp").forward(req, resp);
		}
		else if (username.equals(getInitParameter("username")) && password.equals(getInitParameter("password"))) {
			
			req.setAttribute("username", username);
			req.setAttribute("password", password);
		
		req.getRequestDispatcher("paginaAmministratore.jsp").forward(req, resp);
		}
		
		
			else {
			req.setAttribute("mess", "Accesso non valido, credenziali errate o inesistenti; se non sei registrato, registrati!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}		
			db.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	
		}
		
		if (azione.equalsIgnoreCase("Registrati")) {
			try {
				db = new Database();
				
				if (db.checkAccessoUtente(username, password)) {
					req.setAttribute("mess", "Credenziali gi� presenti; prova con un altra mail, se sei registrato per entrare nel sito cliccare su Accedi");
					req.setAttribute("username", username);

			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
				
				else {
					
					db.inserimentoUtente(username, password);
					req.setAttribute("username", username);
					
					req.getRequestDispatcher("paginaCliente.jsp").forward(req, resp);
				}
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	}
	
	
	}
}


