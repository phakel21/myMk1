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
            width: 100px;
            height: 50px;
        }

    </style>

    <title>Locations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="" class="navbar-brand">Locations</a>
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
        <form:form method="post" modelAttribute="location"
                   enctype="multipart/form-data">

            <div class="row">


                <div class="col d-flex justify-content-end">
                    <label for="name"
                           class="col-form-label">Name</label>
                </div>


                <div class="col d-flex justify-content-start">

                    <form:input cssStyle="width: 200px"
                                path="name"
                                type="text"
                                id="name"
                                class="form-control"/>
                </div>


            </div>


            <div class="my-margin">
                <div class="row">


                    <div class="col d-flex justify-content-end">
                        <label for="file"
                               class="col-form-label">Image</label>
                    </div>


                    <div class="col d-flex justify-content-start">
                        <input style="width: 200px;" type="file" name="file" id="file" class="form-control" accept="image/*"/>
                    </div>


                </div>
            </div>


            <div class="my-margin">
                <div class="row">


                    <div class="col d-flex justify-content-center ">
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
            <th scope="col">Image</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>

        </tr>
        </thead>

        <tbody>
        <c:forEach var="location" items="${locations}">

            <tr>
                <td>${location.name}</td>

                <td>
                    <div>
                        <img alt="not found image" class="image" src="/images/locations/${location.image}">
                    </div>
                </td>

                <td>
                    <div>
                        <a href="/admin/control/location/${location.name}/update">
                            <button class="btn btn-primary" type="submit">Edit</button>
                        </a>
                    </div>
                </td>

                <td>
                    <div>
                        <a href="/admin/control/location/${location.name}/delete">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </a>
                    </div>
                </td>

            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>