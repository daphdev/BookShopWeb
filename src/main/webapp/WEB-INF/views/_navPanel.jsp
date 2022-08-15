<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<aside class="col-md-3 mb-md-0 mb-3">
  <nav class="list-group">
    <a class="list-group-item ${param.active == 'USER' ? 'active' : ''}"
       href="${pageContext.request.contextPath}/user" role="button">
      Tài khoản
    </a>
    <a class="list-group-item ${param.active == 'ORDER' ? 'active' : ''}"
       href="${pageContext.request.contextPath}/order" role="button">
      Đơn hàng của tôi
    </a>
    <a class="list-group-item ${param.active == 'WISHLIST' ? 'active' : ''}"
       href="${pageContext.request.contextPath}/wishlist">
      Sản phẩm yêu thích
    </a>
    <a class="list-group-item ${param.active == 'CHANGE_PASSWORD' ? 'active' : ''}"
       href="${pageContext.request.contextPath}/changePassword" role="button">
      Đổi mật khẩu
    </a>
    <a class="list-group-item ${param.active == 'SETTING' ? 'active' : ''}"
       href="${pageContext.request.contextPath}/setting" role="button">
      Thiết đặt
    </a>
    <a class="list-group-item" href="${pageContext.request.contextPath}/signout" role="button">Đăng xuất</a>
  </nav>
</aside> <!-- col.// -->
