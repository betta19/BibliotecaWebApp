
<%@page import="it.dstech.bibliotecawebapp.modelli.LibroVenduto"%>

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
		List<LibroVenduto> listaAcquisti = (List<LibroVenduto>) request.getAttribute("listaLibriVenduti");
	%>





	<table class="table table-striped">

		<tr>
			
			<th>Titolo</th>
			<th>Quantità</th>
			<th>Username</th>
			<th>Scontrino</th>
		</tr>
		<%
			for (LibroVenduto a : listaAcquisti) {
		%>

		<tr>
			
			<td><%=a.getTitolo()%></td>
			<td><%=a.getQuantita()%></td>
			<td><%=a.getUsername()%></td>
			<td><%=a.getIdScontrino()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<form action="indietro" method="post">
		<input type="submit" class="btn btn-outline-secondary btn-block"
			style="width: 150px; height: 50px; margin: auto"
			value="Torna Indietro">

	</form>


</body>
</html>