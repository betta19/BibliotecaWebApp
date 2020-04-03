package it.dstech.bibliotecawebapp.pagineaccesso;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.bibliotecawebapp.connessione.Database;


@WebServlet(urlPatterns = {"/validazione"})
public class ValidazioneAccount extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("utente");
		Database db;
		try {
			db = new Database();
			db.validaUtente(username);
			
			req.setAttribute("mess", "L'utente "+ username +" è stato validato");
			db.close();
			req.getRequestDispatcher("/login.jsp").forward(req, resp);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
