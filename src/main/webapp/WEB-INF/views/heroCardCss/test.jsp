<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .my-margin {
            margin-top: 20px;
        }
    </style>
    <title>Heroes</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<%--<label for="myCharacter">Character</label>--%>

<%--<form action="/a/hero/test">--%>
    <%--<select id="myCharacter" name="chooseCharacter">&ndash;%&gt;--%>
        <%--<c:forEach var="myCharacter" items="${myCharacters}">--%>
            <%--<option value="${myCharacter.name}">${myCharacter.name}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>

<%--<button type="submit">--%>
<%--Check--%>
<%--</button>--%>
<%--</form>--%>

<%--<h1>${myCharacter.name}</h1>--%>

<div class="my-margin d-flex justify-content-center">


    <form:form class="row g-3 align-items-center" method="post" modelAttribute="hero">
    <div class="col-auto">
        <label for="name" class="col-form-label">Name</label>
    </div>
    <div class="col-auto">
        <form:input path="name" type="text" id="name" class="form-control"/>
    </div>
    <form:errors path="name"/>
        <select class="form-select"
                aria-label="Default select example"
                id="myCharacter"
                name="chooseCharacter">
                <%--<option selected></option>--%>

            <c:forEach var="location" items="${myCharacters}">
                <option value="${location.name}">${location.name}</option>
            </c:forEach>
        </select>
        <div class="col-auto">
            <input type="submit" class="btn btn-primary"/>
        </div>


    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Create</button>
    </div>

    </form:form>
</div>

<%--<div class="col-auto">--%>
    <%--<label for="myCharacter" class="col-form-label">Character</label>--%>
<%--</div>--%>

<%--<div class="col-auto">--%>
    <%--<form action="/${user}/hero/create">--%>
        <%----%>

    <%--</form>--%>



<%--</div>--%>

<div class="container my-margin my-table">

    <div class="row ">
        <div class="col-sm-12">
            <table class="table table-dark ">

                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <td>${myCharacter.name}</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="col">Hp</th>
                    <td>${myCharacter.hp}</td>
                </tr>
                <tr>
                    <th scope="col">Mp</th>
                    <td>${myCharacter.mp}</td>
                </tr>
                <tr>
                    <th scope="col">Power</th>
                    <td>${myCharacter.power}</td>
                </tr>

                <tr>
                    <th scope="col">Image</th>
                    <td class="wid-tab"><img class="image" src="/images/myCharacters/${myCharacter.image}"></td>
                </tr>


                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>