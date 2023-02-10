<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<a id="topMenu" href="${pageContext.request.contextPath}/inscription">S'inscrire
				</a>
			</div>
		</div>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Login</li>
		</ol>
	</nav>


	<div class="container">

		<div class="row justify-content-center">
			<h2>Login</h2>
		</div>
		<div class="row justify-content-center">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<label id="loginIdLabel" style="font-weight: bold">Identifiant : </label> <input id="loginId"
					type="text" name="id"> <br> <label style="font-weight: bold">Mot de
					Passe : </label> <input id="loginPassword" type="password" name="mdp">

				<br>

				<div class="inscriptionButtons">
					<input id="inscriptionButton" class="btn btn-primary"
						id="signUpOKButton" type="submit" value="Valider"> <a
						id="inscriptionButton" class="btn btn-secondary"
						href="${pageContext.request.contextPath}/accueil">Annuler</a>
				</div>
			</form>
		</div>

		<div id="logErrorMessages">

					<c:if test="${!empty listeErreur }">
			<c:forEach var="erreur" items="${listeErreur }">
				<div id="loginError" class="alert alert-danger" role="alert">
					${listeErreur }</div>
			</c:forEach>
		</c:if>
		</div>
	</div>

	<footer id="footer">
		<%@ include file="footer.html"%>
	</footer>

</body>
</html>