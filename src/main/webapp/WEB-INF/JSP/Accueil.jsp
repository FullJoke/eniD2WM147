<%@page import="fr.eni.eniD2WM147.bo.Categorie"%>
<%@page import="fr.eni.eniD2WM147.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
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
			<li id="bread" class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>


	<div class="container">
		<h2>Liste des enchères</h2>
		<%
		List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
		%>
		<%
		List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute("articles");
		%>


		<label id="selectArticle">Filtres :</label> <input type="text"
			name="selectArt" placeholder="Le nom de l'article contient">
		<br>



		<form action="<%=request.getContextPath()%>/accueil" method="get">
			<label>Categories :</label> <select id="categorieSelect"
				name="categorieChoisie">
				<option value="0">Toutes</option>
				<%
				for (Categorie c : categories) {
				%>
				<option value="<%=c.getNumCategorie()%>"><%=c.getLibelle()%></option>
				<%
				}
				%>
			</select> <input class="btn btn-primary" id="Search" type="submit"
				name="categorieSelectButton" value="Filtre">
		</form>


		<div id="articlesListe" class="row">
			<%
			for (ArticleVendu a : articles) {
			%>
			<div class="card" style="width: 15rem;">
				<img class="card-img-left" src="..." alt="PC GAMER">
				<div class="card-body">
					<h5 class="card-title"><%=a.getNom()%></h5>
					<p class="card-text">
						Prix :
						<%=a.getPrixVente()%>
						crédits
					</p>
					<p class="card-text">
						Fin de l'enchère :
						<%=a.getFinEnchere()%></p>
					<%-- 					<p class="card-text">"Vendeur : <%=a.getUtilisateur().getIdUtilisateur()%>"</p>
 --%>
				</div>
			</div>
			<%
			}
			%>

		</div>
	</div>
</body>
</html>