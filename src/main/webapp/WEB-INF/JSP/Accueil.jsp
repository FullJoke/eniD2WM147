<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enchère</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
<body>

	<header>
		<%@ include file="Entete.html"%>

		<%
		String idUtilisateur = (String) session.getAttribute("idUtilisateur");
		%>

		<%
		if (idUtilisateur == null) {
		%>
		<div class="container">
			<div class="row col-offset 5">
				<a href="http://www.google.fr">S'inscrire </a> / <a
					href="<%=request.getContextPath()%>/login"> Se Connecter</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="container">
			<a href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
		</div>
		<%
		}
		%>



	</header>

	<div class="container">
		<div class="row justify-content-lg-right">
			<h2>Liste des enchères</h2>
		</div>
	</div>

</body>
</html>