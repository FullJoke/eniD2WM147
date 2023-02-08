<%@page import="fr.eni.eniD2WM147.bo.Categorie"%><%@page
	import="fr.eni.eniD2WM147.bo.ArticleVendu"%><%@page
	import="java.util.List"%><%@page
	import="fr.eni.eniD2WM147.bo.Utilisateur"%><%@taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
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
		<c:choose>
			<c:when test="${empty Utilisateur }">
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/inscription">S'inscrire
						</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu" href="${pageContext.request.contextPath}/login">
							Se Connecter</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu" href="${pageContext.request.contextPath}/Profil">Mon
							Profil</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/CreationArticle">Vendre
							un article</a>
					</div>
					.
					<div class="p-2">
						<p id="topMenuUnusable">Enchères</p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>
	<div class="container">
		<h2>Liste des enchères</h2>
		<form action="${pageContext.request.contextPath}/accueil"
			method="post">
			<label id="selectArticle">Filtres :</label> <input type="text"
				name="rechercheClavier" placeholder="Le nom de l'article contient">
			<br> <label>Categories :</label> <select id="categorieSelect"
				name="categorieChoisie">
				<option value="0">Toutes</option>
				<c:forEach var="c" items="${categories}">
					<option value="${c.numCategorie}">${c.libelle}</option>
				</c:forEach>
			</select> <input class="btn btn-primary" id="categorieSelectButton"
				type="submit" name="categorieSelectButton" value="Filtrer">
			<!-- </form> -->
			<c:if test="${!empty Utilisateur }">
				<div id="AccueilAchatsRadio">
					<div>
						<input type="radio" name="RButton" value="Achats"> <label
							id="achat" for="Achats">Achats</label>
					</div>
					<br> <label id="EnchereOuvCheck" for="enchères ouvertes">enchères
						ouvertes</label> <input type="checkbox" name="enchereOuvCheck"> <br>
					<label id="MesEncheresCheck" for="mes enchères"> mes
						enchères</label> <input type="checkbox" name="mesEncheresCheck"> <br>
					<label id="EnchereWinCheck" for=" mes enchères remportées">mes
						enchères remportées</label> <input type="checkbox"
						name="enchereRemporteesCheck">
				</div>
				<div id="AccueilVentesRadio">
					<div>
						<input type="radio" id="ventes" name="RButton" value="Ventes">
						<label for="Mes Ventes">Mes Ventes</label>
					</div>
					<br> <label for="mes ventes en cours">mes ventes en
						cours</label> <input type="checkbox" name="ventesEnCoursCheck"> <br>
					<label for="ventes non debutées">ventes non debutées</label> <input
						type="checkbox" name="ventesNonDébutéesCheck"> <br> <label
						for="ventes terminées">ventes terminées</label> <input
						type="checkbox" name=ventesTermineesCheck>
				</div>
			</c:if>
		</form>
		<div id="articlesListe" class="row">
			<c:if test="${!empty articles }">
				<c:forEach var="a" items="${articles}">
					<div class="card" style="width: 15rem">
						<form
							action="${pageContext.request.contextPath}/AfficherDetailArticle">
							<input type="hidden" name="Article" value="${a.idArticle}">
							<img class="card-img-left" src="..." alt="Image Produit">
							<div class="card-body">
								<h5 class="card-title">${a.nom}</h5>
								<p class="card-text">Prix : ${a.enchere.montantEnchere==0? a.prixInitial : a.enchere.montantEnchere}
									crédits</p>
								<fmt:parseDate value="${a.finEnchere}"
									pattern="yyyy-MM-dd'T'HH:mm" var="date_fin_enchere" />
								<fmt:formatDate value="${date_fin_enchere}"
									pattern="dd MMMM yyyy HH:mm" var="dateFin" />
								<p class="card-text">
									<b>Fin de l'enchère : </b><br> ${dateFin}
								</p>
								<p class="card-text">Vendeur : ${a.utilisateur.pseudo}</p>
								<c:if test="${!empty Utilisateur }">
									<input id="DetailArticleButton" class="btn btn-primary"
										type="submit" value="Détails">
								</c:if>
							</div>
						</form>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty articles}">
				<div class="alert alert-info" role="alert" id="noArticleAlerte">
					Il n'y a aucun article dans cette catégorie.</div>
			</c:if>
		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>