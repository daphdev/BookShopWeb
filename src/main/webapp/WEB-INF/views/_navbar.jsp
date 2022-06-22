<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="col-md-3 mb-md-0 mb-3">
    <nav class="list-group">


        <a class="list-group-item ${requestScope.screen =='account' ? 'active' : ''}" href="#">Tài khoản</a>

        <c:choose>
            <c:when test="${requestScope.screen == 'order'}">
                <a class="list-group-item active" href="${pageContext.request.contextPath}/order"> Đơn hàng của tôi </a>
            </c:when>
            <c:otherwise>
                <a class="list-group-item" href="${pageContext.request.contextPath}/order"> Đơn hàng của tôi </a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.screen == 'wishlist'}">
                <a class="list-group-item active" href="${pageContext.request.contextPath}/wishlist"> Sản phẩm yêu
                    thích </a>
            </c:when>
            <c:otherwise>
                <a class="list-group-item" href="${pageContext.request.contextPath}/wishlist"> Sản phẩm yêu thích </a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.screen == 'change_pass'}">
                <a class="list-group-item active" href="#"> Đổi mật khẩu </a>
            </c:when>
            <c:otherwise>
                <a class="list-group-item" href="#"> Đổi mật khẩu </a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.screen == 'setting'}">
                <a class="list-group-item active" href="#"> Thiết đặt </a>
            </c:when>
            <c:otherwise>
                <a class="list-group-item" href="#"> Thiết đặt </a>
            </c:otherwise>
        </c:choose>
        <a class="list-group-item" href="${pageContext.request.contextPath}/signout"> Đăng xuất </a>
    </nav>
</aside>
<!-- col.// -->