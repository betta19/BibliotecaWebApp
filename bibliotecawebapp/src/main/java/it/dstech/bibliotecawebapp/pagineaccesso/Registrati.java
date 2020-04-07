package it.dstech.bibliotecawebapp.pagineaccesso;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.dstech.bibliotecawebapp.connessione.Database;
import it.dstech.bibliotecawebapp.emailutility.Mail;
import it.dstech.bibliotecawebapp.modelli.Utente;

@WebServlet(name = "registrazione", urlPatterns = { "/registrazione" })
@MultipartConfig
public class Registrati extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Part image = req.getPart("image");
		Database db;
		try {
			db = new Database();

			if (username.equals(getInitParameter("username"))) {
				req.setAttribute("mess", "Se sei l'amministratore, effettua l'accesso");

				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else if (db.checkRegistraUtente(username)) {
				req.setAttribute("mess",
						"Credenziali già presenti; provi con un altra mail, se è gia registrato per entrare nel sito cliccare su Accedi");
				req.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}

			else {

					db.salvaUtente(username, password, image.getInputStream());

					Utente u1 = new Utente(username);
					Mail.sendEmail(u1.getUsername(), "Conferma Mail", generaLinkValidazioneUtente(u1));
					req.setAttribute("username", username);
					req.setAttribute("mess",
							"La registrazione sarà confermata solo dopo aver cliccato sul link che le abbiamo inviato sulla sua mail");
					db.close();

					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
		} 
		catch (ClassNotFoundException | SQLException | MessagingException e) {
			e.printStackTrace();
		}
	}

	private String generaLinkValidazioneUtente(Utente utente) {
		String validationPath = "http://localhost:8080/bibliotecawebapp/validazione?utente=";
		return "Per attivare la mail clicca su questo link: " + validationPath + utente.getUsername();
	}
}
