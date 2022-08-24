<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file="../heroStyle/heroCard.css" %>
    </style>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="#" class="navbar-brand"></a>
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

<div class="my-margin">

    <div class="row">

        <div class="col">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <h3>${hero.name}</h3>
                </div>

            </div>

            <div class="row">
                <div class="col d-flex justify-content-center">
                    <label class="my-card">
                        <div class="card-content">
                            <img id="back" src="/images/locations/back.jpg" alt="back" srcset=""/>
                            <img id="hero" src="/images/myCharacters/${hero.myCharacterDTO.image}" alt="movies"
                                 srcset=""/>
                            <div class="content">
                                <h4>${hero.myCharacterDTO.name}</h4>
                                <p>HP : ${hero.myCharacterDTO.hp}</p>
                                <p>MP : ${hero.myCharacterDTO.mp}</p>
                                <p>Power : ${hero.myCharacterDTO.power}</p>

                            </div>
                        </div>
                    </label>
                </div>
            </div>

            <div class="row">
                <div class="col d-flex justify-content-center">

                    <a href="/${hero.myUserDTO.login}/hero/${hero.name}/location/choose">
                        <button class="btn btn-primary">
                            Choose Location
                        </button>
                    </a>

                </div>
            </div>

        </div>


    </div>


</div>
</body>
</html>