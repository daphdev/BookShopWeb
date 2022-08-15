<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>${requestScope.product.name}</title>

  <!-- Custom Scripts -->
  <script src="${pageContext.request.contextPath}/js/toast.js" type="module"></script>
  <script src="${pageContext.request.contextPath}/js/product.js" type="module"></script>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop-2 bg-light">
  <div class="container">
    <nav>
      <ol class="breadcrumb">
        <li class="breadcrumb-item" aria-current="page">
          <a href="${pageContext.request.contextPath}/">Trang chủ</a>
        </li>
        <li class="breadcrumb-item" aria-current="page">
          <a href="${pageContext.request.contextPath}/category?id=${requestScope.category.id}">${requestScope.category.name}</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">${requestScope.product.name}</li>
      </ol>
    </nav>
  </div> <!-- container.// -->
</section> <!-- section-pagetop-2.// -->

<section class="section-content padding-y">
  <div class="container">
    <div class="row">

      <aside class="col-md-5 mb-md-0 mb-4 d-flex justify-content-center align-items-center">
        <c:choose>
          <c:when test="${empty requestScope.product.imageName}">
            <img width="280"
                 height="280"
                 class="img-fluid"
                 src="${pageContext.request.contextPath}/img/280px.png"
                 alt="280px.png">
          </c:when>
          <c:otherwise>
            <img width="280"
                 height="280"
                 class="img-fluid"
                 src="${pageContext.request.contextPath}/image/${requestScope.product.imageName}"
                 alt="${requestScope.product.imageName}">
          </c:otherwise>
        </c:choose>
      </aside>

      <main class="col-md-7">

        <h2 class="title">${requestScope.product.name}</h2>

        <div class="rating-wrap my-3">
          <span class="rating-stars me-2">
            <c:forEach begin="1" end="5" step="1" var="i">
              <i class="bi bi-star-fill ${i <= requestScope.averageRatingScore ? 'active' : ''}"></i>
            </c:forEach>
          </span>
          <small class="label-rating text-muted me-2">${requestScope.totalProductReviews} đánh giá</small>
          <small class="label-rating text-success">
            <i class="bi bi-bag-check-fill"></i> ${requestScope.product.totalBuy} đã mua
          </small>
        </div>

        <div class="mb-4">
          <c:choose>
            <c:when test="${requestScope.product.discount == 0}">
              <span class="price h4">
                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.price}"/>₫
              </span>
            </c:when>
            <c:otherwise>
              <span class="price h4">
                <fmt:formatNumber
                        pattern="#,##0"
                        value="${requestScope.product.price * (100 - requestScope.product.discount) / 100}"/>₫
              </span>
              <span class="ms-2 text-muted text-decoration-line-through">
                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.price}"/>₫
              </span>
              <span class="ms-2 badge bg-info">
                -<fmt:formatNumber pattern="#,##0" value="${requestScope.product.discount}"/>%
              </span>
            </c:otherwise>
          </c:choose>
        </div>

        <dl class="row mb-4">
          <dt class="col-xl-4 col-sm-5 col-6">Tác giả</dt>
          <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.author}</dd>

          <dt class="col-xl-4 col-sm-5 col-6">Số trang</dt>
          <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.pages}</dd>

          <dt class="col-xl-4 col-sm-5 col-6">Nhà xuất bản</dt>
          <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.publisher}</dd>

          <dt class="col-xl-4 col-sm-5 col-6">Năm xuất bản</dt>
          <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.yearPublishing}</dd>

          <dt class="col-xl-4 col-sm-5 col-6">Số lượng</dt>
          <dd class="col-xl-8 col-sm-7 col-6">
            <input type="number" id="cart-item-quantity" class="form-control w-50" value="1" min="1"
                   max="${requestScope.product.quantity}" step="1"/>
          </dd>
        </dl>

        <div>
          <button type="button" class="btn btn-danger" id="add-wishlist-item"
                  title="Thêm vào danh sách yêu thích" ${requestScope.isWishlistItem == 1 ? 'disabled' : ''}>
            <i class="bi bi-heart"></i>
          </button>
          <button type="button" class="btn btn-primary ms-2" id="buy-now">Mua ngay</button>
          <button type="button" class="btn btn-light ms-2" id="add-cart-item">Thêm vào giỏ hàng</button>
        </div>

      </main>

    </div>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<section class="section-content mb-4">
  <div class="container">
    <div class="row">
      <div class="col">
        <h3 class="pb-2">Mô tả sản phẩm</h3>
        <div>${requestScope.product.description}</div>
      </div>
    </div>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<section class="section-content mb-5">
  <div class="container">
    <div class="row">
      <div class="col">
        <c:if test="${requestScope.totalProductReviews != 0}">
          <h3 id="review" class="pb-2">${requestScope.totalProductReviews} đánh giá</h3>

          <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success" role="alert">${sessionScope.successMessage}</div>
          </c:if>
          <c:if test="${not empty sessionScope.errorDeleteReviewMessage}">
            <div class="alert alert-danger" role="alert">${sessionScope.errorDeleteReviewMessage}</div>
          </c:if>

          <div class="rattings-wrapper mb-5">
            <c:forEach var="productReview" items="${requestScope.productReviews}">
              <div class="sin-rattings mb-4">
                <div class="star-author-all mb-2 clearfix">
                  <div class="ratting-author float-start">
                    <h5 class="float-start me-3">${productReview.user.fullname}</h5>
                    <span>
                      <fmt:parseDate value="${productReview.createdAt}"
                                     pattern="yyyy-MM-dd'T'HH:mm" var="parsedCreatedAt"
                                     type="both"/>
                      <fmt:formatDate pattern="HH:mm dd/MM/yyyy " value="${parsedCreatedAt}"/>
                    </span>
                  </div>
                  <div class="ratting-star float-end">
                    <span class="rating-stars me-2">
                      <c:forEach begin="1" end="5" step="1" var="i">
                        <i class="bi bi-star-fill ${i <= productReview.ratingScore ? 'active' : ''}"></i>
                      </c:forEach>
                    </span>
                    <span>(${productReview.ratingScore})</span>
                  </div>
                </div>

                <div>
                  <c:choose>
                    <c:when test="${productReview.isShow == 1}">
                      ${productReview.content}
                    </c:when>
                    <c:otherwise>
                      <em>Nội dung đánh giá đã được ẩn bởi quản trị viên</em>
                    </c:otherwise>
                  </c:choose>
                </div>

                <c:if test="${productReview.userId == sessionScope.currentUser.id}">
                  <form action="${pageContext.request.contextPath}/deleteProductReview"
                        method="post">
                    <input type="hidden" name="productReviewId" value="${productReview.id}">
                    <input type="hidden" name="productId" value="${requestScope.product.id}">
                    <div class="btn-group" role="group">
                      <a href="${pageContext.request.contextPath}/editProductReview?id=${productReview.id}"
                         role="button"
                         class="btn btn-primary btn-sm">
                        Sửa
                      </a>
                      <button type="submit" class="btn btn-danger btn-sm"
                              onclick="return confirm('Bạn có muốn xóa?')">Xóa
                      </button>
                    </div>
                  </form>
                </c:if>
              </div>
            </c:forEach>

            <nav class="mt-4">
              <ul class="pagination">
                <li class="page-item ${requestScope.pageReview == 1 ? 'disabled' : ''}">
                  <a class="page-link"
                     href="${pageContext.request.contextPath}/product?id=${requestScope.product.id}&pageReview=${requestScope.pageReview - 1}#review">
                    Trang trước
                  </a>
                </li>

                <c:forEach begin="1" end="${requestScope.totalPagesOfProductReviews}" var="i">
                  <c:choose>
                    <c:when test="${requestScope.pageReview == i}">
                      <li class="page-item active">
                        <a class="page-link">${i}</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/product?id=${requestScope.product.id}&pageReview=${i}#review">
                            ${i}
                        </a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <li class="page-item ${requestScope.pageReview == requestScope.totalPagesOfProductReviews ? 'disabled' : ''}">
                  <a class="page-link"
                     href="${pageContext.request.contextPath}/product?id=${requestScope.product.id}&pageReview=${requestScope.pageReview + 1}#review">
                    Trang sau
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </c:if>

        <h3 id="review-form" class="pb-2">Thêm đánh giá</h3>

        <c:if test="${not empty sessionScope.errorAddReviewMessage}">
          <div class="alert alert-danger" role="alert">${sessionScope.errorAddReviewMessage}</div>
        </c:if>

        <c:choose>
          <c:when test="${not empty sessionScope.currentUser}">
            <div class="ratting-form-wrapper">
              <div class="ratting-form">
                <form action="${pageContext.request.contextPath}/addProductReview" method="post">
                  <div class="row">
                    <div class="col-md-3 mb-3">
                      <select class="form-select ${not empty sessionScope.violations.ratingScoreViolations
                                ? 'is-invalid' : (not empty sessionScope.values.ratingScore ? 'is-valid' : '')}"
                              name="ratingScore">
                        <option disabled ${not empty sessionScope.values.ratingScore ? '' : 'selected'}>
                          Cho sao
                        </option>
                        <c:forEach var="i" begin="1" end="5">
                          <option value="${i}" ${sessionScope.values.ratingScore == i ? 'selected' : ''}>${i}</option>
                        </c:forEach>
                      </select>
                      <c:if test="${not empty sessionScope.violations.ratingScoreViolations}">
                        <div class="invalid-feedback">
                          <ul class="list-unstyled mb-0">
                            <c:forEach var="violation"
                                       items="${sessionScope.violations.ratingScoreViolations}">
                              <li>${violation}</li>
                            </c:forEach>
                          </ul>
                        </div>
                      </c:if>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col">
                      <textarea class="form-control ${not empty sessionScope.violations.contentViolations
                                  ? 'is-invalid' : (not empty sessionScope.values.content ? 'is-valid' : '')}"
                                name="content"
                                placeholder="Nội dung đánh giá"
                                rows="3">${sessionScope.values.content}</textarea>
                      <c:if test="${not empty sessionScope.violations.contentViolations}">
                        <div class="invalid-feedback">
                          <ul class="list-unstyled mb-0">
                            <c:forEach var="violation"
                                       items="${sessionScope.violations.contentViolations}">
                              <li>${violation}</li>
                            </c:forEach>
                          </ul>
                        </div>
                      </c:if>
                    </div>
                  </div>
                  <input type="hidden" name="userId" value="${sessionScope.currentUser.id}">
                  <input type="hidden" name="productId" value="${requestScope.product.id}">
                  <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                </form>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <p>Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để đánh giá sản
              phẩm.</p>
          </c:otherwise>
        </c:choose>
        <%-- Xóa các attribute của AddProductReviewServlet khỏi session --%>
        <c:remove var="values" scope="session"/>
        <c:remove var="violations" scope="session"/>
        <c:remove var="successMessage" scope="session"/>
        <c:remove var="errorAddReviewMessage" scope="session"/>
        <c:remove var="errorDeleteReviewMessage" scope="session"/>
      </div> <!-- col.// -->
    </div> <!-- row.// -->
  </div> <!-- container.//  -->
