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
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se
					Connecter</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Inscription</li>
		</ol>
	</nav>

	<div class="container">
		<div class="row justify-content-md-center">

			<h2>Inscription</h2>

		</div>



		<div class="row justify-content-md-center">
			<form action="<%=request.getContextPath()%>/inscription"
				method="post">
				<label id="signUpPseudoLabel">Pseudo : </label> <input
					id="signUpPseudo" pattern="[a-zA-Z0-9]{3,20}"
					placeholder="Invite01" name="pseudo"> <br> <label
					id="signUpNomLabel">Nom : </label> <input id="signUpNom"
					type="text" name="nom" placeholder="Martin"> <br> <label
					id="signUpPrenomLabel">Prenom : </label> <input id="signUpPrenom"
					type="text" name="prenom" placeholder="Camille"> <br>
				<label id="signUpEmailLabel">Email : </label> <input
					id="signUpEmail" type="email" name="email"
					placeholder="martin.camille@fakemail.com"> <br> <label
					id="signUpTelephoneLabel">Telephone : </label> <input
					id="signUpTelephone" pattern="^0[0-9]{9}" type="tel" name="tel"
					placeholder="01 02 03 04 05"> <br> <label
					id="signUpRueLabel">Rue : </label> <input id="signUpRue"
					type="text" name="rue" placeholder="rue de bidule"> <br>
				<label id="signUpCodePostalLabel">Code Postal : </label> <input
					id="signUpCodePostal" type="text" name="codePostal"
					placeholder="11111"> <br> <label id="signUpVilleLabel">Ville
					: </label> <input id="signUpVille" type="text" name="ville"
					placeholder="Fausseville"> <br>
				<h6 class="mdpCom">
					Le mot de passe doit avoir au moins 8 caractères (max15),<br>
					un chiffre, une lettre majuscule et une minuscule
				</h6>
				<label id="signUpMdpLabel">Mot de passe : </label> <input
					id="signUpMdp" type="password" name="mdp" placeholder="********"
					required
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[-+!*$@%_])([-+!*$@%_\w]{8,15})$">
				<br> <label id="signUpConfirmationLabel">Confirmation :
				</label> <input id="signUpConfirmation" type="password" name="confirmation"
					placeholder="********"> <br>
				<div class="inscriptionButtons">
					<input id="inscriptionButton" class="btn btn-primary"
						id="signUpOKButton" type="submit" value="Valider"> <a
						id="inscriptionButton" class="btn btn-secondary"
						href="<%=request.getContextPath()%>/accueil">Annuler</a>
				</div>
			</form>
		</div>
	</div>


</body>
</html>