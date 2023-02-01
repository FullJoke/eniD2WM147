<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</header>
	
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Profil</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row justify-content-md-center">
			<h2>Profil</h2>
		</div>
		
		<div class="row justify-content-md-center">
			<div>
		<%Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur"); %>
		
			<label id="profilPseudoLabel">Pseudo : </label>
			<input type="text" value="<%=user.getPseudo() %>">
			<br>
			
			<label id="profilNomLabel">Nom : </label>
			<input type="text" value="<%=user.getNom() %>">
			<br>
			
			<label id="profilPrenomLabel">Prenom : </label>
			<input type="text" value="<%=user.getPrenom() %>">
			<br>
			
			<label id="profilEmailLabel">Email : </label>
			<input type="text" value="<%=user.getEmail() %>">
			<br>
			
			<label id="profilTelephoneLabel">Telephone : </label>
			<input type="text" value="<%=user.getTelephone() %>">
			<br>
			
			<label id="profilRueLabel">Rue : </label>
			<input type="text" value="<%=user.getRue() %>">
			<br>
			
			<label id="profilCodePostalLabel">Code Postal : </label>
			<input type="text" value="<%=user.getCodePostal() %>">
			<br>
			
			<label id="profilVilleLabel">Ville : </label>
			<input type="text" value="<%=user.getVille() %>">
			<br>
			
			</div>
			
		</div>
		
		<input class="btn btn-primary" id="signModifButton" type="submit" value="Modifier">
		
	</div>
	
	
	
	
</body>
</html>