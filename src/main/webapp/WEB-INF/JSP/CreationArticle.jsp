<%@page import="java.util.List"%>
<%@page import="fr.eni.eniD2WM147.bo.Categorie"%>
<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation de l'article</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
</head>
<body>

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
			<form method="Post" action="">
				<label>Article :</label> <input type="text" name="article">
				
				<br>
				
				<label id="">Description :</label>
				<textarea id="story" name="story" rows="5" cols="33"></textarea>
				
				<br>
				
				<%
				List<Categorie> categories =
				(List<Categorie>) request.getAttribute("categories");
				%>
				
				<label>Catégories :</label>
				<select name="listcate">
				<option value="0">Toutes</option>
				<%
				for (Categorie c : categories) {
				%>
				<option value="<%=c.getNumCategorie()%>"><%=c.getLibelle()%></option>
				<%
				}
				%>
				</select>
				
				<br>
				
				<label>Photo de l'article :</label>
				<input type="image" name="photoArticle">
				
				<br>
				
				<label>Mise à prix</label>
				<input type="number" name="miseAprix" min="1" max="1000">
				
				<br>
				
				<label>Début de l'enchère</label>
				<input type="datetime-local" name="debutEnchere">
				
				<br>
				
				<label>Fin de lenchère</label>
				<input type="datetime-local" name="finEnchere">
				
				<br>
				
				<fieldset>
					<legend>Retrait</legend>
					<label>Rue :</label> <input type="text" name="rue"> <br>
					<label>Code Postal :</label> <input type="text" name="codePostal">
					<br> <label>Ville :</label> <input type="text" name="ville">
				</fieldset>


				<input class="btn btn-primary" type="submit" name="saveNewArt"
					value="Enregistrer"> <input class="btn btn-primary"
					type="submit" name="annulerNewArt" value="Annuler">
			</form>
		</div>
	</div>
</body>
</html>