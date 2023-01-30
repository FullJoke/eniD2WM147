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
		<div class="justify-content center">
			<h2>Login</h2>

			<form action="<%=request.getContextPath()%>/login" method="post">
				<label>Identifiant : </label> <input type="text" name="id">
				<br> <label>Mot de Passe : </label> <input type="password"
					name="mdp"> <br> <input type="submit"
					value="Valider">
			</form>
		</div>
	</div>

</body>
</html>