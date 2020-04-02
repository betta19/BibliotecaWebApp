package it.dstech.bibliotecawebapp.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.dstech.bibliotecawebapp.connessione.Database;

@WebServlet(name = "opzioniCliente", urlPatterns = { "/opzioniCliente" })

public class OpzioniCliente extends HttpServlet {

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
Database db;
	try {
		
		String username = req.getParameter("username");
		String azione = req.getParameter("action");
		db = new Database();
	if (azione.equalsIgnoreCase("Compra libri")) {
		req.setAttribute("listaLibri", db.stampaListaLibri());
		req.setAttribute("username", username);
	db.close();
	req.getRequestDispatcher("acquisto.jsp").forward(req, resp);
	} 
	else if (azione.equalsIgnoreCase("Affitta libri")) {
		req.setAttribute("username", username);
		db.close();
		req.getRequestDispatcher("affitto.jsp").forward(req, resp);
	}
	else if (azione.equalsIgnoreCase("Stampa acquisti")) {
		req.setAttribute("username", username);
		db.close();
		req.getRequestDispatcher("stampaAcquisti.jsp").forward(req, resp);
	}
	else if (azione.equalsIgnoreCase("Stampa libri in prestito")) {
		req.setAttribute("username", username);
		db.close();
		req.getRequestDispatcher("stampaAffitti.jsp").forward(req, resp);
	}
		
		
	} catch (ClassNotFoundException | IOException | SQLException e) {
		
		e.printStackTrace();
	}
}
}
