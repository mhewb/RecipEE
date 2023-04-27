<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RecipEE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<jsp:include page="/WEB-INF/nav/menu.jsp"></jsp:include>

<div class="container-md my-5 p-3">

<%--    ${!empty isLog ? '<p>You need to be logged!</p>' : ''}--%>

    <h1>Users List</h1>

    <a class="btn btn-success"
       role="button" href="/create-user">Create</a>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">

            <c:forEach var="user" items="${users}">

                <div class="col my-3">
                    <div class="card h-100">

                        <div class="card-body">

                            <h5 class="card-title"> ${user.firstName} ${user.lastName}</h5>

                            <a class="btn btn-primary btn-sm"
                               role="button" href="/edit-user?id=${user.id}">Edit</a>

                            <a class="btn btn-secondary btn-sm"
                               role="button" href="/delete-user?id=${user.id}">Delete</a>

                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>

</div>

</body>
</html>

