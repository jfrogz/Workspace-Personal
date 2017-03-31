<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado Personas</title>
</head>
<body>
<h1>Listado de personas</h1>
<table>
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <ht>Email</ht>
    </tr>
    <c:forEach items="${personas}" var="persona">
        <tr>${persona.nombre}</tr>
        <tr>${persona.apePaterno}</tr>
        <tr>${persona.email}</tr>
    </c:forEach>
</table>
</body>
</html>
