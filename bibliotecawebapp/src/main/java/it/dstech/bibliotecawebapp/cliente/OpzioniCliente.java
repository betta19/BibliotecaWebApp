package it.dstech.bibliotecawebapp.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import it.dstech.bibliotecawebapp.connessione.Database;
import it.dstech.bibliotecawebapp.modelli.Utente;

@WebServlet(urlPatterns = { "/cliente/opzioniCliente" })
@MultipartConfig
public class OpzioniCliente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Database db;
		try {

			//String username = req.getParameter("username");
			String azione = req.getParameter("action");
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			db = new Database();
			if (azione.equalsIgnoreCase("Compra libri")) {
				req.setAttribute("listaLibri", db.stampaListaLibri());
				//req.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Affitta libri")) {
				//req.setAttribute("username", username);
				req.setAttribute("listaLibri", db.stampaListaLibri());
				db.close();
				req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa acquisti")) {
				//req.setAttribute("username", username);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				db.close();
				req.getRequestDispatcher("/stampaAcquisti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa prestiti")) {
				//req.setAttribute("username", username);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				db.close();
				req.getRequestDispatcher("/stampaAffitti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Dettagli")) {
				//req.setAttribute("username", username);
				int idScontrino = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idScontrino);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				req.setAttribute("listaProdottiDelloScontrino", db.stampaProdottiScontrino(idScontrino));
				db.close();
				req.getRequestDispatcher("/listaProdottiDelloScontrino.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Info")) {
				//req.setAttribute("username", username);
				int idTessera = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idTessera);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				req.setAttribute("listaLibriTessera", db.stampaLibriInPrestito(idTessera));
				db.close();
				req.getRequestDispatcher("/listaLibriTessera.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Visualizza profilo")) {
				session.setAttribute("utenteImmagine", db.prendiImmagine(username));
				db.close();
				req.getRequestDispatcher("/profilo.jsp").forward(req, resp);
			}

		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
	}
}
