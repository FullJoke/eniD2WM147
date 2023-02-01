<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
</head>
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
				<a id="topMenu" href="<%=request.getContextPath()%>/inscription">S'inscrire
				</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se
					Connecter</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
			</div>
			.
			<div class="p-2">
				<p id="topMenuUnusable">Mon
					Profil</p>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="http://www.google.fr">Vendre un article</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/accueil">Enchères</a>
			</div>
		</div>
		<%
		}
		%>
	</header>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Profil</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2 id="profilTitle">Profil</h2>
		</div>

		<div class="row justify-content-md-center">
			<div>
				<%
				Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
				%>

				<label id="profilPseudoLabel">Pseudo : </label> <span><%=user.getPseudo()%></span>
				<br> <label id="profilNomLabel">Nom : </label> <span><%=user.getNom()%></span>
				<br> <label id="profilPrenomLabel">Prenom : </label> <span><%=user.getPrenom()%></span>
				<br> <label id="profilEmailLabel">Email : </label> <span><%=user.getEmail()%></span>
				<br> <label id="profilTelephoneLabel">Telephone : </label> <span><%=user.getTelephone()%></span>
				<br> <label id="profilRueLabel">Rue : </label> <span><%=user.getRue()%></span>
				<br> <label id="profilCodePostalLabel">Code Postal : </label> <span><%=user.getCodePostal()%></span>
				<br> <label id="profilVilleLabel">Ville : </label> <span><%=user.getVille()%></span>
				<br>
				<!--<input class="btn btn-primary" id="profilModifButton" type="submit" value="Modifier">  -->
			</div>

		</div>



	</div>




</body>
</html>