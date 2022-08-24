<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file="heroStyle/locationChoose.css" %>

    </style>
    <%--<style>--%>
        <%--/*.my-margin {*/--%>
        <%--/*margin-top: 20px;*/--%>
        <%--/*}*/--%>
        <%--.image {--%>
            <%--width: 300px;--%>
            <%--height: 400px;--%>
        <%--}--%>

        <%--.my-margin {--%>
            <%--margin-top: 20px;--%>
            <%--margin-left: 20px;--%>
            <%--margin-right: 20px;--%>
        <%--}--%>

    <%--</style>--%>
    <title>Heroes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Location</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ">
                <a href="/${userName}/hero/${heroName}/lobby" class="nav-item nav-link">Back</a>
            </div>
            <div class="navbar-nav ms-auto">
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>

        </div>
    </div>
</nav>

<%--<div class="my-margin d-flex justify-content-center">--%>

<%--<form:form method="post" class="row g-3 align-items-center">--%>

<%--<div class="col-auto">--%>
<%--<label for="control" class="col-form-label">Location</label>--%>
<%--</div>--%>

<%--<div class="col-auto">--%>
<%--<select class="form-select" aria-label="Default select example" id="control" name="chooseLocation">--%>
<%--<option selected></option>--%>
<%--<c:forEach var="location" items="${locations}">--%>
<%--<option value="${location.name}">${location.name}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<%--</div>--%>

<%--<div class="col-auto">--%>
<%--<button type="submit" class="btn btn-primary">Choose</button>--%>
<%--</div>--%>

<%--</form:form>--%>

<%--</div>--%>
<%--<form action="/${userName}/hero/${heroName}/location/choose" method="post">--%>


    <%--<div class="my-margin">--%>


        <%--<div class="row">--%>
            <%--<div class="col d-flex justify-content-center"--%>
                 <%--style="margin-bottom: 20px">--%>
                <%--<label for="test">--%>
                    <%--<h3>--%>
                        <%--Choose Location--%>
                    <%--</h3>--%>
                <%--</label>--%>
            <%--</div>--%>
        <%--</div>--%>


        <%--<div class="row">--%>

            <%--<div class="col">--%>
                <%--<c:forEach var="location"--%>
                           <%--items="${locations}">--%>

                    <%--<div class="row">--%>
                        <%--<div class="col d-flex justify-content-center">--%>
                            <%--<h3>${location.name}</h3>--%>
                        <%--</div>--%>


                        <%--<div class="col">--%>


                            <%--<div class="row "--%>
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
                                                 <%--src="/images/locations/${location.image}">--%>
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
                                           <%--name="chooseLocation"--%>
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

            <form action="/${userName}/hero/${heroName}/location/choose" method="post" id="tree">
                <c:forEach var="location"
                           items="${locations}">


                    <label class="my-card">
                        <input checked type="radio" name="chooseLocation" id="movies" value="${location.name}">
                        <div class="card-content">
                            <div class="yx">
                                    ${location.name}
                            </div>
                            <div class="content">
                                <%--<img id="test" src="/images/locations/back.jpg" alt="back" srcset=""/>--%>
                                <img id="test" src="/images/locations/${location.image}"
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