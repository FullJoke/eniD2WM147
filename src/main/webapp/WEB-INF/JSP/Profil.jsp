<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
			</div>
			.
			<div class="p-2">
				<p id="topMenuUnusable">Mon Profil</p>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="${pageContext.request.contextPath}/CreationArticle">Vendre
					un article</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="${pageContext.request.contextPath}/accueil">Enchères</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page" >Profil</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2 id="profilTitle">Profil</h2>
		</div>
		<div class="row justify-content-md-center">
			<div>

				<label id="profilPseudoLabel" style="font-weight: bold">Pseudo : </label> <span>${vendeur.pseudo}</span>
				<br> <label id="profilNomLabel" style="font-weight: bold">Nom : </label> <span>${vendeur.nom}</span>
				<br> <label id="profilPrenomLabel" style="font-weight: bold">Prenom : </label> <span>${vendeur.prenom}</span>
				<br> <label id="profilEmailLabel" style="font-weight: bold">Email : </label> <span>${vendeur.email}</span>
				<br> <label id="profilTelephoneLabel" style="font-weight: bold">Telephone : </label> <span>${vendeur.telephone}</span>
				<br> <label id="profilRueLabel" style="font-weight: bold">Rue : </label> <span>${vendeur.rue}</span>
				<br> <label id="profilCodePostalLabel" style="font-weight: bold">Code Postal : </label> <span>${vendeur.codePostal}</span>
				<br> <label id="profilVilleLabel" style="font-weight: bold">Ville : </label> <span>${vendeur.ville}</span>
				<br>
				
				<c:if test="${vendeur.idUtilisateur==Utilisateur.idUtilisateur}">
					<button class="btn btn-primary" id="profilModifButton"
						onclick="window.location.href ='${pageContext.request.contextPath}/ModificationProfil';">Modifier</button>
				</c:if>
			</div>
		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>