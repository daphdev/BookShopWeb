<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="section-header">
  <section class="header-main border-bottom">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-3 py-3">
          <a class="text-body" href="${pageContext.request.contextPath}/">
            <h3>Shop Bán Sách</h3>
          </a>
        </div> <!-- col.// -->
        <div class="col-lg-4 col-xl-5 ${empty sessionScope.currentUser ? 'mb-3 mb-lg-0' : ''}">
          <form action="${pageContext.request.contextPath}/search" method="post" class="search">
            <div class="input-group w-100">
              <input type="text"
                     class="form-control"
                     placeholder="Nhập từ khóa cần tìm ..."
                     name="q"
                     value="${requestScope.query}">
              <button class="btn btn-primary" type="submit">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </form>
        </div> <!-- col.// -->
        <div class="col-lg-5 col-xl-4">
          <c:if test="${not empty sessionScope.currentUser}">
            <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
              <li>
                <a href="${pageContext.request.contextPath}/user" class="nav-link text-body">
                  <i class="bi bi-person d-block text-center fs-3"></i>
                  Tài khoản
                </a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/order" class="nav-link text-body">
                  <i class="bi bi-list-check d-block text-center fs-3"></i>
                  Đơn hàng
                </a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/cart" class="nav-link text-body position-relative">
                  <span id="total-cart-items-quantity" class="position-absolute top-0 end-0 mt-2 badge rounded-pill bg-primary">
                    ...
                  </span>
                  <i class="bi bi-cart d-block text-center fs-3 position-relative"></i>
                  Giỏ hàng
                </a>
              </li>
            </ul>
          </c:if>
        </div> <!-- col.// -->
      </div> <!-- row.// -->
    </div> <!-- container.// -->
  </section> <!-- header-main.// -->
</header> <!-- section-header.// -->

<nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
             data-bs-toggle="dropdown" aria-expanded="false">
            <strong><i class="bi bi-list"></i> Danh mục sản phẩm</strong>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Sách giáo khoa</a></li>
            <li><a class="dropdown-item" href="#">Sách khoa học</a></li>
            <li><a class="dropdown-item" href="#">Truyện tranh</a></li>
            <li><a class="dropdown-item" href="#">Tiểu thuyết</a></li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li><a class="dropdown-item" href="#">Tất cả danh mục</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Sản phẩm mới</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Sản phẩm bán chạy</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Khuyến mãi</a>
        </li>
      </ul>
      <c:choose>
        <c:when test="${not empty sessionScope.currentUser}">
          <span>Xin chào <strong>${sessionScope.currentUser.fullname}</strong>!</span>
          <a class="btn btn-light ms-2" href="${pageContext.request.contextPath}/signout" role="button">
            Đăng xuất
          </a>
        </c:when>
        <c:otherwise>
          <a class="btn btn-light me-2" href="${pageContext.request.contextPath}/signup" role="button">
            Đăng ký
          </a>
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/signin" role="button">
            Đăng nhập
          </a>
        </c:otherwise>
      </c:choose>
    </div>
  </div> <!-- container.// -->
</nav> <!-- navbar-main.// -->
