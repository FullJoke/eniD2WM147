<%@page import="fr.eni.eniD2WM147.bo.ArticleVendu"%>
<%@page import="fr.eni.eniD2WM147.bo.Retrait"%>
<%@page import="java.util.List"%><%@page
	import="fr.eni.eniD2WM147.bo.Categorie"%><%@page
	import="fr.eni.eniD2WM147.bo.Utilisateur"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation de l'article</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/Style.css" rel="stylesheet">
</head>
<body>

	<div id="logErrorMessages">
		<!-- Gestion des erreurs -->
		<%
		List<String> listeErreur = (List<String>) request.getAttribute("listeErreur");
		if (listeErreur != null) {
		%>
		<%
		for (String erreur : listeErreur) {
		%>
		<div id="loginError" class="alert alert-danger" role="alert">
			<%=erreur%></div>
		<%--  			<div class="d-flex justify-content-center">
				<p style="color: red"><%=erreur%></p>
			</div> --%>

		<%
		}
		%>
		<%
		}
		%>
	</div>

	<header>
		<%@ include file="Entete.html"%>
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
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/accueil">Enchères</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Création
				de vente</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2 id="profilTitle">Création de vente</h2>
		</div>
		<div class="row justify-content-md-center">

			<form method="Post"
				action="<%=request.getContextPath()%>/CreationArticle">
				<label>Article :</label> <input type="text" name="nomArticle" required value="${nomArticle}">
				<br> <label id="CreaVenteDesc">Description :</label> <br>

				<label id="CreaVenteDesc">Description :</label>

				<textarea id="descritpion" name="descritpion" rows="5" cols="33" maxlength="300" ></textarea>
				<br>
				<%
				Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
				List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
				%>
				<label id="CreaVenteCat">Catégories :</label> <select
					name="listcate">
					<%
					for (Categorie c : categories) {
					%>
					<option value="<%=c.getNumCategorie()%>"><%=c.getLibelle()%></option>
					<%
					}
					%>
				</select> <br> <label id="CreaVenteImage">Photo de l'article :</label> <input
					type="image" name="photoArticle"> <br> 
					<label
					id="CreaVentePrixInit">Mise à prix :</label> <input type="number"
					name="miseAprix" min="1" max="1000"> <br> 
					
					<label
					id="CreaVenteDebEnchere" >Début de l'enchère :</label> <input required
					type="datetime-local" name="debutEnchere"> <br> <label
					id="CreaVenteFinEnchere">Fin de l'enchère :</label> <input required
					type="datetime-local" name="finEnchere"> <br>
				<fieldset>
					<legend>Retrait</legend>
					<label id="CreaVenteRue">Rue :</label> <input required type="text"
						name="rue" value="<%=user.getRue()%>"> <br> 
						
						<label
						id="CreaVenteCodeP">Code Postal :</label> <input required type="text"
						name="codePostal" value="<%=user.getCodePostal()%>"> <br>
						
					<label id="CreaVenteVille">Ville :</label> <input required type="text"
						name="ville" value="<%=user.getVille()%>">
				</fieldset>
				<div id="CreaVenteBtn">
					<input class="btn btn-primary" type="submit" name="saveNewArt"
						value="Enregistrer"> <a
						href="<%=request.getContextPath()%>/accueil"
						class="btn btn-secondary"> Annuler</a>
				</div>
			</form>
		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>