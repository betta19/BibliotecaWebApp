<%@page import="it.dstech.bibliotecawebapp.modelli.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Cliente</title>

</head>
<body>

<br>
<%-- <%session.getAttribute("username"); %> --%>
<%Utente utente = (Utente)session.getAttribute("utente");%>
<h2><p class="text-xl-center">Fai una scelta, <%= utente.getUsername() %></p></h2><br><br>

<% String mess = (String) request.getAttribute("mess"); 
	if (mess != null ){
		%>
	<h4><p class="text-md-center text-danger"><%=mess%></p></h4>
		
					
	<% }
	


%><br>
<% String path = request.getContextPath(); %>
<form action="<%=path%>/cliente/opzioniCliente" method="post">
<input type="submit"class="btn btn-outline-primary btn-block" style="width:150px; height:45px;margin:auto" name= action value="Visualizza profilo">
  <br> <input type="submit"class="btn btn-outline-primary btn-block" style="width:150px; height:45px;margin:auto" name= action value="Compra libri">
 <br> <input type="submit"class="btn btn-outline-primary btn-block" style="width:150px; height:45px;margin:auto" name= action value="Affitta libri">
<br> <input type="submit"class="btn btn-outline-primary btn-block" style="width:150px; height:45px;margin:auto" name= action value="Stampa acquisti">
<br> <input type="submit"class="btn btn-outline-primary btn-block" style="width:150px; height:45px;margin:auto" name= action value="Stampa prestiti">

</form>  
 <br>   
<form action="home.jsp">
  <input type="submit"  class="btn btn-outline-secondary btn-block" style="width:150px; height:50px;margin:auto" value="Logout">
  </form>
</body>
</html>