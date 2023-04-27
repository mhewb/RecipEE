<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">RecipEE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">

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


      </ul>
∂
      <span class="navbar-text">
        ${empty username ? 'Not connected': username}
      </span>

    </div>
  </div>
</nav>