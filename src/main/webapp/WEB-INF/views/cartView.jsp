<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Giỏ hàng</title>

  <!-- Custom Scripts -->
  <script src="${pageContext.request.contextPath}/js/toast.js" type="module"></script>
  <script src="${pageContext.request.contextPath}/js/cart.js" type="module"></script>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
  <div class="container">
    <h2 class="title-page">Giỏ hàng</h2>
  </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">

      <c:choose>
        <c:when test="${empty sessionScope.currentUser}">
          <p>
            Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng chức năng giỏ hàng.
          </p>
        </c:when>
        <c:otherwise>
          <main class="col-lg-9 mb-lg-0 mb-3">
            <div class="card">

              <div id="cart-table"></div>

              <div class="card-body border-top">
                <button type="button" class="btn btn-primary float-end" id="checkoutBtn" disabled>Đặt hàng</button>
                <a href="${pageContext.request.contextPath}/" class="btn btn-light">Tiếp tục mua sắm</a>
              </div> <!-- card-body.// -->

            </div> <!-- card.// -->
          </main> <!-- col.// -->

          <aside class="col-lg-3">

            <div class="card mb-3">
              <div class="card-body">
                <p class="card-title">Hình thức giao hàng</p>
                <form>
                  <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="delivery-method"
                           id="delivery-method-1" value="1" disabled>
                    <label class="form-check-label" for="delivery-method-1">Giao tiêu chuẩn</label>
                  </div>
                  <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="delivery-method"
                           id="delivery-method-2" value="2" disabled>
                    <label class="form-check-label" for="delivery-method-2">Giao nhanh</label>
                  </div>
                </form>
              </div> <!-- card-body.// -->
            </div> <!-- card.// -->

            <div class="card">
              <div class="card-body">
                <dl class="row mb-0">
                  <dt class="col-xxl-6 col-lg-12 col-6">Tạm tính:</dt>
                  <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3"><span id="temp-price">0</span>₫</dd>
                  <dt class="col-xxl-6 col-lg-12 col-6">Phí vận chuyển:</dt>
                  <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3"><span id="delivery-price">0</span>₫</dd>
                  <dt class="col-xxl-6 col-lg-12 col-6">Tổng cộng:</dt>
                  <dd class="col-xxl-6 col-lg-12 col-6 text-end mb-3"><strong><span id="total-price">0</span>₫</strong></dd>
                </dl>
              </div> <!-- card-body.// -->
            </div> <!-- card.// -->

          </aside> <!-- col.// -->
        </c:otherwise>
      </c:choose>

    </div> <!-- row.// -->
  </div> <!-- container -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>

<div class="toast-container position-fixed bottom-0 start-0 p-3"></div> <!-- toast-container.// -->

</body>

</html>
