<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Driver File Upload</title>

</head>
<body>
	<h1>driver upload</h1>

	<form method="POST" action="upload" enctype="multipart/form-data">
		driver(.jar) to upload: <input type="file" name="file"><br />
		Name: <input type="text" name="name"><br /> <br /> <input
			type="submit" value="Upload">
	</form>
</body>
</html>