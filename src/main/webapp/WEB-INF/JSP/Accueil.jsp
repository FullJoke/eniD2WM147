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
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a href="http://www.google.fr">S'inscrire </a>
			</div>
			<div class="p-2">
				<a href="<%=request.getContextPath()%>/login"> Se Connecter</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
			</div>
			<div class="p-2">
				<a href="http://www.google.fr">Mon Profil</a>
			</div>
			<div class="p-2">
				<a href="http://www.google.fr">Vendre un article</a>
			</div>
			<div class="p-2">
				<a href="http://www.google.fr">Enchères</a>
			</div>
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