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


</head>
<body>

	<header>
		<%@ include file="Entete.html"%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/inscription">S'inscrire
				</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Login</li>
		</ol>
	</nav>


	<div class="container">

		<div class="row justify-content-center">
			<h2>Login</h2>
		</div>
		<div class="row justify-content-center">
			<form action="<%=request.getContextPath()%>/login" method="post">

				<label id="loginIdLabel">Identifiant : </label> <input id="loginId"
					type="text" name="id"> <br> <label>Mot de
					Passe : </label> <input id="loginPassword" type="password" name="mdp">
				<br> <input id="loginOKButton" type="submit" value="Valider">
			</form>
		</div>

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

</body>
</html>