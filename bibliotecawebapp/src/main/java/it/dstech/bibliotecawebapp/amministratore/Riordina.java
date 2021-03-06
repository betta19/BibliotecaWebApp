package it.dstech.bibliotecawebapp.amministratore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.dstech.bibliotecawebapp.connessione.Database;

@WebServlet(name = "riordina", urlPatterns = "/admin/riordina")
public class Riordina extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

			String titolo = req.getParameter("titolo");
			int quantita = Integer.parseInt(req.getParameter("quantita"));
			try {
				Database db = new Database();
				int quantitaVecchia = db.checkQuantita(titolo);
				int disponibiltaVecchia = db.checkDisponibilita(titolo);
				db.updateLibri(titolo, quantita, quantitaVecchia, disponibiltaVecchia);
				req.setAttribute("listaLibri", db.stampaListaLibri());
				req.setAttribute("messaggio", "quantitą e disponibilitą aggiornate");
				db.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			req.getRequestDispatcher("/riordina.jsp").forward(req, resp);
		} 
}
