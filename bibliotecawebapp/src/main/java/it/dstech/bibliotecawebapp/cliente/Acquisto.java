package it.dstech.bibliotecawebapp.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.bibliotecawebapp.connessione.Database;

@WebServlet (name = "acquisto", urlPatterns = { "/acquisto" })
public class Acquisto extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String username = req.getParameter("username");
	int idScontrino = Integer.parseInt(req.getParameter("idScontrino"));
	String azione = req.getParameter("azione");
	Database db;
	
	if (azione.equalsIgnoreCase("Aggiungi al carrello")) {

		String titolo = req.getParameter("titolo");
		int quantita = Integer.parseInt(req.getParameter("quantita"));
		
		try {
	
			db = new Database();
			if (db.controlloQuantitaLibri(titolo, quantita, db.stampaListaLibri())) {
				db.inserimentoTabellaAcquisto(titolo, quantita, username, idScontrino);
				db.updateQuantitaLibri(titolo, quantita);
			

				req.setAttribute("idScontrino", idScontrino);
			req.setAttribute("username", username);
			req.setAttribute("listaLibri", db.stampaListaLibri());
			req.setAttribute("mess", "Libro aggiunto con successo");
			} else {
				req.setAttribute("idScontrino", idScontrino);
				req.setAttribute("username", username);
				req.setAttribute("listaLibri", db.stampaListaLibri());
				req.setAttribute("mess", "Quantit� libri non disponibile");
			}
			db.close();
			req.getRequestDispatcher("acquisto.jsp").forward(req, resp);
			
		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
	} else if (azione.equalsIgnoreCase("Paga")) {
		try {
			db = new Database();
			double spesa = db.getPrezzo(idScontrino);
			db.totaleScontrino (idScontrino, spesa);
			db.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		} req.setAttribute("username", username);
		req.setAttribute("idScontrino", idScontrino);
		req.getRequestDispatcher("paginaCliente.jsp").forward(req, resp);
	} 
}
}