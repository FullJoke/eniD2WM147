<%@page import="java.util.List"%>
<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/Style.css" rel="stylesheet">
<title>Profil</title>
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
				<p id="topMenuUnusable">Mon Profil</p>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/CreationArticle">Vendre
					un article</a>
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
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/Profil">Profil</a></li>
			<li class="breadcrumb-item active" aria-current="page">Modification
				du Profil</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2>Mon Profil</h2>
		</div>
		<div class="row justify-content-md-center">


			<form action="<%=request.getContextPath()%>/ModificationProfil" method="post">

				<%
				Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
				%>

				<label id="signUpPseudoLabel">Pseudo : </label> <input
					id="ModifPseudo" pattern="[a-zA-Z0-9]{3,20}"
					value="<%=user.getPseudo()%>" name="pseudo"> <br> <label
					id="signUpNomLabel">Nom : </label> <input id="ModifNom" type="text"
					name="nom" value="<%=user.getNom()%>"> <br> <label
					id="signUpPrenomLabel">Prenom : </label> <input id="ModifPrenom"
					type="text" name="prenom" value="<%=user.getPrenom()%>"> <br>
				<label id="signUpEmailLabel">Email : </label> <input id="ModifEmail"
					type="email" name="email" value="<%=user.getEmail()%>"> <br>
				<label id="signUpTelephoneLabel">Telephone : </label> <input
					id="ModifTelephone" pattern="^0[0-9]{9}" type="tel" name="tel"
					value="<%=user.getTelephone()%>"> <br> <label
					id="signUpRueLabel">Rue : </label> <input id="ModifRue" type="text"
					name="rue" value="<%=user.getRue()%>"> <br> <label
					id="signUpCodePostalLabel">Code Postal : </label> <input
					id="ModifCodePostal" type="text" name="codePostal"
					value="<%=user.getCodePostal()%>"> <br> <label
					id="signUpVilleLabel">Ville : </label> <input id="ModifVille"
					type="text" name="ville" value="<%=user.getVille()%>"> <br>

				<label id="signUpMdpNewLabel">Nouveau mot de passe : </label> <input
					id="ModifNewMdp" type="password" name="mdp" placeholder="********"
					required
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br>
				<h6 class="mdpCom">
					Le mot de passe doit avoir au moins 8 caractères (max15),<br>
					un chiffre, une lettre majuscule et une minuscule
				</h6>
				<label id="signUpMdpNewLabel">Nouveau mot de passe : </label> <input
					id="ModifNewMdp" type="password" name="newMdp"
					placeholder="********"
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br> <label id="signUpConfirmationLabel">Confirmation :
				</label> <input id="ModifConfirmation" type="password" name="confirmation"
					placeholder="********"> <br>
				<label id="signUpCreditLabel">Crédit : </label>
				<input id="ModifCrédit" value="<%=user.getCredit()%>" readonly="readonly">
				<div class="ModifProfilButtons">
					<input id="inscriptionButton" class="btn btn-primary"
						id="ModifSaveButton" type="submit" value="Enregistrer"> <a
						id="inscriptionButton" class="btn btn-secondary"
						href="<%=request.getContextPath()%>/Profil">Annuler</a>

				</div>
			</form>
			<form action="<%=request.getContextPath()%>/delete" method="post">
				<input type="hidden" name="utilisateur"
					value="<%=user.getIdUtilisateur()%>">
				<button id="deleteButton" class="btn btn-danger"
					id="inscriptionButton" type="submit"
					onclick="window.location.href ='<%=request.getContextPath()%>/delete';">Supprimer</button>
			</form>

		</div>
	</div>
	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>
</body>
</html>