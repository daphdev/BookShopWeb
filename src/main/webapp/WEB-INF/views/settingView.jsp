<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Thiết đặt</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
  <div class="container">
    <h2 class="title-page">Thiết đặt</h2>
  </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">
      <c:choose>
        <c:when test="${empty sessionScope.currentUser}">
          <p>
            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng chức năng thiết đặt.
          </p>
        </c:when>
        <c:otherwise>
          <jsp:include page="_navPanel.jsp">
            <jsp:param name="active" value="SETTING"/>
          </jsp:include>

          <main class="col-md-9">
            <article class="card">
              <div class="card-body">
                <c:if test="${not empty requestScope.successMessage}">
                  <div class="alert alert-success" role="alert">${requestScope.successMessage}</div>
                </c:if>
                <c:if test="${not empty requestScope.errorMessage}">
                  <div class="alert alert-danger" role="alert">${requestScope.errorMessage}</div>
                </c:if>
                <div class="col-lg-6">
                  <form action="${pageContext.request.contextPath}/setting" method="post">
                    <div class="mb-3">
                      <label for="inputUsername" class="form-label">Tên đăng nhập</label>
                      <input type="text"
                             class="form-control"
                             id="inputUsername"
                             name="username"
                             value="${requestScope.user.username}">
                    </div>
                    <div class="mb-3">
                      <label for="inputFullname" class="form-label">Họ và tên</label>
                      <input type="text"
                             class="form-control"
                             id="inputFullname"
                             name="fullname"
                             value="${requestScope.user.fullname}">
                    </div>
                    <div class="mb-3">
                      <label for="inputEmail" class="form-label">Email</label>
                      <input type="email"
                             class="form-control"
                             id="inputEmail"
                             name="email"
                             value="${requestScope.user.email}">
                    </div>
                    <div class="mb-3">
                      <label for="inputPhoneNumber" class="form-label">Số điện thoại</label>
                      <input type="text"
                             class="form-control"
                             id="inputPhoneNumber"
                             name="phoneNumber"
                             value="${requestScope.user.phoneNumber}">
                    </div>
                    <div class="mb-3">
                      <div class="form-check d-inline-block me-4">
                        <input class="form-check-input"
                               type="radio"
                               name="gender"
                               id="radioGender1"
                               value="0"
                          ${requestScope.user.gender == 0 ? 'checked' : ''}>
                        <label class="form-check-label" for="radioGender1">Nam</label>
                      </div>
                      <div class="form-check d-inline-block">
                        <input class="form-check-input"
                               type="radio"
                               name="gender"
                               id="radioGender2"
                               value="1"
                          ${requestScope.user.gender == 1 ? 'checked' : ''}>
                        <label class="form-check-label" for="radioGender2">Nữ</label>
                      </div>
                    </div>
                    <div class="mb-3">
                      <label for="inputAddress" class="form-label">Địa chỉ</label>
                      <input type="text"
                             class="form-control"
                             id="inputAddress"
                             name="address"
                             value="${requestScope.user.address}">
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Cập nhật thông tin mới</button>
                  </form>
                </div>
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
