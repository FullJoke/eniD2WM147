<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enchere</title>
</head>
<body>

	<h1>ENI - Enchères</h1>

	<form action="<%=request.getContextPath()%>/login" method="post">
		<label>email : </label>
		<input type="text" name="email">
		<br>
		<label>Mot de Passe : </label>
		<input type="password" name="password">
	</form>

</body>
</html>