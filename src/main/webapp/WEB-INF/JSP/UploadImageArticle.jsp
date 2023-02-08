<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Téléchargement Image</title>
</head>
<body>



	<input type="file" id="pictureFile" name="pictureFile"
		accept="image/png, image/jpeg" onchange="PreviewImage();"/>
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