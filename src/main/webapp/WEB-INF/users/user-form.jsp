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

    <h1>Register</h1>

    <form action="${pageContext.request.contextPath}/create-user" method="post">

      <label for="email" class="form-label">Email</label>
      <input type="text" class="form-control" id="email" name="email"
             value="${empty user.email ? '' : user.email }">

      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password"
             value="${empty user.password ? '' : user.password }">

      <label for="firstName" class="form-label">First Name</label>
      <input type="text" class="form-control" id="firstName" name="firstName"
             value="${empty user.firstName ? '' : user.firstName }">

      <label for="lastName" class="form-label">Last Name</label>
      <input type="text" class="form-control" id="lastName" name="lastName"
             value="${empty user.lastName ? '' : user.lastName }">

      <label for="avatarUrl" class="form-label">Avatar Url</label>
      <input type="text" class="form-control" id="avatarUrl" name="avatarUrl"
             value="${empty user.avatarUrl ? '' : user.avatarUrl }">

      <c:if test="${empty isEdit}">
      <button class="btn btn-primary my-3" type="submit">Create User</button>
      </c:if>

      <c:if test="${!empty isEdit}">
      <button class="btn btn-primary my-3" type="submit" name="id"
              value=${user.id} formaction="/edit-user?id=${user.id}">Edit</button>

      <a class="btn btn-danger" role="button" href="/delete-user?id=${user.id}">Delete</a>
      </c:if>

</div>

</body>
</html>

