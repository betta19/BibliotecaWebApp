<%@page import="it.dstech.bibliotecawebapp.modelli.Libro"%>
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
		List<Libro> listaLibri = (List<Libro>) request.getAttribute("listaLibri");
	%>
	<div class="container">
	<br>

	<br> 
		<div class="row">
	
			<div class="col-xl align-self-up ">
				<h3>Libri presenti in biblioteca</h3>
				<table class="table table-striped">

					<tr>
						<th>Titolo</th>
						<th>Autore</th>
						<th>Prezzo</th>
						<th>Disponibilitą</th>
						<th>Quantitą</th>
					</tr>
					<%
						for (Libro l : listaLibri) {
					%>
					<tr>
						<td><%=l.getTitolo()%></td>
						<td><%=l.getAutore()%></td>
						<td><%=l.getPrezzo()%></td>
						<td><%=l.getDisponibilita()%></td>
						<td><%=l.getQuantita()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			
			<div class="col-xl align-self-up ">
			 <h3>Inserisci nuovo libro</h3> <br> 
				<form action="aggiungi" method="post">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Titolo</span>
						</div>
						<input type="text" id="titolo" name="titolo"><br>
						<br>
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Autore</span>
						</div>
						<input type="text" id="autore" name="autore"><br>
						<br>
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Prezzo</span>
						</div>
						<input type="text" id="prezzo" name="prezzo"><br>
						<br>
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Disponibilitą</span>
						</div>
						<input type="number" id="disponibilita" name="disponibilita"><br>
						<br>
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Quantitą</span>
						</div>
						<input type="number" id="quantita" name="quantita"><br>
						<br>
					</div>
					<input type="submit" class="btn btn-outline-success"
						style="width: 120px; height: 45px;" name= "action" value="Aggiungi">
				</form>
				<br>
				<br>
				<form action="indietro" method="post">
					<input type="submit" class="btn btn-outline-secondary" 
						value="Torna Indietro">
				</form>
			</div>
		</div>
	</div>

</body>
</html>