<%@page import="it.dstech.bibliotecawebapp.modelli.LibroInPrestito"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		List<LibroInPrestito> listaPrestiti = (List<LibroInPrestito>) request.getAttribute("listaLibriPrestati");
	%>


<%
		String messaggio = (String) request.getAttribute("messaggio");
		if (messaggio != null) {
	%>

	<p class="text-md-center text-danger"><%=messaggio%></p>

	<%
		}
	%>


	<table class="table table-striped">

		<tr>
			<th>Id Prestito</th>
			<th>Titolo</th>
			<th>Data Affitto</th>
			<th>Username</th>
			<th>Sollecitazione</th>

		</tr>
		<%
			for (LibroInPrestito p : listaPrestiti) {
		%>

		<tr>
			<td><%=p.getIdTessera()%></td>
			<td><%=p.getTitolo()%></td>
			<td><%=p.getDataAffitto()%></td>
			<td><%=p.getUsername()%></td>
			
				<form action="auto" method="post">
					<input type="submit" name="azione" value="Sollecita"> <input
						type="hidden" id="Utente" name="Utente" value=<%=p.getUsername()%>>
						<input type="hidden" id="data" name="data" value=<%=p.getDataAffitto()%>>
							<input type="hidden" id="titolo" name="titolo" value=<%=p.getTitolo()%>>
				</form>
			</td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<form action="paginaAmministratore.jsp">
		<input type="submit" class="btn btn-outline-secondary btn-block"
			style="width: 150px; height: 50px; margin: auto"
			value="Torna Indietro">

	</form>
</body>
</html>