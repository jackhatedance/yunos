<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
<title>Driver File Upload</title>

</head>
<body>
	<h1>last operation result</h1>
	<p>${ operationResult }</p>

	<h2>upload</h2>

	<form method="POST" action="upload" enctype="multipart/form-data">
		driver(.jar) to upload: <input type="file" name="file"><br />
		<br /> <input type="submit" value="Upload">
	</form>


	<h2>delete</h2>
	<form method="POST" action="delete">
		driver ID: <input type="text" name="id"><br /> <br /> <input
			type="submit" value="Delete">
	</form>
</body>
</html>