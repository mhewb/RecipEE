<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">RecipEE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

        <%-- Recipes--%>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Recipes
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/recipes-list">Recipes List</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/create-recipe">Create Recipe</a></li>
          </ul>
        </li>

        <%-- Categories--%>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Categories
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/categories-list">Categories List</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/create-category">Create Category</a></li>
          </ul>
        </li>

        <%-- TAGS--%>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Tags
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tags-list">Tags List</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/create-tag">Create Tag</a></li>
          </ul>
        </li>

      </ul>

      <c:choose>
        <c:when test="${empty sessionScope.loggedUser}">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/login">Login</a>
        </c:when>

        <c:when test="${! empty sessionScope.loggedUser}">
          <a class="btn btn-danger" href="${pageContext.request.contextPath}/random">Not so Random Recipe ?</a>
          <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:when>
      </c:choose>

      <form class="d-flex" role="search" action="${pageContext.request.contextPath}/search" method="get">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="query">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>

    </div>
  </div>
</nav>