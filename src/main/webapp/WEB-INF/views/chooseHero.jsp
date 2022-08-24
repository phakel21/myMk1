<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <%--<style>--%>
    <%--.image {--%>
    <%--width: 300px;--%>
    <%--height: 400px;--%>
    <%--}--%>

    <%--.my-margin {--%>
    <%--margin-top: 20px;--%>
    <%--margin-left: 20px;--%>
    <%--margin-right: 20px;--%>
    <%--}--%>
    <style>
        <%@include file="heroStyle/heroChoose.css" %>

    </style>
    <title>Heroes</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
                    <a href="/${user}/hero/choose" class="nav-item nav-link">Back</a>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>

        </div>
    </div>
</nav>

<%--<form action="/${userName}/hero/choose" method="post">--%>


<%--<div class="my-margin">--%>


<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-center"--%>
<%--style="margin-bottom: 20px">--%>
<%--<label for="test">--%>
<%--<h3>--%>
<%--Choose Hero--%>
<%--</h3>--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="row">--%>

<%--<div class="col">--%>
<%--<c:forEach var="location"--%>
<%--items="${heroes}">--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-center">--%>
<%--<h3>${location.name}</h3>--%>
<%--</div>--%>


<%--<div class="col">--%>


<%--<div class="row"--%>
<%--style="--%>
<%--width: 400px;--%>
<%--border-radius: 25px;--%>
<%--border: 4px solid black">--%>

<%--<div class="col">--%>


<%--<div class="row"--%>
<%--&lt;%&ndash;style="background-color:#d3d3d3;&ndash;%&gt;--%>
<%--&lt;%&ndash;border-top-left-radius: 22px;&ndash;%&gt;--%>
<%--&lt;%&ndash;border-top-right-radius: 22px">&ndash;%&gt;>--%>
<%--<div class="col d-flex justify-content-center"--%>
<%--style="margin-bottom: 10px;--%>
<%--margin-top: 10px">--%>
<%--<img class="image"--%>
<%--src="/images/myCharacters/${location.myCharacterDTO.image}">--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-center"--%>
<%--style="border-bottom: 4px solid black">--%>
<%--<h1>--%>
<%--<b>--%>
<%--${location.myCharacterDTO.name}--%>
<%--</b>--%>
<%--</h1>--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="row" style="margin-bottom: 10px">--%>

<%--<div class="col">--%>
<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-end">--%>
<%--HP--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-end">--%>
<%--MP--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-end">--%>
<%--Power--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="col">--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-start">--%>
<%--${location.myCharacterDTO.hp}--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-start">--%>
<%--${location.myCharacterDTO.mp}--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
<%--<div class="col d-flex justify-content-start">--%>
<%--${location.myCharacterDTO.power}--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>


<%--</div>--%>


<%--</div>--%>


<%--<div class="row" style="margin-bottom: 20px; margin-top: 20px">--%>
<%--<div class="col d-flex justify-content-center">--%>
<%--<input checked--%>
<%--style="border: 2px solid black"--%>
<%--id="test"--%>
<%--type="radio"--%>
<%--name="chooseHero"--%>
<%--class="form-check-input"--%>
<%--value="${location.name}"/>--%>

<%--</div>--%>
<%--</div>--%>

<%--</div>--%>

<%--</div>--%>


<%--&lt;%&ndash;<div class="row "&ndash;%&gt;--%>
<%--&lt;%&ndash;style="margin-bottom: 20px">&ndash;%&gt;--%>

<%--&lt;%&ndash;</div>&ndash;%&gt;--%>


<%--</c:forEach>--%>
<%--</div>--%>

<%--<div class="col d-flex justify-content-center">--%>


<%--<div class="row">--%>
<%--<div class="col">--%>
<%--<button type="submit"--%>
<%--class="btn btn-primary">--%>
<%--Choose--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>


<%--</div>--%>
<%--</div>--%>


<%--</div>--%>
<%--</form>--%>

<section>
    <%--<div class="container">--%>

            <div class="row">

                <div class="col d-flex justify-content-center">

                    <form action="/${userName}/hero/choose" method="post" id="tree">
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
                                    <img id="test1" src="/images/myCharacters/${hero.myCharacterDTO.image}"
                                         alt="back"
                                         srcset=""/>
                                </div>
                            </div>
                        </label>


                    </c:forEach>
                    </form>
                </div>
            </div>

    <%--</div>--%>

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