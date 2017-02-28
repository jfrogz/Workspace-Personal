<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Blog de Fernando Robles</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/static/css/estilos.css">
</head>
<body>
<section class="jumbotron jumbotron-frog">
    <div class="container">
        <h1 class="titulo-blog">${nombre}</h1>
        <p>${carrera}</p>
    </div>
</section>

<script src="/static/js/jquery-1.12.4.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

</body>
</html>