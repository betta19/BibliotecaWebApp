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
<title>Amministratore</title>
</head>
<body>
	
	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<h1>
					<p class="text-md-center">Gestisci Libreria</p>
				</h1>
			</div>
		</div>
	</div>
				<%
					String messaggio = (String) request.getAttribute("messaggio");
					if (messaggio != null) {
				%>

				<p class="text-md-center text-danger"><%=messaggio%></p>

				<%
					}
				%>
				<br> 
				<% String path = request.getContextPath(); %>
				<form action="<%=path%>/admin/gestioneBiblioteca" method="post">
					<input type="submit" class="btn btn-outline-primary btn-block"
						style="width: 300px; height: 60px; margin: auto" name="azione"
						value="Aggiungi un libro"> <br> <input type="submit"
						class="btn btn-outline-primary btn-block"
						style="width: 300px; height: 60px; margin: auto" name="azione"
						value="Riordina libro"> <br> <input type="submit"
						class="btn btn-outline-primary btn-block"
						style="width: 300px; height: 60px; margin: auto" name="azione"
						value="Stampa lista libri"> <br> <input type="submit"
						class="btn btn-outline-primary btn-block"
						style="width: 300px; height: 60px; margin: auto" name="azione"
						value="Stampa lista libri venduti"> <br> <input
						type="submit" class="btn btn-outline-primary btn-block"
						style="width: 300px; height: 60px; margin: auto" name="azione"
						value="Stampa lista libri prestati">
							<br> <br>
						<input type="submit"  class="btn btn-outline-secondary btn-block" style="width:150px; height:50px;margin:auto" name= "azione" value="Logout">
				</form>
			

</body>
</html>