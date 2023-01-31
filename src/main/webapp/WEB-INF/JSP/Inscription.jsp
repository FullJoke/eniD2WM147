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
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Inscription</li>
		</ol>
	</nav>

	<div class="container">
		<div class="row justify-content-md-center">

			<h2>Inscription</h2>

		</div>



		<div class="row justify-content-md-center">

			<form action="" method="post">
				<label id="signUpPseudoLabel">Pseudo : </label>
				<input id="signUpPseudo" pattern="[A-z0-9]" type="text" name="pseudo"
					placeholder="Invite01">
					<br>
				<label id="signUpNomLabel">Nom : </label>
				<input id="signUpNom" type="text" name="nom" placeholder="Martin">
				<br>
				<label id="signUpPrenomLabel">Prenom : </label>
				<input id="signUpPrenom" type="text" name="prenom" placeholder="Camille">
				<br>
				<label id="signUpEmailLabel">Email : </label>
				<input id="signUpEmail" type="text" name="email"
					placeholder="martin.camille@fakemail.com">
				<br>
				<label id="signUpTelephoneLabel">Telephone : </label>
				<input id="signUpTelephone" pattern="^0[0-9]{9}" type="text" name="tel"
					placeholder="01 02 03 04 05">
				<br>
				<label id="signUpRueLabel">Rue : </label>
				<input id="signUpRue" type="text" name="rue" placeholder="rue de bidule">
				<br>
				<label id="signUpCodePostalLabel">Code Postal : </label>
				<input id="signUpCodePostal" type="text" name="codePostal"
					placeholder="11111">
				<br>
				<label id="signUpVilleLabel">Ville : </label>
				<input id="signUpVille" type="text" name="ville" placeholder="Fausseville">
				<br>
				<label id="signUpMdpLabel">Mot de passe : </label>
				<input id="signUpMdp"
					type="password" name="mdp" placeholder="********">
					<br>
				<label id="signUpConfirmationLabel">Confirmation : </label>
				<input id="signUpConfirmation" type="password" name="confirmation"
					placeholder="********">
				<br>
				<input class="btn btn-primary" id="signUpOKButton" type="submit" value="Valider">
				<input class="btn btn-primary" id="loginWithdrawButton"
					type="submit" value="Annuler">
			</form>

			<div id="logErrorMessages">
				<!-- Gestion des erreurs -->
				<%
				List<String> listeErreur = (List<String>) request.getAttribute("listeErreur");
				
				if (listeErreur != null) {
				%>
				<%
					for (String erreur : listeErreur) {
					%>
					<div class="d-flex justify-content-center">
						<p style="color: red"><%=erreur%></p>
					</div>
					<%
					}
				%>
				<%
				}
				%>
			</div>
		</div>
	</div>

</body>
</html>