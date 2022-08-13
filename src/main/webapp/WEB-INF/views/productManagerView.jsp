<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Quản lý sản phẩm</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <c:if test="${not empty sessionScope.successMessage}">
      <div class="alert alert-success mb-0 mt-4" role="alert">
          ${sessionScope.successMessage}
      </div>
    </c:if>
    <c:if test="${not empty sessionScope.errorMessage}">
      <div class="alert alert-danger mb-0 mt-4" role="alert">
          ${sessionScope.errorMessage}
      </div>
    </c:if>
    <c:remove var="successMessage" scope="session"/>
    <c:remove var="errorMessage" scope="session"/>

    <header class="section-heading py-4 d-flex justify-content-between">
      <h3 class="section-title">Quản lý sản phẩm</h3>
      <a class="btn btn-primary"
         href="${pageContext.request.contextPath}/admin/productManager/create"
         role="button"
         style="height: fit-content;">
        Thêm sản phẩm
      </a>
    </header> <!-- section-heading.// -->

    <main class="table-responsive-xl mb-5">
      <table class="table table-bordered table-striped table-hover align-middle">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">ID</th>
          <th scope="col">Hình</th>
          <th scope="col">Tên sản phẩm</th>
          <th scope="col">Giá gốc</th>
          <th scope="col">Khuyến mãi</th>
          <th scope="col">Giá bán</th>
          <th scope="col">Tồn kho</th>
          <th scope="col">Lượt mua</th>
          <th scope="col" style="width: 225px;">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" varStatus="loop" items="${requestScope.products}">
          <tr>
            <th scope="row">${loop.index + 1}</th>
            <td>${product.id}</td>
            <td class="text-center">
              <c:choose>
                <c:when test="${empty product.imageName}">
                  <img width="38" src="${pageContext.request.contextPath}/img/280px.png"
                       alt="280px.png">
                </c:when>
                <c:otherwise>
                  <img width="38" src="${pageContext.request.contextPath}/image/${product.imageName}"
                       alt="${product.imageName}">
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/product?id=${product.id}" target="_blank">${product.name}</a>
            </td>
            <td><fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫</td>
            <td><fmt:formatNumber pattern="#,##0" value="${product.discount}"/>%</td>
            <td>
              <c:choose>
                <c:when test="${product.discount == 0}">
                  <fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫
                </c:when>
                <c:otherwise>
                  <fmt:formatNumber pattern="#,##0" value="${product.price * (100 - product.discount) / 100}"/>₫
                </c:otherwise>
              </c:choose>
            </td>
            <td>${product.quantity}</td>
            <td>${product.totalBuy}</td>
            <td class="text-center text-nowrap">
              <a class="btn btn-primary me-2"
                 href="${pageContext.request.contextPath}/admin/productManager/detail?id=${product.id}"
                 role="button">
                Xem
              </a>
              <a class="btn btn-success me-2"
                 href="${pageContext.request.contextPath}/admin/productManager/update?id=${product.id}"
                 role="button">
                Sửa
              </a>
              <a class="btn btn-danger"
                 href="${pageContext.request.contextPath}/admin/productManager/delete?id=${product.id}"
                 role="button"
                 onclick="return confirm('Bạn có muốn xóa?')">
                Xóa
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </main>

    <c:if test="${requestScope.totalPages != 0}">
      <nav class="mt-3 mb-5">
        <ul class="pagination justify-content-center">
          <li class="page-item ${requestScope.page == 1 ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/admin/productManager?page=${requestScope.page - 1}">
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
                     href="${pageContext.request.contextPath}/admin/productManager?page=${i}">
                      ${i}
                  </a>
                </li>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/admin/productManager?page=${requestScope.page + 1}">
              Trang sau
            </a>
          </li>
        </ul>
      </nav>
    </c:if>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footerAdmin.jsp"/>
</body>

</html>
