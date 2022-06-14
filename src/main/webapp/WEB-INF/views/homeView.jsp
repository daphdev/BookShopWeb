<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Trang chủ</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-content mb-2">
  <div class="container">
    <header class="section-heading py-4 d-flex justify-content-between">
      <h3 class="section-title">Danh mục sản phẩm</h3>
      <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
    </header> <!-- section-heading.// -->
    <div class="row item-grid">
      <c:forEach var="category" items="${requestScope.categories}">
        <div class="col-lg-3 col-md-6">
          <div class="card mb-4">
            <div class="card-body">
              <a href="${pageContext.request.contextPath}/category?id=${category.id}" class="stretched-link">
                <div class="d-flex align-items-center">
                  <img src="${pageContext.request.contextPath}/image/${category.imageName}" alt="${category.name}">
                  <span class="category-title ms-3">${category.name}</span>
                </div>
              </a>
            </div>
          </div>
        </div> <!-- col.// -->
      </c:forEach>
    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<section class="section-content mb-5">
  <div class="container">
    <header class="section-heading py-4 d-flex justify-content-between">
      <h3 class="section-title">Sản phẩm mới nhất</h3>
      <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
    </header> <!-- section-heading.// -->
    <div class="row item-grid">
      <c:forEach var="product" items="${requestScope.products}">
        <div class="col-lg-3 col-md-6">
          <div class="card p-3 mb-4">
            <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="img-wrap text-center">
              <img class="img-fluid" src="${pageContext.request.contextPath}/image/${product.imageName}"
                   width="200" height="200" alt="${product.name}">
            </a>
            <figcaption class="info-wrap mt-2">
              <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="title">${product.name}</a>
              <div>
                <c:choose>
                  <c:when test="${product.discount == 0}">
                    <span class="price mt-1 fw-bold">
                      <fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫
                    </span>
                  </c:when>
                  <c:otherwise>
                    <span class="price mt-1 fw-bold">
                      <fmt:formatNumber
                              pattern="#,##0"
                              value="${product.price * (100 - product.discount) / 100}"/>₫
                    </span>
                    <span class="ms-2 text-muted text-decoration-line-through">
                      <fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫
                    </span>
                  </c:otherwise>
                </c:choose>
              </div>
            </figcaption>
          </div>
        </div> <!-- col.// -->
      </c:forEach>
    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
