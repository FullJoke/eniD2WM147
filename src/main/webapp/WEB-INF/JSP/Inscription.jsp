<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enchère</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="CSS/Style.css" rel="stylesheet">
<body></body>

<header>
	<%@ include file="Entete.html"%>
	<div class="d-flex flex-row-reverse">
		<div class="p-2">
			<a id="topMenu" href="<%=request.getContextPath()%>/login"> Se Connecter</a>
		</div>
	</div>
</header>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a
			href="<%=request.getContextPath()%>/accueil">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Inscription</li>
	</ol>
</nav>

<div class="container">
	<div class="row justify-content-md-center">

		<h2>Inscription</h2>

	</div>

</div>


<body>

</body>
</html>