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
		<c:choose>
			<c:when test="${empty Utilisateur}">
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/inscription">S'inscrire
						</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu" href="${pageContext.request.contextPath}/login">
							Se Connecter</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="d-flex flex-row-reverse">
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
					</div>
					.
					<div class="p-2">
						<a id="topMenu" href="${pageContext.request.contextPath}/Profil">Mon
							Profil</a>
						<form action="${pageContext.request.contextPath}/Profil"
							method="post">
							<button name="vendeur" value="${Utilisateur.idUtilisateur}">
								Mon Profil</button>
						</form>
					</div>
					.
					<div class="p-2">
						<a id="topMenu"
							href="${pageContext.request.contextPath}/CreationArticle">Vendre
							un article</a>
					</div>
					.
					<div class="p-2">
						<p id="topMenuUnusable">Enchères</p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li id="bread" class="breadcrumb-item active" aria-current="page">Home</li>
		</ol>
	</nav>


   <form method="post" action="${pageContext.request.contextPath}/TelechargerImage"
        enctype="multipart/form-data">
      
        <label for="pictureFile">Choisir le fichier à télécharger : </label>
        <input type="file" id="pictureFile" name="pictureFile" accept="image/png, image/jpeg" onchange="PreviewImage();"/>
        <br>
        <input type="submit" value="Télécharger" />
    </form>
    <img id="uploadPreview" style="width: 100px; height: 100px;"/>

    <script type="text/javascript">
	    function PreviewImage() {
	        var oFReader = new FileReader();
	        oFReader.readAsDataURL(document.getElementById("pictureFile").files[0]);
	
	        oFReader.onload = function (oFREvent) {
	            document.getElementById("uploadPreview").src = oFREvent.target.result;
	        };
	    };
	</script>




</body>
</html>