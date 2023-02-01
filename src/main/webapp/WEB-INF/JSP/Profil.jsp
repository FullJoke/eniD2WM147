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
			<span><%=user.getPseudo() %></span>
			<br>
			
			<label id="profilNomLabel">Nom : </label>
			<span><%=user.getNom() %></span>
			<br>
			
			<label id="profilPrenomLabel">Prenom : </label>
			<span><%=user.getPrenom() %></span>
			<br>
			
			<label id="profilEmailLabel">Email : </label>
			<span><%=user.getEmail() %></span>
			<br>
			
			<label id="profilTelephoneLabel">Telephone : </label>
			<span><%=user.getTelephone() %></span>
			<br>
			
			<label id="profilRueLabel">Rue : </label>
			<span><%=user.getRue() %></span>
			<br>
			
			<label id="profilCodePostalLabel">Code Postal : </label>
			<span><%=user.getCodePostal() %></span>
			<br>
			
			<label id="profilVilleLabel">Ville : </label>
			<span><%=user.getVille() %></span>
			<br>
			
			<button class="btn btn-primary" id="profilModifButton" onclick="window.location.href ='<%=request.getContextPath()%>/ModificationProfil';">Modifier</button>
			
			</div>
			
		</div>
		
		
		
	</div>
	
	
	
	
</body>
</html>