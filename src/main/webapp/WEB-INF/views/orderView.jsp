<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Đơn hàng</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
  <div class="container">
    <h2 class="title-page">Đơn hàng</h2>
  </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">
      <c:choose>
        <c:when test="${not empty sessionScope.currentUser}">
          <jsp:include page="_navPanel.jsp">
            <jsp:param name="active" value="ORDER"/>
          </jsp:include>

          <main class="col-md-9">

            <div class="table-responsive-xxl">
              <table class="table table-bordered table-striped table-hover align-middle">
                <thead>
                <tr>
                  <th scope="col" style="min-width: 125px;">Mã đơn hàng</th>
                  <th scope="col" style="min-width: 100px;">Ngày mua</th>
                  <th scope="col" style="min-width: 300px;">Sản phẩm</th>
                  <th scope="col" style="min-width: 100px;">Tổng tiền</th>
                  <th scope="col" style="min-width: 175px;">Trạng thái đơn hàng</th>
                  <th scope="col">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${requestScope.orders}">
                  <tr>
                    <th scope="row">${order.id}</th>
                    <td>${order.createdAt}</td>
                    <td>${order.name}</td>
                    <td><fmt:formatNumber pattern="#,##0" value="${order.total}"/>₫</td>
                    <td>
                      <c:choose>
                        <c:when test="${order.status == 1}">
                          <span class="badge bg-warning text-dark">Đang giao hàng</span>
                        </c:when>
                        <c:when test="${order.status == 2}">
                          <span class="badge bg-success">Giao hàng thành công</span>
                        </c:when>
                        <c:when test="${order.status == 3}">
                          <span class="badge bg-danger">Hủy đơn hàng</span>
                        </c:when>
                      </c:choose>
                    </td>
                    <td class="text-center text-nowrap">
                      <a class="btn btn-primary me-2"
                         href="${pageContext.request.contextPath}/orderDetail?id=${order.id}"
                         role="button">
                        Xem đơn hàng
                      </a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>

            <c:if test="${requestScope.totalPages != 0}">
              <nav class="mt-4">
                <ul class="pagination">
                  <li class="page-item ${requestScope.page == 1 ? 'disabled' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/order?page=${requestScope.page - 1}">
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
                             href="${pageContext.request.contextPath}/order?page=${i}">
                              ${i}
                          </a>
                        </li>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>

                  <li class="page-item ${requestScope.page == requestScope.totalPages ? 'disabled' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/order?page=${requestScope.page + 1}">
                      Trang sau
                    </a>
                  </li>
                </ul>
              </nav>
            </c:if>

          </main> <!-- col.// -->
        </c:when>
        <c:otherwise>
          <p>
            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng trang này.
          </p>
        </c:otherwise>
      </c:choose>
    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
