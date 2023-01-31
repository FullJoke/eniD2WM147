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
	</header>

	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-2">
				<h2>Login</h2>
			</div>
		</div>
		<div class="row justify-content-lg-center">
			<form action="<%=request.getContextPath()%>/login" method="post">
				<label id="loginIdLabel">Identifiant : </label> <input id="loginId"
					type="text" name="id"> <br> <label>Mot de Passe : </label>
					<input id="loginPassword" type="password" name="mdp">
				<br> <input id="loginOK" type="submit" value="Valider">
			</form>
		</div>

	</div>

</body>
</html>