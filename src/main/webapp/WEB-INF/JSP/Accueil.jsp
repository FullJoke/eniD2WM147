<%@page import="fr.eni.eniD2WM147.bo.ArticleVendu"%>
<%@page import="fr.eni.eniD2WM147.bo.Categorie"%>
<%@page import="java.util.List"%>
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
				<a id="topMenu" href="http://www.google.fr">Mon Profil</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="http://www.google.fr">Vendre un article</a>
			</div>
			.
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

	<%-- 	<%List<Categorie> articles = (List<Categorie>) request.getAttribute("articles");%>

	<%for (Categorie c : articles) {%>
		<%=c.getLibelle()%><br>
		<%for (ArticleVendu a : c.getArticles()) {%>
			<%=a%>
		<%} %>
	<%}%> --%>


	<div class="container">
		<form action="<%=request.getContextPath()%>/accueil">
			<label for="cat-select">Filtre : </label> <select name="categorie"
				id="cat-select">
				<option value="all">Toutes catégories</option>
				<option value="Info">Info</option>
				<option value="Sport">Sport</option>
				<option value="Numérique">numérique</option>
			</select> <input type="submit" value="OK">
		</form>

		<%
		String cat = (String) request.getAttribute("nom");
		%>

		<div class="row">
			<div class="card" style="width: 18rem;">
				<img class="card-img-top" src="..." alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title"><%=cat%></h5>
					<p class="card-text">Prix : XXX crédits</p>
					<p class="card-text">Fin de l'enchère : jj/mm/yyyy</p>
					<p class="card-text">Vendeur : NinJea</p>
					<a href="#" class="btn btn-primary">Infos</a>
				</div>

			</div>



		</div>
	</div>
</body>
</html>