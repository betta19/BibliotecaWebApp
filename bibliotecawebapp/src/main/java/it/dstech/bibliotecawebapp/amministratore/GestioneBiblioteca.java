package it.dstech.bibliotecawebapp.amministratore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.bibliotecawebapp.connessione.Database;


@WebServlet(name = "gestione", urlPatterns = "/gestioneBiblioteca")
public class GestioneBiblioteca extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("messaggio", "hai tentato di accedere manualmente alla gestione biblioteca");
		req.getRequestDispatcher("paginaAmministratore.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String azione = req.getParameter("azione");
		if (azione.equalsIgnoreCase("Aggiungi un libro")) {
			try {
				Database db = new Database();
				req.setAttribute("listaLibri", db.stampaListaLibri());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("aggiungi.jsp").forward(req, resp);
		} else if (azione.equalsIgnoreCase("Riordina libro")) {
			try {
				Database db = new Database();
				req.setAttribute("listaLibri", db.stampaListaLibri());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("riordina.jsp").forward(req, resp);
		} else if (azione.equalsIgnoreCase("Invia mail")) {
			Database db;
			try {
				db = new Database();
				req.setAttribute("listaLibriPrestati", db.ordinaData());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			req.getRequestDispatcher("invia_mail.jsp").forward(req, resp);
		} else if (azione.equalsIgnoreCase("Stampa lista libri")) {
			try {
				Database db = new Database();
				req.setAttribute("listaLibri", db.stampaListaLibri());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("lista_libri.jsp").forward(req, resp);
		} else if (azione.equalsIgnoreCase("Stampa lista libri venduti")) {
			try {
				Database db = new Database();
				req.setAttribute("listaLibriVenduti", db.stampaLibriVenduti());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("lista_libri_venduti.jsp").forward(req, resp);
		} else if (azione.equalsIgnoreCase("Stampa lista libri prestati")) {
			try {
				Database db = new Database();
				req.setAttribute("listaLibriPrestati", db.stampaLibriPrestati());
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("lista_libri_prestati.jsp").forward(req, resp);
		}
	}
}
