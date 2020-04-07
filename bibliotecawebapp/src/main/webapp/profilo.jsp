<%@page import="it.dstech.bibliotecawebapp.modelli.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<head>

<meta charset="ISO-8859-1">
<title>Profilo</title>
</head>
<body>
<br>

<%Utente utente = (Utente) request.getAttribute("utente"); %>
<h2><p class="text-xl-center">Ciao, <%=utente.getUsername()%></p></h2><br><br>
<div class="container">
<img alt="image" class="rounded mx-auto d-block img-circle" width="404" height="336" src="data:image/jpg;base64, <%= utente.getImage() %>"> <br>
</div>
<% String mess = (String) request.getAttribute("mess"); 
	if (mess != null ){
		%>
	<h4><p class="text-md-center text-danger"><%=mess%></p></h4>
		
					
	<% } %>
	<form action = "image" method="post">

	</form> <br> <br>
	<form action="tornaIndietro" method="post">
  <input type="submit" class="btn btn-outline-secondary  rounded mx-auto d-block" value="Torna Indietro">
  <input type="hidden" id="username" name="username" value=<%=utente.getUsername()%>>  
  
</form>
</body>
</html>