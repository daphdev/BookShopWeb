<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Thông tin người dùng #${requestScope.user.id}</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">Thông tin người dùng</h3>
    </header> <!-- section-heading.// -->

    <div class="card mb-5">
      <div class="card-body">
        <dl class="row">
          <dt class="col-md-3">ID</dt>
          <dd class="col-md-9">${requestScope.user.id}</dd>

          <dt class="col-md-3">Tên đăng nhập</dt>
          <dd class="col-md-9">${requestScope.user.username}</dd>

          <dt class="col-md-3">Họ và tên</dt>
          <dd class="col-md-9">${requestScope.user.fullname}</dd>

          <dt class="col-md-3">Email</dt>
          <dd class="col-md-9">${requestScope.user.email}</dd>

          <dt class="col-md-3">Số điện thoại</dt>
          <dd class="col-md-9">${requestScope.user.phoneNumber}</dd>

          <dt class="col-md-3">Giới tính</dt>
          <dd class="col-md-9">${requestScope.user.gender == 0 ? 'Nam' : 'Nữ'}</dd>

          <dt class="col-md-3">Địa chỉ</dt>
          <dd class="col-md-9">${requestScope.user.address}</dd>
        </dl>
      </div>
    </div> <!-- card.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footerAdmin.jsp"/>
</body>

</html>
