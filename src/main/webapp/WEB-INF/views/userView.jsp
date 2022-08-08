<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Tài khoản</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
  <div class="container">
    <h2 class="title-page">Tài khoản</h2>
  </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">
      <c:choose>
        <c:when test="${empty sessionScope.currentUser}">
          <p>
            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để xem thông tin tài khoản.
          </p>
        </c:when>
        <c:otherwise>
          <aside class="col-md-3 mb-md-0 mb-3">
            <nav class="list-group">
              <a class="list-group-item active" href="${pageContext.request.contextPath}/user" role="button"> Tài khoản </a>
              <a class="list-group-item" href="${pageContext.request.contextPath}/order" role="button">Đơn hàng của tôi</a>
              <a class="list-group-item" href="#"> Sản phẩm yêu thích </a>
              <a class="list-group-item" href="${pageContext.request.contextPath}/changePassword" role="button"> Đổi mật khẩu </a>
              <a class="list-group-item" href="#"> Thiết đặt </a>
              <a class="list-group-item" href="${pageContext.request.contextPath}/signout" role="button">Đăng xuất</a>
            </nav>
          </aside> <!-- col.// -->

          <main class="col-md-9">
            <article class="card">
              <div class="card-body">
                <div>
                  <strong>${sessionScope.currentUser.fullname}</strong>
                  <p>${sessionScope.currentUser.email}</p>
                </div>
                <hr>
                <div>
                  <p class="bi bi-map d-block lh-lg">
                    Địa chỉ:
                    <br>
                      ${sessionScope.currentUser.address}
                  </p>
                </div>
                <article class="card-group">
                  <figure class="card bg-light">
                    <div class="p-3">
                      <h4 class="title">${requestScope.countCartItemQuantity}</h4>
                      <span>Sản phẩm trong giỏ</span>
                    </div>
                  </figure>
                  <figure class="card bg-light">
                    <div class="p-3">
                      <h4 class="title">${requestScope.countOrderItemQuantity}</h4>
                      <span>Đơn hàng</span>
                    </div>
                  </figure>
                  <figure class="card bg-light">
                    <div class="p-3">
                      <h4 class="title">${requestScope.countOrderDeliver}</h4>
                      <span>Sản phẩm đang giao</span>
                    </div>
                  </figure>
                  <figure class="card bg-light">
                    <div class="p-3">
                      <h4 class="title">${requestScope.countOrderReceived}</h4>
                      <span>Sản phẩm đã nhận</span>
                    </div>
                  </figure>
                </article> <!-- row .//  -->
              </div> <!-- card-body.// -->
            </article>
          </main> <!-- col.// -->
        </c:otherwise>
      </c:choose>
    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
