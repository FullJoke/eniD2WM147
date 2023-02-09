<%@page import="fr.eni.eniD2WM147.bo.Utilisateur"%>
<%@page import="fr.eni.eniD2WM147.bo.Image"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Téléchargement Image</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/Style.css" rel="stylesheet">
</head>
<body>

	<header>
		<%@ include file="Entete.html"%>
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
				<p id="topMenuUnusable">Vendre un article</p>
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
			<li class="breadcrumb-item active" aria-current="page">Création
				de vente</li>
		</ol>
	</nav>
	<form method="post"
		action="${pageContext.request.contextPath}/CreationArticle"
		enctype="multipart/form-data">





		<label for="pictureFile">Choisir le fichier à télécharger : </label> <input
			type="file" id="pictureFile" name="pictureFile"
			accept="image/png, image.jpeg" onchange="PreviewImage();" /> <br>
		<input type="submit" value="<%=request.getContextPath()%>/fichier image"  name="telechargerImage" />
	</form>
	<img id="uploadPreview" style="width: 100px; height: 100px;" />

	<script type="text/javascript">
		function PreviewImage() {
			var oFReader = new FileReader();
			oFReader
					.readAsDataURL(document.getElementById("pictureFile").files[0]);

			oFReader.onload = function(oFREvent) {
				document.getElementById("uploadPreview").src = oFREvent.target.result;
			};
		};
	</script>




</body>
</html>