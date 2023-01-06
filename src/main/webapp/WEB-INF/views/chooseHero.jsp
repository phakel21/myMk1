<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <style>
        <%@include file="heroStyle/heroChoose.css" %>

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

            <div class="navbar-nav ">
                <a href="/hero/create" class="nav-item nav-link">Create Hero</a>
            </div>
            <div class="navbar-nav ms-auto">
                <div class="navbar-nav">
                    <a href="/login" class="nav-item nav-link">Back</a>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>

        </div>
    </div>
</nav>

<section>


    <div class="row">

        <div class="col d-flex justify-content-center">

            <form action="/hero/choose" method="post" id="tree">
                <c:forEach var="hero"
                           items="${heroes}">


                    <label class="my-card">
                        <input checked type="radio" name="chooseHero" id="movies" value="${hero.name}">
                        <div class="card-content card-content-user-name">
                            <div class="yx">
                                    ${hero.name}
                            </div>
                            <div class="content">
                                <img id="test" src="/images/locations/back.jpg" alt="back" srcset=""/>
                                <img id="test1" src="/images/characters/${hero.myCharacter.image}"
                                     alt="back"
                                     srcset=""/>
                            </div>
                        </div>
                    </label>


                </c:forEach>
            </form>
        </div>
    </div>

    <div class="row">

        <div class="col lmm">
            <button form="tree"
                    type="submit"
                    class="btn btn-primary">
                Choose
            </button>
        </div>
    </div>


</section>


</body>
</html>