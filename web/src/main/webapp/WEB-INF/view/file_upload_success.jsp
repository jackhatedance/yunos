<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %> 
<html>
<head>
    <title>Spring MVC Multiple File Upload</title>
</head>
<body>
    <h1>Spring Multiple File Upload example</h1>
    <p>Following files are uploaded successfully.</p>
    <ol>
        <c:forEach items="${files}" var="file">
            <li>${file}</li>
        </c:forEach>
    </ol>
</body>
</html>