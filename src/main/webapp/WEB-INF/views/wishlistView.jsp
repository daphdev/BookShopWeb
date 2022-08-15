<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Sản phẩm yêu thích</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
  <div class="container">
    <h2 class="title-page">Sản phẩm yêu thích</h2>
  </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">
      <jsp:include page="_navPanel.jsp">
        <jsp:param name="active" value="WISHLIST"/>
      </jsp:include>

      <main class="col-md-9">
        <article class="card">
          <div class="card-body">
            <c:choose>
              <c:when test="${empty sessionScope.currentUser}">
                <p>
                  Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng chức năng sản
                  phẩm yêu thích.
                </p>
              </c:when>
              <c:when test="${empty requestScope.wishlistItems}">
                <p>
                  Người dùng không có sản phẩm yêu thích.
                </p>
              </c:when>
              <c:otherwise>
                <div class="row g-3">
                  <c:forEach var="wishlistItem" items="${requestScope.wishlistItems}">
                    <div class="col-lg-6">
                      <figure class="d-flex align-items-center m-0">
                        <div class="aside">
                          <c:choose>
                            <c:when test="${empty wishlistItem.product.imageName}">
                              <img width="80"
                                   height="80"
                                   src="${pageContext.request.contextPath}/img/280px.png"
                                   alt="280px.png">
                            </c:when>
                            <c:otherwise>
                              <img width="80"
                                   height="80"
                                   src="${pageContext.request.contextPath}/image/${wishlistItem.product.imageName}"
                                   alt="${wishlistItem.product.imageName}">
                            </c:otherwise>
                          </c:choose>
                        </div>
                        <figcaption class="ps-3">
                          <a href="${pageContext.request.contextPath}/product?id=${wishlistItem.product.id}"
                             target="_blank">
                              ${wishlistItem.product.name}
                          </a>
                          <p class="mb-2">
                            <c:choose>
                              <c:when test="${wishlistItem.product.discount == 0}">
                                <span class="price">
                                  <fmt:formatNumber pattern="#,##0" value="${wishlistItem.product.price}"/>₫
                                </span>
                              </c:when>
                              <c:otherwise>
                                <span class="price">
                                  <fmt:formatNumber
                                    pattern="#,##0"
                                    value="${wishlistItem.product.price * (100 - wishlistItem.product.discount) / 100}"/>₫
                                </span>
                                <span class="ms-2 text-muted text-decoration-line-through">
                                  <fmt:formatNumber pattern="#,##0" value="${wishlistItem.product.price}"/>₫
                                </span>
                                <span class="ms-2 badge bg-info">
                                  -<fmt:formatNumber pattern="#,##0" value="${wishlistItem.product.discount}"/>%
                                </span>
                              </c:otherwise>
                            </c:choose>
                          </p>
                          <form action="${pageContext.request.contextPath}/wishlist" method="post">
                            <input type="hidden" name="id" value="${wishlistItem.id}">
                            <button type="submit" class="btn btn-danger btn-sm" data-toggle="tooltip"
                                    title="Xóa khỏi danh sách yêu thích"
                                    onclick="return confirm('Bạn có muốn xóa sản phẩm yêu thích này?')">
                              <i class="bi bi-trash"></i>
                            </button>
                          </form>
                        </figcaption>
                      </figure>
                    </div> <!-- col.// -->
                  </c:forEach>
                </div> <!-- row .// -->
              </c:otherwise>
            </c:choose>
          </div> <!-- card-body.// -->
        </article>
      </main> <!-- col.// -->

    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>
</body>

</html>
