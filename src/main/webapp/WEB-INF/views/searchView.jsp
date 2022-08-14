<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Kết quả tìm kiếm cho "${requestScope.query}"</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-content mb-5">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">
        Kết quả tìm kiếm cho "${requestScope.query}": ${requestScope.totalProducts} sản phẩm
      </h3>
    </header> <!-- section-heading.// -->

    <div class="row item-grid">
      <c:forEach var="product" items="${requestScope.products}">
        <div class="col-xl-3 col-lg-4 col-md-6">
          <div class="card p-3 mb-4">
            <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="img-wrap text-center">
              <c:choose>
                <c:when test="${empty product.imageName}">
                  <img width="200"
                       height="200"
                       class="img-fluid"
                       src="${pageContext.request.contextPath}/img/280px.png"
                       alt="280px.png">
                </c:when>
                <c:otherwise>
                  <img width="200"
                       height="200"
                       class="img-fluid"
                       src="${pageContext.request.contextPath}/image/${product.imageName}"
                       alt="${product.imageName}">
                </c:otherwise>
              </c:choose>
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
                    <span class="ms-2 badge bg-info">
                      -<fmt:formatNumber pattern="#,##0" value="${product.discount}"/>%
                    </span>
                  </c:otherwise>
                </c:choose>
              </div>
            </figcaption>
          </div>
        </div> <!-- col.// -->
      </c:forEach>
    </div> <!-- row.// -->

    <c:if test="${requestScope.totalPages != 0}">
      <nav class="mt-4">
        <ul class="pagination">
          <li class="page-item ${requestScope.page == 1 ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${requestScope.page - 1}">
              Trang trước
            </a>
          </li>

          <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
            <c:choose>
              <c:when test="${requestScope.page == i}">
                <li class="page-item active">
                  <a class="page-link">${i}</a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="page-item">
                  <a class="page-link"
                     href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${i}">
                      ${i}
                  </a>
                </li>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/search?q=${requestScope.query}&page=${requestScope.page + 1}">
              Trang sau
            </a>
          </li>
        </ul>
      </nav>
    </c:if>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
