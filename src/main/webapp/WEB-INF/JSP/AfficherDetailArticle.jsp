<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage du détail de l'article</title>

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
				<a id="topMenu" href="<%=request.getContextPath()%>/Profil">Mon
					Profil</a>
			</div>
			.
			<div class="p-2">
				<p id="topMenuUnusable">Vendre un article</p>
<%-- 				<a id="topMenu"
					href="<%=request.getContextPath()%>/AfficherDetailArticle">Vendre
					un article</a> --%>
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
			<li class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>

	<h1 style="text-align: center;">Détail Vente</h1>

	<h4 id="Pcgamer">Pc gamer pour travailler</h4>
	<br>

	<label id="descripAff">Description : </label>
	<textarea style="vertical-align: top;"></textarea>
	<br>

	<label id="cateAff">Categorie : </label>

	<br>

	<label id="bestOfferAff">Meilleure offre : </label>

	<br>

	<label id="misePrixAff">Mise à prix : </label>

	<br>

	<label id="finEncAff">Fin de l'enchère: </label>

	<br>

	<label id="retraitAff">Retrait: </label>

	<br>

	<label id="vendeurAff">Vendeur : </label>

	<br>
	<label id="vendeurAff">Ma proposition: </label>
	<input type="number" min="1" max="1000">
	<input class="btn btn-primary" type="submit" name="encherirAff"
		value="Encherir">

</body>
</html>