</section> <!-- section-content.// -->

<section class="section-content mb-5">
  <div class="container">
    <h3 class="pb-2">Sản phẩm liên quan</h3>
    <div class="row item-grid">
      <c:forEach var="relatedProduct" items="${requestScope.relatedProducts}">
        <div class="col-xl-3 col-lg-4 col-md-6">
          <div class="card p-3 mb-4">
            <a href="${pageContext.request.contextPath}/product?id=${relatedProduct.id}"
               class="img-wrap text-center">
              <c:choose>
                <c:when test="${empty relatedProduct.imageName}">
                  <img width="200"
                       height="200"
                       class="img-fluid"
                       src="${pageContext.request.contextPath}/img/280px.png"
                       alt="280px.png">
                </c:when>
                <c:otherwise>
                  <img width="200"
                       height="200"
                       class="img-fluid"
                       src="${pageContext.request.contextPath}/image/${relatedProduct.imageName}"
                       alt="${relatedProduct.imageName}">
                </c:otherwise>
              </c:choose>
            </a>
            <figcaption class="info-wrap mt-2">
              <a href="${pageContext.request.contextPath}/product?id=${relatedProduct.id}"
                 class="title">${relatedProduct.name}</a>
              <div>
                <c:choose>
                  <c:when test="${relatedProduct.discount == 0}">
                    <span class="price mt-1 fw-bold">
                      <fmt:formatNumber pattern="#,##0" value="${relatedProduct.price}"/>₫
                    </span>
                  </c:when>
                  <c:otherwise>
                    <span class="price mt-1 fw-bold">
                      <fmt:formatNumber
                              pattern="#,##0"
                              value="${relatedProduct.price * (100 - relatedProduct.discount) / 100}"/>₫
                    </span>
                    <span class="ms-2 text-muted text-decoration-line-through">
                      <fmt:formatNumber pattern="#,##0" value="${relatedProduct.price}"/>₫
                    </span>
                    <span class="ms-2 badge bg-info">
                      -<fmt:formatNumber pattern="#,##0" value="${relatedProduct.discount}"/>%
                    </span>
                  </c:otherwise>
                </c:choose>
              </div>
            </figcaption>
          </div>
        </div> <!-- col.// -->
      </c:forEach>
    </div> <!-- row.// -->
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>

<div class="toast-container position-fixed bottom-0 start-0 p-3"></div> <!-- toast-container.// -->

</body>

</html>
