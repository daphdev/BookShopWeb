<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Thông tin đơn hàng #${requestScope.order.id}</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">Thông tin đơn hàng</h3>
    </header> <!-- section-heading.// -->

    <div class="card mb-5">
      <div class="card-body">
        <dl class="row">
          <dt class="col-md-3">Mã đơn hàng</dt>
          <dd class="col-md-9">${requestScope.order.id}</dd>

          <dt class="col-md-3">Ngày tạo</dt>
          <dd class="col-md-9">
            ${requestScope.order.createdAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}
          </dd>

          <dt class="col-md-3">Ngày cập nhật</dt>
          <dd class="col-md-9">
            ${requestScope.order.updatedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))}
          </dd>

          <dt class="col-md-3">Người đặt/nhận</dt>
          <dd class="col-md-9">
            <a href="${pageContext.request.contextPath}/admin/userManager/detail?id=${requestScope.order.user.id}">
              ${requestScope.order.user.username}
            </a>
            (${requestScope.order.user.fullname})
          </dd>

          <dt class="col-md-3">Hình thức thanh toán</dt>
          <dd class="col-md-9">
            ${requestScope.order.deliveryMethod == 1 ? 'Giao tiêu chuẩn' : 'Giao nhanh'}
          </dd>

          <dt class="col-md-3">Tạm tính</dt>
          <dd class="col-md-9">
            <fmt:formatNumber pattern="#,##0"
                              value="${requestScope.order.totalPrice - requestScope.order.deliveryPrice}"/>₫
          </dd>

          <dt class="col-md-3">Phí vận chuyển</dt>
          <dd class="col-md-9">
            <fmt:formatNumber pattern="#,##0" value="${requestScope.order.deliveryPrice}"/>₫
          </dd>

          <dt class="col-md-3">Tổng tiền</dt>
          <dd class="col-md-9">
            <fmt:formatNumber pattern="#,##0" value="${requestScope.order.totalPrice}"/>₫
          </dd>

          <dt class="col-md-3">Trạng thái</dt>
          <dd class="col-md-9">
            <c:choose>
              <c:when test="${requestScope.order.status == 1}">
                <span class="badge bg-warning text-dark">Đang giao hàng</span>
              </c:when>
              <c:when test="${requestScope.order.status == 2}">
                <span class="badge bg-success">Giao hàng thành công</span>
              </c:when>
              <c:when test="${requestScope.order.status == 3}">
                <span class="badge bg-danger">Hủy đơn hàng</span>
              </c:when>
            </c:choose>
          </dd>

          <dt class="col-md-3">Sản phẩm</dt>
          <dd class="col-md-9">
            <div class="table-responsive border rounded">
              <table class="table table-borderless">
                <thead class="text-muted">
                <tr class="small text-uppercase">
                  <th scope="col" style="min-width: 280px;">Sản phẩm</th>
                  <th scope="col" style="min-width: 150px;">Giá</th>
                  <th scope="col" style="min-width: 150px;">Số lượng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderItem" items="${requestScope.order.orderItems}">
                  <tr>
                    <td>
                      <figure class="itemside">
                        <div class="float-start me-3">
                          <c:choose>
                            <c:when test="${empty orderItem.product.imageName}">
                              <img width="80"
                                   height="80"
                                   src="${pageContext.request.contextPath}/img/280px.png"
                                   alt="280px.png">
                            </c:when>
                            <c:otherwise>
                              <img width="80"
                                   height="80"
                                   src="${pageContext.request.contextPath}/image/${orderItem.product.imageName}"
                                   alt="${orderItem.product.imageName}">
                            </c:otherwise>
                          </c:choose>
                        </div>
                        <figcaption class="info">
                          <a href="${pageContext.request.contextPath}/product?id=${orderItem.product.id}"
                             target="_blank">
                              ${orderItem.product.name}
                          </a>
                        </figcaption>
                      </figure>
                    </td>
                    <td>
                      <div class="price-wrap">
                        <c:choose>
                          <c:when test="${orderItem.discount == 0}">
                            <span class="price">
                              <fmt:formatNumber pattern="#,##0" value="${orderItem.price}"/>₫
                            </span>
                          </c:when>
                          <c:otherwise>
                            <div>
                              <span class="price">
                                <fmt:formatNumber
                                        pattern="#,##0"
                                        value="${orderItem.price * (100 - orderItem.discount) / 100}"/>₫
                              </span>
                              <span class="ms-2 badge bg-info">
                                -<fmt:formatNumber pattern="#,##0" value="${orderItem.discount}"/>%
                              </span>
                            </div>
                            <small class="text-muted text-decoration-line-through">
                              <fmt:formatNumber pattern="#,##0" value="${orderItem.price}"/>₫
                            </small>
                          </c:otherwise>
                        </c:choose>
                      </div>
                    </td>
                    <td>${orderItem.quantity}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div> <!-- table.responsive-md.// -->
          </dd>
        </dl>
      </div>
    </div> <!-- card.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footerAdmin.jsp"/>
</body>

</html>
