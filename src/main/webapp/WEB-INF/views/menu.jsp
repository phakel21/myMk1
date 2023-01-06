<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file="heroStyle/heroCard.css" %>
    </style>
    <title>Lobby</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">Lobby</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">


            <div class="navbar-nav ms-auto">
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>

<div class="d-flex justify-content-center">
    <h1> You ${winOrLose}</h1>
</div>

<div class="d-flex justify-content-center">
    <form method="POST">
        <button class="btn btn-success btn-lg" name="choose" value="lobby">To Lobby</button>
    </form>
</div>
</body>
</html>