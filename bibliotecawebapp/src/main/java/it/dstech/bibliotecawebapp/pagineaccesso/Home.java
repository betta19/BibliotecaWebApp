package it.dstech.bibliotecawebapp.pagineaccesso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = { "/home" })
public class Home extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String scegli = req.getParameter("scegli");

		if (scegli == null) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else if (scegli.equalsIgnoreCase("Registrati")) {
			req.getRequestDispatcher("registrazione.jsp").forward(req, resp);
		} else if (scegli.equalsIgnoreCase("Login")) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
