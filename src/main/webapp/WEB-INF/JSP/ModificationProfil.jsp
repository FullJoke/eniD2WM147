<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
<title>Profil</title>
</head>
<body>

	<header>
		<%@ include file="Entete.html"%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se
					Connecter</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Modification
				du Profil</li>
		</ol>
	</nav>

	<div class="container">
		<div class="row justify-content-md-center">

			<h2>Mon Profil</h2>

		</div>



		<div class="row justify-content-md-center">

			<form action="<%=request.getContextPath()%>/ModificationProfil"
				method="post">
				<%
				Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
				%>
				<label id="ModifPseudoLabel">Pseudo : </label> <input
					id="ModifPseudo" pattern="[a-zA-Z0-9]{3,20}"
					value="<%=user.getPseudo()%>"> <br> <label
					id="ModifNomLabel">Nom : </label> <input id="ModifNom" type="text"
					name="nom" value="<%=user.getNom()%>"> <br> <label
					id="ModifPrenomLabel">Prenom : </label> <input id="ModifPrenom"
					type="text" name="prenom" value="<%=user.getPrenom()%>"> <br>

				<label id="ModifEmailLabel">Email : </label> <input id="ModifEmail"
					type="email" name="email" value="<%=user.getEmail()%>"> <br>

				<label id="ModifTelephoneLabel">Telephone : </label> <input
					id="ModifTelephone" pattern="^0[0-9]{9}" type="tel" name="tel"
					value="<%=user.getTelephone()%>"> <br> <label
					id="ModifRueLabel">Rue : </label> <input id="ModifRue" type="text"
					name="rue" value="<%=user.getRue()%>"> <br> <label
					id="ModifCodePostalLabel">Code Postal : </label> <input
					id="ModifCodePostal" type="text" name="codePostal"
					value="<%=user.getCodePostal()%>"> <br> <label
					id="ModifVilleLabel">Ville : </label> <input id="ModifVille"
					type="text" name="ville" value="<%=user.getVille()%>"> <br>

				<label id="ModifMdpActuelLabel">Ancien mot de passe : </label> <input
					id="ModifMdpActuel" type="password" name="mdp"
					placeholder="********" required
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br>

				<h6>Le mot de passe doit avoir au moins 8 caractères (max15),
					un chiffre, une lettre majuscule et une minuscule</h6>
				<label id="ModifNewMdpLabel">Nouveau mot de passe : </label> <input
					id="ModifNewMdp" type="password" name="mdp" placeholder="********"
					required
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br> <label id="ModifConfirmationLabel">Confirmation :
				</label> <input id="ModifConfirmation" type="password" name="confirmation"
					placeholder="********"> <br>

				<div class="ModifProfilButtons">
					<input id="inscriptionButton" class="btn btn-primary"
						id="ModifSaveButton" type="submit" value="Enregistrer"> <a
						id="inscriptionButton" class="btn btn-secondary"
						href="<%=request.getContextPath()%>/Profil">Annuler</a> <a
						id="inscriptionButton" class="btn btn-danger"
						href="http://www.google.fr">Supprimer</a>
				</div>
			</form>

		</div>
	</div>

</body>
</html>