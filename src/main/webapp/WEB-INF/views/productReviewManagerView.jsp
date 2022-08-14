<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Quản lý đánh giá</title>
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

    <header class="section-heading py-4">
      <h3 class="section-title">Quản lý đánh giá</h3>
    </header> <!-- section-heading.// -->

    <main class="table-responsive-xl mb-5">
      <table class="table table-bordered table-striped table-hover align-middle">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">ID</th>
          <th scope="col">Người dùng</th>
          <th scope="col">Ngày tạo</th>
          <th scope="col">Ngày cập nhật</th>
          <th scope="col">Số sao</th>
          <th scope="col">Sản phẩm</th>
          <th scope="col" style="width: 200px;">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="productReview" varStatus="loop" items="${requestScope.productReviews}">
          <tr>
            <th scope="row">${loop.index + 1}</th>
            <td>${productReview.id}</td>
            <td>
              <a href="${pageContext.request.contextPath}/admin/userManager/detail?id=${productReview.user.id}">
                  ${productReview.user.username}
              </a>
              (${productReview.user.fullname})
            </td>
            <td>${productReview.createdAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</td>
            <td>${productReview.updatedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</td>
            <td>
              <div class="ratting-star">
                <span class="rating-stars">
                  <c:forEach begin="1" end="5" step="1" var="i">
                    <i class="bi bi-star-fill ${i <= productReview.ratingScore ? 'active' : ''}"></i>
                  </c:forEach>
                </span>
                <span>(${productReview.ratingScore})</span>
              </div>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/product?id=${productReview.product.id}#review"
                 target="_blank">
                  ${productReview.product.name}
              </a>
            </td>
            <td class="text-center text-nowrap">
              <a class="btn btn-primary me-2"
                 href="${pageContext.request.contextPath}/admin/reviewManager/detail?id=${productReview.id}"
                 role="button">
                Xem
              </a>
              <div class="btn-group" role="group">
                <button type="submit"
                        class="btn ${productReview.isShow == 0 ? 'btn-secondary' : 'btn-success'}"
                        form="update-hide-${productReview.id}" ${productReview.isShow == 0 ? 'disabled' : ''}
                        onclick="return confirm('Bạn có muốn ẩn đánh giá này?')">
                  Ẩn
                </button>
                <button type="submit"
                        class="btn ${productReview.isShow == 1 ? 'btn-secondary' : 'btn-warning'}"
                        form="update-show-${productReview.id}" ${productReview.isShow == 1 ? 'disabled' : ''}
                        onclick="return confirm('Bạn có muốn hiện đánh giá này?')">
                  Hiện
                </button>
              </div>
              <form action="${pageContext.request.contextPath}/admin/reviewManager/update" method="post"
                    id="update-hide-${productReview.id}">
                <input type="hidden" name="id" value="${productReview.id}">
                <input type="hidden" name="action" value="HIDE">
              </form>
              <form action="${pageContext.request.contextPath}/admin/reviewManager/update" method="post"
                    id="update-show-${productReview.id}">
                <input type="hidden" name="id" value="${productReview.id}">
                <input type="hidden" name="action" value="SHOW">
              </form>
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
               href="${pageContext.request.contextPath}/admin/reviewManager?page=${requestScope.page - 1}">
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
                     href="${pageContext.request.contextPath}/admin/reviewManager?page=${i}">
                      ${i}
                  </a>
                </li>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/admin/reviewManager?page=${requestScope.page + 1}">
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
