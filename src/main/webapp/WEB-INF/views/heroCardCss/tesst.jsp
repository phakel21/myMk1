<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <title>Heroes</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <style><%@include file="../heroStyle/heroCard.css"%></style>
    <%--<link rel="stylesheet" href="<c:url value="heroCard.css"/>" type="text/css">--%>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<section>

    <div class="container">
        <%--<c:forEach var="myCharacter"--%>
                   <%--items="${myCharacters}">--%>
            <label class="card">
                <input checked type="radio" name="category" id="movies">
                <div class="card-content">
                    <img id="hero" src="/images/myCharacters/5b5fd2bb-acf6-418b-8c64-cc812479f159.subzero_to_left.png" alt="movies" srcset=""/>
                    <div class="content">
                        <h4>Name : Scorpion</h4>
                        <p>HP : 100</p>
                        <p>MP : 100</p>
                        <p>Power : 100</p>

                    </div>
                </div>
            </label>


        <%--</c:forEach>--%>
    </div>
</section>
</body>
</html>