<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Thông tin sản phẩm #${requestScope.product.id}</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">Thông tin sản phẩm</h3>
    </header> <!-- section-heading.// -->

    <div class="card mb-5">
      <div class="card-body">
        <dl class="row">
          <dt class="col-md-3">ID</dt>
          <dd class="col-md-9">${requestScope.product.id}</dd>

          <dt class="col-md-3">Tên sản phẩm</dt>
          <dd class="col-md-9">
            <a href="${pageContext.request.contextPath}/product?id=${requestScope.product.id}" target="_blank">
              ${requestScope.product.name}
            </a>
          </dd>

          <dt class="col-md-3">Thể loại</dt>
          <dd class="col-md-9">
            <a href="${pageContext.request.contextPath}/category?id=${requestScope.category.id}" target="_blank">
              ${requestScope.category.name}
            </a>
          </dd>

          <dt class="col-md-3">Giá gốc</dt>
          <dd class="col-md-9"><fmt:formatNumber pattern="#,##0" value="${requestScope.product.price}"/>₫</dd>

          <dt class="col-md-3">Khuyến mãi</dt>
          <dd class="col-md-9"><fmt:formatNumber pattern="#,##0" value="${requestScope.product.discount}"/>%</dd>

          <dt class="col-md-3">Giá bán</dt>
          <dd class="col-md-9">
            <c:choose>
              <c:when test="${requestScope.product.discount == 0}">
                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.price}"/>₫
              </c:when>
              <c:otherwise>
                <fmt:formatNumber
                        pattern="#,##0"
                        value="${requestScope.product.price * (100 - requestScope.product.discount) / 100}"/>₫
              </c:otherwise>
            </c:choose>
          </dd>

          <dt class="col-md-3">Tồn kho</dt>
          <dd class="col-md-9">${requestScope.product.quantity}</dd>

          <dt class="col-md-3">Lượt mua</dt>
          <dd class="col-md-9">${requestScope.product.totalBuy}</dd>

          <dt class="col-md-3">Tác giả</dt>
          <dd class="col-md-9">${requestScope.product.author}</dd>

          <dt class="col-md-3">Số trang</dt>
          <dd class="col-md-9">${requestScope.product.pages}</dd>

          <dt class="col-md-3">Nhà xuất bản</dt>
          <dd class="col-md-9">${requestScope.product.publisher}</dd>

          <dt class="col-md-3">Năm xuất bản</dt>
          <dd class="col-md-9">${requestScope.product.yearPublishing}</dd>

          <dt class="col-md-3">Mô tả sản phẩm</dt>
          <dd class="col-md-9">${requestScope.product.description}</dd>

          <dt class="col-md-3">Hình sản phẩm</dt>
          <dd class="col-md-9">
            <c:choose>
              <c:when test="${empty requestScope.product.imageName}">
                <img width="280"
                     class="img-thumbnail"
                     src="${pageContext.request.contextPath}/img/280px.png"
                     alt="280px.png">
              </c:when>
              <c:otherwise>
                <img width="280"
                     class="img-thumbnail"
                     src="${pageContext.request.contextPath}/image/${requestScope.product.imageName}"
                     alt="${requestScope.product.imageName}">
              </c:otherwise>
            </c:choose>
          </dd>

          <dt class="col-md-3">Được giao dịch?</dt>
          <dd class="col-md-9">${requestScope.product.shop == 0 ? 'Không' : 'Có'}</dd>

          <dt class="col-md-3">Ngày tạo</dt>
          <dd class="col-md-9">${requestScope.product.createdAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</dd>

          <dt class="col-md-3">Ngày cập nhật</dt>
          <dd class="col-md-9">${requestScope.product.updatedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</dd>

          <dt class="col-md-3">Ngày bắt đầu khuyến mãi</dt>
          <dd class="col-md-9">${requestScope.product.startsAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</dd>

          <dt class="col-md-3">Ngày kết thúc khuyến mãi</dt>
          <dd class="col-md-9">${requestScope.product.endsAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}</dd>
        </dl>
      </div>
    </div> <!-- card.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footerAdmin.jsp"/>
</body>

</html>
