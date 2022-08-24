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

        .image {
            width: 50px;
            height: 100px;
        }

        .err {
            color: red;
            margin: 5px
        }

    </style>
    <title>Characters</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Characters</a>
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
        <form:form method="post" modelAttribute="myCharacter"
                   enctype="multipart/form-data">

            <div class="row">
                <div class="col d-flex justify-content-end">
                    <label for="name" class="col-form-label">Name</label>
                </div>
                <div class="col d-flex justify-content-start">
                    <form:input cssStyle="width: 200px" path="name" type="text" id="name" class="form-control"/>
                    <form:errors path="name" cssClass="err"/>
                </div>

            </div>
            <div class="my-margin">
                <div class="row">
                    <div class="col d-flex justify-content-end">
                        <label for="hp" class="col-form-label">HP</label>
                    </div>
                    <div class="col d-flex justify-content-start">
                        <form:input cssStyle="width: 200px" path="hp" type="text" id="hp" class="form-control"/>
                        <form:errors path="hp" cssClass="err"/>
                    </div>

                </div>
            </div>

            <div class="my-margin">
                <div class="row">
                    <div class="col d-flex justify-content-end">
                        <label for="mp" class="col-form-label">MP</label>
                    </div>
                    <div class="col d-flex justify-content-start">
                        <form:input cssStyle="width: 200px" path="mp" type="text" id="mp" class="form-control"/>
                        <form:errors path="mp" cssClass="err"/>
                    </div>

                </div>
            </div>

            <div class="my-margin">
                <div class="row">
                    <div class="col d-flex justify-content-end">
                        <label for="power" class="col-form-label">Power</label>
                    </div>
                    <div class="col d-flex justify-content-start">
                        <form:input cssStyle="width: 200px" path="power" type="text" id="power" class="form-control"/>
                        <form:errors path="power" cssClass="err"/>
                    </div>

                </div>
            </div>


            <div class="my-margin">
                <div class="row">


                    <div class="col d-flex justify-content-end">
                        <label for="file"
                               class="col-form-label">Image</label>
                    </div>


                    <div class="col d-flex justify-content-start">
                        <input style="width: 200px;" type="file" name="file" id="file" class="form-control"
                               accept="image/*"/>

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

<div class="container my-margin ">

    <table class="table table-dark ">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Hp</th>
            <th scope="col">Mp</th>
            <th scope="col">Power</th>
            <th scope="col">Image</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="myCharacter" items="${myCharacters}">
            <tr>
                <td>${myCharacter.name}</td>
                <td>${myCharacter.hp}</td>
                <td>${myCharacter.mp}</td>
                <td>${myCharacter.power}</td>
                <td><img alt="not found image" class="image" src="/images/characters/${myCharacter.image}"></td>
                <td>

                    <a href="/admin/control/character/${myCharacter.name}/update">
                        <button class="btn btn-primary" type="submit">Edit</button>
                    </a>

                </td>
                <td>

                    <a href="/admin/control/character/${myCharacter.name}/delete">
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