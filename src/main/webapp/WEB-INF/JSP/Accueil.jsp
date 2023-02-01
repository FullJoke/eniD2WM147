<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
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
		Utilisateur Utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
		%>

		<%
		if (Utilisateur == null) {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/inscription">S'inscrire </a>
			</div>.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se Connecter</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
			</div>.
			<div class="p-2">
				<a id="topMenu" href="http://www.google.fr">Mon Profil</a>
			</div>.
			<div class="p-2">
				<a id="topMenu" href="http://www.google.fr">Vendre un article</a>
			</div>.
			<div class="p-2">
				<a id="topMenu" href="http://www.google.fr">Enchères</a>
			</div>
		</div>
		<%
		}
		%>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>


	<div class="container">
		<h2>Liste des enchères</h2>
	</div>

</body>
</html>