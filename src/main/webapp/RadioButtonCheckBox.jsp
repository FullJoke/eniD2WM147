<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertion Button checkbox</title>
</head>
<body>

	<form method="post" action="">
		<div>
			<input type="radio" id="achat" name="radioButton" value="Achats">
			<label for="Achats">Achats</label>
		</div>

		<br> <label for="enchères ouvertes">enchères ouvertes</label> <input
			type="checkbox" name="checkbox"> <br> <label
			for="mes enchères"> mes enchères</label> <input type="checkbox"
			name="checkbox"> <br> <label
			for=" mes enchères remportées">mes enchères remportées</label> <input
			type="checkbox" name="checkbox">

		<div>
			<input type="radio" id="ventes" name="radioButton" value="Mes ventes">
			<label for="Mes Ventes">Mes Ventes</label>
		</div>
		<br> <label for="mes ventes en cours">mes ventes en cours</label>
		<input type="checkbox" name="checkbox"> <br> <label
			for="ventes non debutées">ventes non debutées</label> <input
			type="checkbox" name="checkbox"> <br> <label
			for="ventes terminées">enchères ouvertes</label> <input
			type="checkbox" name="checkbox">
	</form>

</body>
</html>