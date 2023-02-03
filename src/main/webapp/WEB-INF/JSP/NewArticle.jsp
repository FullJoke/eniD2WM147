<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertion de l'article</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
</head>
<body>


	<header>
		<%@ include file="Entete.html"%>



		<%
		Utilisateur Utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
		%>

		<%
		if (Utilisateur == null) {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/inscription">S'inscrire
				</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se
					Connecter</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="d-flex flex-row-reverse">
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/Profil">Mon
					Profil</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/CreationArticle">Vendre
					un article</a>
			</div>
			.
			<div class="p-2">
				<p id="topMenuUnusable">Enchères</p>
			</div>
		</div>
		<%
		}
		%>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>

</body>
</html>