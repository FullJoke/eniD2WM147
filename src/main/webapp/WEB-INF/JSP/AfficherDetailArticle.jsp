<%@page import="fr.eni.eniD2WM147.bo.Categorie"%><%@page
	import="fr.eni.eniD2WM147.bo.ArticleVendu"%><%@page
	import="fr.eni.eniD2WM147.bo.Utilisateur"%><%@taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
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

		<div id="logErrorMessages">

			<c:if test="${!empty listeErreur }">
				<c:forEach var="erreur" items="${listeErreur }">
					<div id="loginError" class="alert alert-danger" role="alert">
						${listeErreur }</div>
				</c:forEach>

			</c:if>
		</div>


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
						<form action="${pageContext.request.contextPath}/Profil"
							method="post">
							<button id="topMenu" name="vendeur"
								value="${Utilisateur.idUtilisateur}">Mon Profil</button>
						</form>
					</div>
					.
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/CreationArticle">Vendre
							un article</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu" href="${pageContext.request.contextPath}/accueil">Enchères</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/accueil">Home</a></li>
			<li id="detailVente" class="breadcrumb-item active"
				aria-current="page">Détails Vente</li>
		</ol>
	</nav>



	<div class="container">
		<form id="detailsVendeurBtn"
			action="${pageContext.request.contextPath}/Profil" method="post">

			<div class="row justify-content-md-center">
				<h2 id="AfficherDétailTitle" style="text-align: center;">Détail
					Vente</h2>
			</div>


			<div class="row justify-content-md-center">
				<!-- Comme pas de boucle ou pas d'article que l'on peut créer avec des cheverons,
 on utilise l'attribut de l'article pour obtenir ses infos -->
				<div class="row justify-content-md-center">
					<h4 id="NomArt" style="text-align: center;">${ArticleAAfficher.nom}</h4>
					<br>
				</div>

				<div id="detailsArticle">
					<label id="descriptionArt" style="font-weight: bold">Description
						: </label>
					<textarea readonly style="vertical-align: top">${ArticleAAfficher.description}</textarea>
					<br> <br> <label id="cateArt" style="font-weight: bold">Categorie
						: </label> <span>${ArticleAAfficher.categorie.libelle}</span> <br> <label
						id="bestOfferArt" style="font-weight: bold">Meilleure
						offre : </label> Par <span style="text-decoration: underline"><c:if
							test="${ArticleAAfficher.enchere.montantEnchere > 0}">
						 ${ArticleAAfficher.enchere.utilisateur.pseudo}
                        </c:if></span> pour<span style="text-decoration: underline">
						${ArticleAAfficher.enchere.montantEnchere} </span>crédits <br> <label
						id="misePrixArt" style="font-weight: bold">Mise à prix : </label>
					<span>${ArticleAAfficher.prixInitial} pts</span> <br>

					<fmt:parseDate value="${ArticleAAfficher.finEnchere}"
						pattern="yyyy-MM-dd'T'HH:mm" var="date_fin_enchere" />
					<fmt:formatDate value="${date_fin_enchere}"
						pattern="dd MMMM yyyy HH:mm" var="dateFin" />
					<label id="finEncArt" style="font-weight: bold">Fin de
						l'enchère : </label> <span>${dateFin}</span> <br> <label
						id="retraitArt" style="font-weight: bold">Retrait : </label><span>
						${ArticleAAfficher.retrait.rue }, </span> <span>
						${ArticleAAfficher.retrait.codePostal}
						${ArticleAAfficher.retrait.ville}</span> <br> <label id="vendeurArt"
						style="font-weight: bold">Vendeur : </label><span id="idVendeur">${ArticleAAfficher.utilisateur.pseudo}</span>
					<input type="hidden" name="vendeur"
						value="${ArticleAAfficher.utilisateur.idUtilisateur}"> <input
						id="infoVendeurButton" type="submit" value="Détails Vendeur"
						class="btn btn-primary"> <br>
				</div>
			</div>
		</form>

		<div id="myOffer">
			<form action="${pageContext.request.contextPath}/FaireUneEnchere"
				method="post">
				<label id="porpositionArt" style="font-weight: bold">Ma
					proposition : </label> <input type="hidden" name="idArticle"
					value="${ArticleAAfficher.idArticle}"> <input type="hidden"
					name="bestOffer" value="${ArticleAAfficher.enchere.montantEnchere}">
				<input type="number" min="1" max="1000" name="enchere"> <input
					id="MakeAnEnchereButton" class="btn btn-primary" type="submit"
					name="encherirAff" value="Encherir">


			</form>

			<div>
				<form method="post"
					action="${pageContext.request.contextPath}/deleteArticle">
					<c:if
						test="${ArticleAAfficher.utilisateur.idUtilisateur==Utilisateur.idUtilisateur }">
						<button class="btn btn-danger" id="supressionArtButton"
							value="${ArticleAAfficher.idArticle }" type="submit"
							name="articleASupprimer"
							onclick="window.location.href ='${pageContext.request.contextPath}/deleteArticle';">Supprimer
							Vente</button>
					</c:if>
				</form>
			</div>
		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>