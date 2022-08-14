<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Quản lý thể loại</title>
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
      <h3 class="section-title">Quản lý thể loại</h3>
      <a class="btn btn-primary"
         href="${pageContext.request.contextPath}/admin/categoryManager/create"
         role="button"
         style="height: fit-content;">
        Thêm thể loại
      </a>
    </header> <!-- section-heading.// -->

    <main class="table-responsive-xl mb-5">
      <table class="table table-bordered table-striped table-hover align-middle">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">ID</th>
          <th scope="col">Hình</th>
          <th scope="col">Tên thể loại</th>
          <th scope="col" style="width: 225px;">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" varStatus="loop" items="${requestScope.categories}">
          <tr>
            <th scope="row">${loop.index + 1}</th>
            <td>${category.id}</td>
            <td class="text-center">
              <c:choose>
                <c:when test="${empty category.imageName}">
                  <img width="38" src="${pageContext.request.contextPath}/img/50px.png"
                       alt="50px.png">
                </c:when>
                <c:otherwise>
                  <img width="38" src="${pageContext.request.contextPath}/image/${category.imageName}"
                       alt="${category.imageName}">
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/category?id=${category.id}"
                 target="_blank">${category.name}</a>
            </td>
            <td class="text-center text-nowrap">
              <a class="btn btn-primary me-2"
                 href="${pageContext.request.contextPath}/admin/categoryManager/detail?id=${category.id}"
                 role="button">
                Xem
              </a>
              <a class="btn btn-success me-2"
                 href="${pageContext.request.contextPath}/admin/categoryManager/update?id=${category.id}"
                 role="button">
                Sửa
              </a>
              <a class="btn btn-danger"
                 href="${pageContext.request.contextPath}/admin/categoryManager/delete?id=${category.id}"
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
               href="${pageContext.request.contextPath}/admin/categoryManager?page=${requestScope.page - 1}">
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
                     href="${pageContext.request.contextPath}/admin/categoryManager?page=${i}">
                      ${i}
                  </a>
                </li>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/admin/categoryManager?page=${requestScope.page + 1}">
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
