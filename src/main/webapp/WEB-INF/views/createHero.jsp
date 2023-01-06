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
    <title>Heroes</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Hero</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">

            <div class="navbar-nav ms-auto">
                <div class="navbar-nav">
                    <a href="/hero/choose" class="nav-item nav-link">Back</a>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>

        </div>
    </div>
</nav>


<div class="my-margin">


    <form:form method="post"
               modelAttribute="hero">

        <div class="row">

            <div class="col d-flex justify-content-end">
                <label for="name"
                       class="col-form-label">Name</label>
            </div>
            <div class="col d-flex justify-content-start">

                <form:input path="name"
                            type="text"
                            id="name"
                            class="form-control"/>
            </div>


        </div>
        <div class="row">
            <div class="col d-flex justify-content-end">
                <label class="col-form-label">Choose Character</label>
            </div>
            <div class="col">

            </div>
        </div>

        <div class="row">

            <div class="col d-flex justify-content-center">

                <c:forEach var="myCharacter"
                           items="${myCharacters}">

                    <label class="my-card">
                        <input checked type="radio" name="chooseCharacter" id="movies" value="${myCharacter.name}">
                        <div class="card-content">
                            <img id="back" src="/images/locations/back.jpg" alt="back" srcset=""/>
                            <img id="hero" src="/images/characters/${myCharacter.image}" alt="movies" srcset=""/>
                            <div class="content">
                                <h4>${myCharacter.name}</h4>
                                <p>HP : ${myCharacter.hp}</p>
                                <p>MP : ${myCharacter.mp}</p>
                                <p>Power : ${myCharacter.power}</p>

                            </div>
                        </div>
                    </label>

                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col d-flex justify-content-center">


                <div class="row">
                    <div class="col">
                        <button type="submit"
                                class="btn btn-primary">
                            Create
                        </button>
                    </div>
                </div>


            </div>
        </div>


    </form:form>
</div>

</body>
</html>