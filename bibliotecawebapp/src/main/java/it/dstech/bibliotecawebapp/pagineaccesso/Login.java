package it.dstech.bibliotecawebapp.pagineaccesso;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.dstech.bibliotecawebapp.connessione.Database;
import it.dstech.bibliotecawebapp.emailutility.Mail;
import it.dstech.bibliotecawebapp.modelli.Utente;


@WebServlet(name = "login", urlPatterns = { "/login" }, initParams = {
		@WebInitParam(name = "username", value = "admin"), @WebInitParam(name = "password", value = "1234") })

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		String azione = req.getParameter("action");
	

		try {
			Database db = new Database();
			Utente utente = db.getCliente(username, password);
			

			if (azione.equalsIgnoreCase("Accedi")) {
				
				
				
				if (utente != null) {

					
					if (!utente.isActive()) {
						// utente esiste ma non è stato attivato
						session.setAttribute("username", username);
						req.setAttribute("message", scriviRispostaUtenteNonAttivo(utente));
						db.close();
						req.getRequestDispatcher("/login.jsp").forward(req, resp);
					}
					
					else {
					session.setAttribute("utente", utente);
					session.setAttribute("username", username);
					db.close();
					
					req.getRequestDispatcher("/paginaCliente.jsp").forward(req, resp);
				}
				}
				else if (username.equals(getInitParameter("username"))
						&& password.equals(getInitParameter("password"))) {

					session.setAttribute("username", username);
					session.setAttribute("password", password);
					db.close();
					req.getRequestDispatcher("paginaAmministratore.jsp").forward(req, resp);
				}

				else {
					req.setAttribute("mess",
							"Accesso non valido, credenziali errate o inesistenti; se non sei registrato, registrati!");
					db.close();
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}

			
				
				 /*if (username.equals(getInitParameter("username"))) {
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
					
					if (utente == null) {
						
					db.inserimentoUtente(username, password);
					
					Utente u1 = new Utente(username);	
					Mail.sendEmail(u1.getUsername(), "Conferma Mail", generaLinkValidazioneUtente(u1));
					req.setAttribute("username", username);
					req.setAttribute("mess", "La registrazione sarà confermata solo dopo aver cliccato sul link che le abbiamo inviato sulla sua mail");
					db.close();
					
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}
				}  */

			

		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private String scriviRispostaUtenteNonAttivo(Utente utente) {
		String mailUtente = utente.getUsername();

		return "L'utente " + mailUtente + " non ha ancora validato l'email";
	}
	
}
