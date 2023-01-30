<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enchère</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="/CSS/Style.css" rel="stylesheet">


</head>
<body>

	<div class="container">
		<h2>ENI - Enchères</h2>

		<form action="<%=request.getContextPath()%>/login" method="post">
			<label>identifiant : </label> <input type="text" name="email"> <br>
			<label>Mot de Passe : </label> <input type="password" name="password">
			<br> <input type="submit" value="Valider">
		</form>
	</div>

</body>
</html>