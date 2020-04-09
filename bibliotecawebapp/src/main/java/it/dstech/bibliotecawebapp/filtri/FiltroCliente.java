package it.dstech.bibliotecawebapp.filtri;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter (filterName= "cliente", urlPatterns = "/cliente/*")
public class FiltroCliente implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
HttpServletRequest req = (HttpServletRequest) request;
HttpServletResponse resp = (HttpServletResponse) response;
HttpSession session = (HttpSession) req.getSession();
// if (session.getAttribute("utente") == null) {
//	resp.sendRedirect("/login.jsp");
// } else {
	chain.doFilter(request, response);
// }
		
		
	}

	@Override
	public void destroy() {
		
	}

}
