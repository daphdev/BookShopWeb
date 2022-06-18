<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 6/14/2022
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_meta.jsp"/>
    <title>Đơn hàng</title>

    <link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

    <!-- Bootstrap v5.0.1 -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="js/bootstrap.bundle.js" type="text/javascript"></script>

    <!-- Bootstrap Icons v1.5.0 -->
    <link href="css/bootstrap-icons.css" type="text/css" rel="stylesheet">

    <!-- Custom Styles -->
    <link href="css/styles.css" type="text/css" rel="stylesheet">
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
                    <jsp:include page="_navbar.jsp"/>

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
                                <c:forEach var="orders" items="${requestScope.orders}">
                                    <tr>
                                        <th scope="row">${orders.id}</th>
                                        <td>${orders.createdAt}</td>
                                        <td>${orders.name}</td>
                                        <td><fmt:formatNumber pattern="#,##0" value="${orders.total}"/>₫</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${orders.status == 1}">
                                                    <span class="badge bg-warning text-dark">Đang giao hàng</span>
                                                </c:when>
                                                <c:when test="${orders.status == 2}">
                                                    <span class="badge bg-success">Giao hàng thành công</span>
                                                </c:when>
                                                <c:when test="${orders.status == 3}">
                                                    <span class="badge bg-danger">Hủy đơn hàng</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td class="text-center text-nowrap">
                                            <a class="btn btn-primary me-2" href="#" role="button">Xem đơn hàng</a>
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

                    </main>
                    <!-- col.// -->
                </c:when>
                <c:otherwise>
                    <p>Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng trang này
                    </p>
                </c:otherwise>
            </c:choose>
        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>

</body>

</html>
