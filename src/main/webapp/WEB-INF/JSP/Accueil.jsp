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

		<div class="entete">
			<a href="http://www.google.fr">S'inscrire</a> / <a
				href="<%=request.getContextPath()%>/login">Se Connecter</a>
		</div>



	</header>

	<div class="container">
		<h2>Liste des enchères</h2>
	</div>

</body>
</html>