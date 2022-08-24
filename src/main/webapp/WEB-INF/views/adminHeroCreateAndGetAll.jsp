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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Heroes</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">


            <div class="navbar-nav ms-auto">
                <div class="navbar-nav">
                    <a href="/admin/control" class="nav-item nav-link">Back</a>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout" class="nav-item nav-link">Logout</a>
                </sec:authorize>
            </div>


        </div>
    </div>
</nav>


<div class="col">

    <div class="my-margin">
        <form:form method="post" modelAttribute="hero">
            <div class="row">
                <div class="col d-flex justify-content-end">
                    <label for="name" class="col-form-label">Name</label>
                </div>
                <div class="col d-flex justify-content-start">
                    <form:input cssStyle="width: 200px" path="name" type="text" id="name" class="form-control"/>
                    <form:errors path="name" cssStyle="color: red; margin: 5px"/>
                </div>

            </div>


            <div class="my-margin">

                <div class="row">
                    <div class="col d-flex justify-content-end">
                        <label for="myCharacter" class="col-form-label">Character</label>
                    </div>
                    <div class="col d-flex justify-content-start" id="name">
                        <select class="form-select" aria-label="Default select example" id="myCharacter"
                                name="chooseCharacter" style="width: 200px">

                            <c:forEach var="myCharacter" items="${myCharacters}">
                                <option value="${myCharacter.name}">${myCharacter.name}</option>
                            </c:forEach>
                        </select>

                    </div>

                </div>
            </div>

            <div class="my-margin">

                <div class="row">
                    <div class="col d-flex justify-content-end">
                        <label for="myUser" class="col-form-label">User</label>
                    </div>
                    <div class="col d-flex justify-content-start">
                        <select class="form-select" aria-label="Default select example" id="myUser"
                                name="chooseUser" style="width: 200px">

                            <c:forEach var="myUser" items="${myUsers}">
                                <option value="${myUser.login}">${myUser.login}</option>
                            </c:forEach>
                        </select>

                    </div>

                </div>
            </div>
            <div class="my-margin">

                <div class="row">
                    <div class="col d-flex justify-content-center">
                        <button type="submit" class="btn btn-success">Create</button>
                    </div>

                </div>
            </div>
        </form:form>
    </div>


</div>


<div class="container my-margin">

    <table class="table table-dark">

        <thead>
        <tr>

            <th scope="col">Name</th>
            <th scope="col">Character</th>
            <th scope="col">User</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>

        </tr>
        </thead>

        <tbody>
        <c:forEach var="hero" items="${heroes}">

            <tr>
                <td>${hero.name}</td>

                <td><a href="/admin/control/hero/${hero.myCharacterDTO.name}/edit">
                        ${hero.myCharacterDTO.name}
                </a></td>

                <td>${hero.myUserDTO.login}</td>

                <td>

                    <a href="/admin/control/hero/${hero.name}/update">
                        <button class="btn btn-primary" type="submit">Edit</button>
                    </a>

                </td>

                <td>

                    <a href="/admin/control/hero/${hero.name}/delete">
                        <button class="btn btn-danger" type="submit">Delete</button>
                    </a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>


</body>
</html>