<%@page import="fr.eni.eniD2WM147.bo.Categorie"%>
<%@page import="fr.eni.eniD2WM147.bo.ArticleVendu"%>
<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage du détail de l'article</title>

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
				<a id="topMenu"
					href="<%=request.getContextPath()%>/CreationArticle">Vendre
					un article</a>
			</div>
			.
			<div class="p-2">
				<a id="topMenu" href="<%=request.getContextPath()%>/accueil">Enchères</a>
			</div>
		</div>
		<%
		}
		%>
	</header>


	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item"><a
				href="<%=request.getContextPath()%>/accueil">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Détails Vente</li>
		</ol>
	</nav>
	
	<%ArticleVendu av = (ArticleVendu) request.getAttribute("ArticleAAfficher");%>

<form method ="post" action="<%=request.getContextPath()%>/accueil">
	<div class="container">
		<h2 style="text-align: center;">Détail Vente</h2>

		<h4 style="text-align: center;"><%=av.getNom()%></h4>
		<br>
		<div id="detailsArticle">
			<label id="descriptionArt">Description : </label>
			<textarea readonly style="vertical-align: top"><%=av.getDescription()%></textarea>
			
			<br>
			<br>
			
			<label id="cateArt">Categorie :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<%=av.getCategorie().getLibelle()%></label>
			
			<br>
			
			<label id="bestOfferArt">Meilleure offre :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<%=av.getEnchere().getMontantEnchere()%> crédits par 
			<%=av.getEnchere().getUtilisateur().getPseudo()%> </label>
			
			<br>
			
			<label id="misePrixArt">Mise à prix :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<%=av.getPrixInitial()%> pts</label>
			
			<br> <label id="finEncArt">Fin de l'enchère :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<%=av.getFinEnchere()%></label>
			
			<br> <label id="retraitArt">Retrait :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 
			<%=av.getRetrait().getRue()%> <%=av.getRetrait().getCodePostal()%> 
			<%=av.getRetrait().getVille()%></label>
			
			<br> <label id="vendeurArt">Vendeur :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<%=av.getUtilisateur().getPseudo()%> </label>
			<form id="detailsVendeurBtn" action="<%=request.getContextPath()%>/Profil" method="post">
			<input type="hidden" name="vendeur" value="<%=av.getUtilisateur().getIdUtilisateur()%>">
			<input type="submit" value="Détails Vendeur" class="btn btn-primary">
			
			</form>
			
			<br> <label id="porpositionArt">Ma proposition : </label>
			<input type="number" min="1" max="1000" name="encherir">
			<input id="MakeAnEnchereButton" class="btn btn-primary" type="submit" name="encherirAff"
				value="Encherir">
		</div>
	</div>
	</form>

	<footer id="footer">
	<%@ include file="footer.html"%>
	</footer>

</body>
</html>