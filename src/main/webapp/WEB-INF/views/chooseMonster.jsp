<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file="heroStyle/monsterChoose.css" %>

    </style>
    <title>Heroes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Choose Monster</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">

            <div class="navbar-nav ms-auto">
                <div class="navbar-nav">
                    <a href="/location/choose" class="nav-item nav-link">Back</a>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>

        </div>
    </div>
</nav>


<section>

    <div class="row ">
        <div class="col d-flex justify-content-center">

                <label class="col-form-label" style="font-size: 50px">Available monsters</label>

        </div>
    </div>
    <div class="row">


        <div class="col d-flex justify-content-center">

            <form action="/monster/choose" method="post" id="tree">
                <c:forEach var="monster"
                           items="${unlockedMonsters}">


                    <label class="my-card">
                        <input checked type="radio" name="chooseMonster" id="movies" value="${monster.name}">
                        <div class="card-content card-content-user-name">
                            <div class="yx">
                                    ${monster.name}
                            </div>
                            <div class="content">
                                <img class="test" src="/images/locations/back.jpg" alt="back" srcset=""/>
                                <img class="test1" src="/images/monsters/${monster.image}"
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

    <c:if test="${isEmpty != true}">
        <div class="row ">
            <div class="col d-flex justify-content-center">

                <label class="col-form-label" style="font-size: 50px">Unavailable monsters</label>

            </div>
        </div>

        <div class="row">

            <div class="col d-flex justify-content-center">

                <form action="/monster/choose" method="post" id="tree1">
                    <c:forEach var="monster"
                               items="${disableMonsters}">


                        <label class="my-card">
                                <%--                        <input checked type="radio" name="chooseMonster" id="movies1" value="${monster.name}">--%>
                            <div class="card-content card-content-user-name">
                                <div class="yx">
                                        ${monster.name}
                                </div>
                                <div class="content">
                                    <img class="test" src="/images/locations/back.jpg" alt="back" srcset=""/>
                                    <img class="test1" src="/images/monsters/${monster.image}"
                                         alt="back"
                                         srcset=""/>
                                </div>
                            </div>
                        </label>


                    </c:forEach>
                </form>
            </div>
        </div>

    </c:if>


</section>


</body>
</html>