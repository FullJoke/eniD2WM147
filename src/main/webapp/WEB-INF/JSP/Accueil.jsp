<%@page import="fr.eni.eniD2WM147.bo.Categorie"%>
<%@page import="fr.eni.eniD2WM147.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<a id="topMenu" href="<%=request.getContextPath()%>/CreationArticle">Vendre
					un article</a>
			</div>
			.
			<div class="p-2">
				<p id="topMenuUnusable">Enchères</p>
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
		<form action="<%=request.getContextPath()%>/accueil" method="post">
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
			</select> <input class="btn btn-primary" id="categorieSelectButton"
				type="submit" name="categorieSelectButton" value="Filtrer">
		<!-- </form> -->
		
		<%if (Utilisateur != null) {%>
<!-- 		<form method="post" action=""> -->
		<div id="AccueilAchatsRadio">
		<div>
			<input type="radio" name="RButton" value="Achats">
			<label id="achat" for="Achats">Achats</label>
		</div>

		<br> <label id="EnchereOuvCheck" for="enchères ouvertes">enchères ouvertes</label>
		<input type="checkbox" name="enchereOuvCheck">
		
		<br>
		
		<label id="MesEncheresCheck" for="mes enchères"> mes enchères</label>
		<input type="checkbox" name="mesEncheresCheck">
		
		<br>
		
		<label id="EnchereWinCheck" for=" mes enchères remportées">mes enchères remportées</label>
		<input type="checkbox" name="enchereRemporteesCheck">
		</div>

 <!-- ------------------------------------------------------------------------ -->

		<div id="AccueilVentesRadio">
		<div>
			<input type="radio" id="ventes" name="RButton" value="Ventes">
			<label for="Mes Ventes">Mes Ventes</label>
		</div>
		
		<br>
		
		<label for="mes ventes en cours">mes ventes en cours</label>
		<input type="checkbox" name="ventesEnCoursCheck">
		
		<br>
		
		<label for="ventes non debutées">ventes non debutées</label>
		<input type="checkbox" name="ventesNonDébutéesCheck">
		
		<br>
		
		<label for="ventes terminées">ventes terminées</label>
		<input type="checkbox" name=ventesTermineesCheck>
		</div>
	</form>
	<%}%>


		<div id="articlesListe" class="row">
			<%
			if (!articles.isEmpty()) {
			%>
			<%
			for (ArticleVendu a : articles) {
				int idArt = a.getIdArticle();
			%>
			<div class="card" style="width: 15rem">
				<form action="<%=request.getContextPath()%>/AfficherDetailArticle">
					<input type="hidden" name="Article" value="<%=idArt%>"> <img
						class="card-img-left" src="..." alt="Image Produit">
					<div class="card-body">
						<h5 class="card-title"><%=a.getNom()%></h5>
						<p class="card-text">
							Prix :
							<%=a.getPrixInitial()%>
							crédits
						</p>
						<p class="card-text">
							Fin de l'enchère :
							<%=a.getFinEnchere()%></p>
						<p class="card-text">
							Vendeur :
							<%=a.getUtilisateur().getPseudo()%></p>

						<%
						if (Utilisateur != null) {
						%>
						<input id="DetailArticleButton" class="btn btn-primary"
							type="submit" value="Détails">
						<%
						}
						%>
					</div>
				</form>
			</div>
			<%
			}
			%>
			<%
			}
			%>
			<%
			if (articles.isEmpty()) {
			%>
			<div class="alert alert-info" role="alert" id="noArticleAlerte">
				Il n'y a aucun article dans cette catégorie.</div>
			<%
			}
			%>

		</div>
	</div>


	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>

</body>
</html>