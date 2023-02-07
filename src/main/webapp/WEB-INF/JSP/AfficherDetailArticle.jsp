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
		<%
		Utilisateur Utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
		%>
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
			<li class="breadcrumb-item active" aria-current="page">Détails
				Vente</li>
		</ol>
	</nav>
	<%
	ArticleVendu av = (ArticleVendu) request.getAttribute("ArticleAAfficher");
	%>
	<div id="AfficherDétailsArticle" class="container">
		<form id="detailsVendeurBtn"
			action="${pageContext.request.contextPath}/Profil" method="post">
			<h2 id="AfficherDétailTitle" style="text-align: center;">Détail
				Vente</h2>
			<div class="row justify-content-md-center">
				<!-- Comme pas de boucle ou pas d'article que l'on peut créer avec des cheverons,
 on utilise l'attribut de l'article pour obtenir ses infos -->
				<h4 id="NomArt" style="text-align: center;">${ArticleAAfficher.nom}</h4>
				<br>
				<div id="detailsArticle">
					<label id="descriptionArt">Description : </label>
					<textarea readonly style="vertical-align: top">${ArticleAAfficher.description}</textarea>
					<br> <br> <label id="cateArt">Categorie : <span>${ArticleAAfficher.categorie.libelle}</span>
					</label> <br> <label id="bestOfferArt">Meilleure offre : <span>${ArticleAAfficher.enchere.montantEnchere}
							crédits</span> <c:if
							test="${ArticleAAfficher.enchere.montantEnchere > 0}">                            par ${ArticleAAfficher.enchere.utilisateur.pseudo}
                        </c:if>
					</label> <br> <label id="misePrixArt">Mise à prix : <span>${ArticleAAfficher.prixInitial}
							pts</span></label> <br>
					<fmt:parseDate value="${ArticleAAfficher.finEnchere}"
						pattern="yyyy-MM-dd'T'HH:mm" var="date_fin_enchere" />
					<fmt:formatDate value="${date_fin_enchere}"
						pattern="dd MMMM yyyy HH:mm" var="dateFin" />
					<label id="finEncArt">Fin de l'enchère : <span>${dateFin}</span>
					</label> <br> <label id="retraitArt">Retrait : <span>${ArticleAAfficher.retrait.rue },
					</span> <span>${ArticleAAfficher.retrait.codePostal}
							${ArticleAAfficher.retrait.ville}</span>
					</label> <br> <label id="vendeurArt">Vendeur <span>${ArticleAAfficher.utilisateur.pseudo}</span>
					</label> <input type="hidden" name="vendeur"
						value="${ArticleAAfficher.utilisateur.idUtilisateur}"> <input
						type="submit" value="Détails Vendeur" class="btn btn-primary">
					<br>
				</div>
			</div>
		</form>
	</div>
	<div id="myOffer">
		<form action="${pageContext.request.contextPath}/FaireUneEnchere"
			method="post">
			<label id="porpositionArt">Ma proposition : </label> <input
				type="hidden" name="idArticle" value="<%=av.getIdArticle()%>">
			<input type="hidden" name="bestOffer"
				value="<%=av.getEnchere().getMontantEnchere()%>"> <input
				type="number" min="1" max="1000" name="enchere"> <input
				id="MakeAnEnchereButton" class="btn btn-primary" type="submit"
				name="encherirAff" value="Encherir">
		</form>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>