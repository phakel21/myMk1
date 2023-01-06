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
    <title>Update Hero</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Update Hero</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">


            <div class="navbar-nav ms-auto">
                <div class="navbar-nav">
                    <a href="/admin/control/heroes" class="nav-item nav-link">Back</a>
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
        <form:form class="row" method="post"
                   action="/hero/update/name">
            <div class="col d-flex justify-content-end">
                <label for="name" class="col-form-label">Name</label>
            </div>
            <div class="col-auto" style="width: 200px;">
                <input name="updateName" type="text" id="name" class="form-control"/>
            </div>
            <div class="col justify-content-start">
                <button type="submit" class="btn btn-primary">Update</button>
            </div>
        </form:form>

    </div>




</div>


<div class="container my-margin ">

    <table class="table table-dark ">


        <tbody>

        <tr>
            <th scope="col">Name</th>
            <td>${hero.name}</td>
        </tr>
        <tr>
            <th scope="col">Character</th>
            <td><a href="/admin/control/myCharacter/${hero.myCharacter.name}/edit">
                ${hero.myCharacter.name}
            </a></td>
        </tr>
        <tr>
            <th scope="col">User</th>
            <td>${hero.myUser.login}</td>
        </tr>


        </tbody>
    </table>
</div>

<div class="my-margin d-flex justify-content-center">
    <div>
        <a href="/hero/${hero.name}/delete">
            <button class="btn btn-danger" type="submit">Delete</button>
        </a>
    </div>
</div>

</body>
</html>