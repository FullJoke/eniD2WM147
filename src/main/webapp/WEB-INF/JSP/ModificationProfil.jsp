<%@page import="java.util.List"%><%@page
	import="fr.eni.eniD2WM147.bo.Utilisateur"%><%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/Style.css" rel="stylesheet">
<title>Profil</title>
</head>
<body>
	<div id="logErrorMessages">
		<c:if test="${!empty listeErreur }">
			<c:forEach var="erreur" items="${listeErreur }">
				<div id="loginError" class="alert alert-danger" role="alert">
					${listeErreur }</div>
			</c:forEach>
		</c:if>
	</div>
	<header>
		<%@ include file="Entete.html"%>
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
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/accueil">Home</a></li>
			<li class="breadcrumb-item"><a href="#" onclick="history.go(-1)">Profil</a></li>
			<li class="breadcrumb-item active" aria-current="page">Modification
				du Profil</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2>Mon Profil</h2>
		</div>
		<div class="row justify-content-md-center">
			<form action="${pageContext.request.contextPath}/ModificationProfil"
				method="post">
				<label id="signUpPseudoLabel"style="font-weight: bold">Pseudo : </label> <input
					id="ModifPseudo" pattern="[a-zA-Z0-9]{3,20}"
					value="${Utilisateur.pseudo}" name="pseudo"> <br> <label
					id="signUpNomLabel"style="font-weight: bold">Nom : </label> <input id="ModifNom" type="text"
					name="nom" value="${Utilisateur.nom}"> <br> <label
					id="signUpPrenomLabel"style="font-weight: bold">Prenom : </label> <input id="ModifPrenom"
					type="text" name="prenom" value="${Utilisateur.prenom}"> <br>
				<label id="signUpEmailLabel"style="font-weight: bold">Email : </label> <input id="ModifEmail"
					type="email" name="email" value="${Utilisateur.email}"> <br>
				<label id="signUpTelephoneLabel"style="font-weight: bold">Telephone : </label> <input
					id="ModifTelephone" pattern="^0[0-9]{9}" type="tel" name="tel"
					value="${Utilisateur.telephone}"> <br> <label
					id="signUpRueLabel"style="font-weight: bold">Rue : </label> <input id="ModifRue" type="text"
					name="rue" value="${Utilisateur.rue}"> <br> <label
					id="signUpCodePostalLabel"style="font-weight: bold">Code Postal : </label> <input
					id="ModifCodePostal" type="text" name="codePostal"
					value="${Utilisateur.codePostal}"> <br> <label
					id="signUpVilleLabel"style="font-weight: bold">Ville : </label> <input id="ModifVille"
					type="text" name="ville" value="${Utilisateur.ville}"> <br>
				<label id="signUpMdpLabel"style="font-weight: bold">Mot de passe : </label> <input
					id="ModifNewMdp" type="password" name="mdp" placeholder="********"
					required
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br>
				<h6 class="mdpCom">
					Le mot de passe doit avoir au moins 8 caractères (max15),<br>
					un chiffre, une lettre majuscule et une minuscule
				</h6>
				<label id="signUpMdpNewLabel"style="font-weight: bold">Nouveau mot de passe : </label> <input
					id="ModifNewMdp" type="password" name="newMdp"
					placeholder="********"
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br> <label id="signUpConfirmationLabel"style="font-weight: bold">Confirmation :
				</label> <input id="ModifConfirmation" type="password" name="confirmation"
					placeholder="********"> <br> <label
					id="signUpCreditLabel"style="font-weight: bold">Crédit : </label> <input id="ModifCrédit"
					value="${Utilisateur.credit}" readonly="readonly">
				<div class="ModifProfilButtons">
					<input id="inscriptionButton" class="btn btn-primary"
						id="ModifSaveButton" type="submit" value="Enregistrer"> <a
						id="inscriptionButton" class="btn btn-secondary"
						href="#" onclick="history.go(-1)">Annuler</a>
				</div>
			</form>
			<form action="${pageContext.request.contextPath}/delete"
				method="post">
				<input type="hidden" name="utilisateur"
					value="${Utilisateur.idUtilisateur}">
				<button id="deleteButton" class="btn btn-danger"
					id="inscriptionButton" type="submit"
					onclick="window.location.href ='${pageContext.request.contextPath}/delete';">Supprimer</button>
			</form>
		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>