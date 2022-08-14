<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Sửa sản phẩm</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">Sửa sản phẩm</h3>
    </header> <!-- section-heading.// -->

    <main class="row mb-5">
      <form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/productManager/update"
            enctype="multipart/form-data">
        <c:if test="${not empty requestScope.successMessage}">
          <div class="alert alert-success mb-3" role="alert">
              ${requestScope.successMessage}
          </div>
        </c:if>
        <c:if test="${not empty requestScope.errorMessage}">
          <div class="alert alert-danger mb-3" role="alert">
              ${requestScope.errorMessage}
          </div>
        </c:if>
        <div class="mb-3">
          <label for="product-name" class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
          <input type="text"
                 class="form-control ${not empty requestScope.violations.nameViolations
                   ? 'is-invalid' : (not empty requestScope.product.name ? 'is-valid' : '')}"
                 id="product-name"
                 name="name"
                 value="${requestScope.product.name}"
                 required>
          <c:if test="${not empty requestScope.violations.nameViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.nameViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-category" class="form-label">Thể loại <span class="text-danger">*</span></label>
          <select class="form-select ${not empty requestScope.violations.categoryViolations
                    ? 'is-invalid' : (not empty requestScope.categoryId ? 'is-valid' : '')}"
                  id="product-category"
                  name="category"
                  required>
            <option ${empty requestScope.categoryId || requestScope.categoryId == 0 ? 'selected' : ''} disabled>
              Chọn một thể loại...
            </option>
            <c:forEach var="category" items="${requestScope.categories}">
              <option value="${category.id}" ${requestScope.categoryId == category.id ? 'selected' : ''}>
                  ${category.name}
              </option>
            </c:forEach>
          </select>
          <c:if test="${not empty requestScope.violations.categoryViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.categoryViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-price" class="form-label">Giá gốc <span class="text-danger">*</span></label>
          <div class="input-group ${not empty requestScope.violations.priceViolations ? 'has-validation' : ''}">
            <input type="number"
                   class="form-control ${not empty requestScope.violations.priceViolations
                     ? 'is-invalid' : (not empty requestScope.product.price ? 'is-valid' : '')}"
                   id="product-price"
                   name="price"
                   value="${requestScope.product.price}"
                   min="0"
                   step="500"
                   required>
            <span class="input-group-text">₫</span>
            <c:if test="${not empty requestScope.violations.priceViolations}">
              <div class="invalid-feedback">
                <ul class="list-unstyled mb-0">
                  <c:forEach var="violation" items="${requestScope.violations.priceViolations}">
                    <li>${violation}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>
        </div>
        <div class="mb-3">
          <label for="product-discount" class="form-label">Khuyến mãi <span class="text-danger">*</span></label>
          <div class="input-group ${not empty requestScope.violations.discountViolations ? 'has-validation' : ''}">
            <input type="number"
                   class="form-control ${not empty requestScope.violations.discountViolations
                     ? 'is-invalid' : (not empty requestScope.product.discount ? 'is-valid' : '')}"
                   id="product-discount"
                   name="discount"
                   value="${requestScope.product.discount}"
                   min="0"
                   max="100"
                   required>
            <span class="input-group-text">%</span>
            <c:if test="${not empty requestScope.violations.discountViolations}">
              <div class="invalid-feedback">
                <ul class="list-unstyled mb-0">
                  <c:forEach var="violation" items="${requestScope.violations.discountViolations}">
                    <li>${violation}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>
        </div>
        <div class="mb-3">
          <label for="product-quantity" class="form-label">Tồn kho <span class="text-danger">*</span></label>
          <input type="number"
                 class="form-control ${not empty requestScope.violations.quantityViolations
                   ? 'is-invalid' : (not empty requestScope.product.quantity ? 'is-valid' : '')}"
                 id="product-quantity"
                 name="quantity"
                 value="${requestScope.product.quantity}"
                 min="0"
                 required>
          <c:if test="${not empty requestScope.violations.quantityViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.quantityViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-totalBuy" class="form-label">Lượt mua <span class="text-danger">*</span></label>
          <input type="number"
                 class="form-control ${not empty requestScope.violations.totalBuyViolations
                   ? 'is-invalid' : (not empty requestScope.product.totalBuy ? 'is-valid' : '')}"
                 id="product-totalBuy"
                 name="totalBuy"
                 value="${requestScope.product.totalBuy}"
                 min="0"
                 required>
          <c:if test="${not empty requestScope.violations.totalBuyViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.totalBuyViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-author" class="form-label">Tác giả <span class="text-danger">*</span></label>
          <input type="text"
                 class="form-control ${not empty requestScope.violations.authorViolations
                   ? 'is-invalid' : (not empty requestScope.product.author ? 'is-valid' : '')}"
                 id="product-author"
                 name="author"
                 value="${requestScope.product.author}"
                 required>
          <c:if test="${not empty requestScope.violations.authorViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.authorViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-pages" class="form-label">Số trang <span class="text-danger">*</span></label>
          <input type="number"
                 class="form-control ${not empty requestScope.violations.pagesViolations
                   ? 'is-invalid' : (not empty requestScope.product.pages ? 'is-valid' : '')}"
                 id="product-pages"
                 name="pages"
                 value="${requestScope.product.pages}"
                 min="1"
                 required>
          <c:if test="${not empty requestScope.violations.pagesViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.pagesViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-publisher" class="form-label">Nhà xuất bản <span class="text-danger">*</span></label>
          <input type="text"
                 class="form-control ${not empty requestScope.violations.publisherViolations
                   ? 'is-invalid' : (not empty requestScope.product.publisher ? 'is-valid' : '')}"
                 id="product-publisher"
                 name="publisher"
                 value="${requestScope.product.publisher}"
                 required>
          <c:if test="${not empty requestScope.violations.publisherViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.publisherViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-yearPublishing" class="form-label">Năm xuất bản <span class="text-danger">*</span></label>
          <input type="number"
                 class="form-control ${not empty requestScope.violations.yearPublishingViolations
                   ? 'is-invalid' : (not empty requestScope.product.yearPublishing ? 'is-valid' : '')}"
                 id="product-yearPublishing"
                 name="yearPublishing"
                 value="${requestScope.product.yearPublishing}"
                 min="1901"
                 max="2099"
                 required>
          <c:if test="${not empty requestScope.violations.yearPublishingViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.yearPublishingViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-description" class="form-label">Mô tả sản phẩm</label>
          <textarea class="form-control ${not empty requestScope.violations.descriptionViolations
                      ? 'is-invalid' : (not empty requestScope.product.description ? 'is-valid' : '')}"
                    id="product-description"
                    rows="10"
                    name="description">${requestScope.product.description}</textarea>
          <c:if test="${not empty requestScope.violations.descriptionViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.descriptionViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-imageName" class="form-label d-block">Hình sản phẩm</label>
          <c:choose>
            <c:when test="${not empty requestScope.product.imageName}">
              <img width="200"
                   class="img-thumbnail mb-3"
                   src="${pageContext.request.contextPath}/image/${requestScope.product.imageName}"
                   alt="${requestScope.product.imageName}"
                   title="${requestScope.product.imageName}">
              <div class="mb-3 form-check">
                <input class="form-check-input"
                       type="checkbox"
                       value="deleteImage"
                       id="delete-image"
                       name="deleteImage" ${not empty requestScope.deleteImage ? 'checked' : ''}>
                <label class="form-check-label" for="delete-image">Xóa hình này?</label>
              </div>
            </c:when>
            <c:otherwise>
              <div class="fst-italic mb-3">Không có hình</div>
            </c:otherwise>
          </c:choose>
          <input type="file"
                 class="form-control"
                 id="product-imageName"
                 name="image"
                 accept="image/*">
        </div>
        <div class="mb-3">
          <label class="form-label d-block">Cho phép giao dịch? <span class="text-danger">*</span></label>
          <div class="form-check d-inline-block me-4">
            <input class="form-check-input ${not empty requestScope.violations.shopViolations
                     ? 'is-invalid' : (not empty requestScope.product.shop ? 'is-valid' : '')}"
                   type="radio"
                   name="shop"
                   id="product-shop-yes"
                   value="1" ${requestScope.product.shop == 1 ? 'checked' : ''}
                   required>
            <label class="form-check-label" for="product-shop-yes">Có</label>
          </div>
          <div class="form-check d-inline-block">
            <input class="form-check-input ${not empty requestScope.violations.shopViolations
                     ? 'is-invalid' : (not empty requestScope.product.shop ? 'is-valid' : '')}"
                   type="radio"
                   name="shop"
                   id="product-shop-no"
                   value="0" ${requestScope.product.shop == 0 ? 'checked' : ''}
                   required>
            <label class="form-check-label" for="product-shop-no">Không</label>
          </div>
          <c:if test="${not empty requestScope.violations.genderViolations}">
            <div class="is-invalid"></div>
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.genderViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-startsAt" class="form-label">Ngày bắt đầu khuyến mãi</label>
          <input type="datetime-local"
                 class="form-control ${not empty requestScope.violations.startsAtViolations
                   ? 'is-invalid' : (not empty requestScope.product.startsAt ? 'is-valid' : '')}"
                 id="product-startsAt"
                 name="startsAt"
                 value="${requestScope.product.startsAt}">
          <c:if test="${not empty requestScope.violations.startsAtViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.startsAtViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <div class="mb-3">
          <label for="product-endsAt" class="form-label">Ngày kết thúc khuyến mãi</label>
          <input type="datetime-local"
                 class="form-control ${not empty requestScope.violations.endsAtViolations
                   ? 'is-invalid' : (not empty requestScope.product.endsAt ? 'is-valid' : '')}"
                 id="product-endsAt"
                 name="endsAt"
                 value="${requestScope.product.endsAt}">
          <c:if test="${not empty requestScope.violations.endsAtViolations}">
            <div class="invalid-feedback">
              <ul class="list-unstyled">
                <c:forEach var="violation" items="${requestScope.violations.endsAtViolations}">
                  <li>${violation}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
        </div>
        <input type="hidden" name="id" value="${requestScope.product.id}">
        <input type="hidden" name="imageName" value="${requestScope.product.imageName}">
        <button type="submit" class="btn btn-primary me-2 mb-3">
          Sửa
        </button>
        <button type="reset"
                class="btn btn-warning me-2 mb-3"
                onclick="return confirm('Bạn có muốn để giá trị mặc định?')">
          Mặc định
        </button>
        <a class="btn btn-danger mb-3"
           href="${pageContext.request.contextPath}/admin/productManager"
           role="button"
           onclick="return confirm('Bạn có muốn hủy?')">
          Hủy
        </a>
      </form>
    </main>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footerAdmin.jsp"/>
</body>

</html>
