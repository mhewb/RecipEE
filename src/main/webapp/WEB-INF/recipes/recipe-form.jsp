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

    <h1>Recipe Creation</h1>

    <form action="${pageContext.request.contextPath}/create-recipe" method="post">

      <label for="name">Name</label>
      <input type="text"
             id="name"
             name="name"
             value="${empty isEdit ? 'A Recipe Name' : recipe.name}"
             class="form-control form-control-sm">

      <label for="ingredients" class="form-control-sm">Ingrédients</label>
      <textarea id="ingredients" class="form-control" name="ingredients" rows="7">${empty isEdit ? 'Some Ingredients' : recipe.ingredients}</textarea>

      <select class="form-select" name="category">

        <c:if test="${!empty isEdit}">
          <option value="${currentCategory.id}" selected>${currentCategory.name}</option>
        </c:if>

        <c:if test="${empty isEdit}">
          <option selected>Please select a Category</option>
        </c:if>

        <c:forEach var="cat" items="${categories}">
          <option value="${cat.id}">${cat.name}</option>
        </c:forEach>

      </select>

      <label for="preparationTime">Temps de préparation (en min)</label>
      <input type="text"
             id="preparationTime"
             name="preparationTime"
             value="${empty isEdit ? '5' : recipe.preparationTime}"
             class="form-control form-control-sm">

      <label for="instructions" class="form-control-sm">Instructions</label>
      <textarea id="instructions" class="form-control" name="instructions" rows="10">${empty isEdit ? 'Some instructions' : recipe.instructions}</textarea>

      <label for="cookingTime">Temps de cuisson (en min)</label>
      <input type="text"
             id="cookingTime"
             name="cookingTime"
             value="${empty isEdit ? '10' : recipe.cookingTime}"
             class="form-control form-control-sm">


      <c:if test="${empty isEdit}">
      <button class="btn btn-primary my-3" type="submit">Create Recipe</button>
      </c:if>

      <c:if test="${!empty isEdit}">
      <button class="btn btn-primary my-3" type="submit" name="id"
              value=${recipe.id} formaction="/edit-recipe?id=${recipe.id}">Edit</button>

      <a class="btn btn-danger" role="button" href="/delete-recipe?id=${recipe.id}">Delete</a>
      </c:if>

</div>

</body>
</html>

