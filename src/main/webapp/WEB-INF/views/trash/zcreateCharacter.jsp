<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <title>Hello Admin</title>
</head>
<body>
<%--<a href="/admin/location">location administration</a>--%>
<%--<a href="/admin/hero">Hero administration</a>--%>

<%--<div class="row">--%>
    <%--<div  class="col-sm-12">--%>
        <%--<table class="table table-bordered">--%>
            <%--<td>${myCharacter.name}</td>--%>
        <%--</table>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <form:form class="form-horizontal" method="POST"
                        modelAttribute="myCharacterDTO" >
                <div class="form-group">
                    <label for="name" class="control-label col-sm-2">Name:</label>
                    <div class="col-sm-10">
                        <form:input type="text" path="name" id="name" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="hp" class="control-label col-sm-2">HP:</label>
                    <div class="col-sm-10">
                        <form:input type="text" path="hp" id="hp" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="control-label col-sm-2">MP:</label>
                    <div class="col-sm-10">
                        <form:input type="text" path="mp" id="mp" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="control-label col-sm-2">Power:</label>
                    <div class="col-sm-10">
                        <form:input type="text" path="power" id="power" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <button class="btn btn-success" type="submit">Create</button>
                    </div>
                </div>

            </form:form>

        </div>

    </div>

</div>

</body>
</html>