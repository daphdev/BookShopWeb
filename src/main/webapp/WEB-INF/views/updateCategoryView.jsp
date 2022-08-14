<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="_meta.jsp"/>
  <title>Sửa thể loại</title>
</head>

<body>
<jsp:include page="_headerAdmin.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4">
      <h3 class="section-title">Sửa thể loại</h3>
    </header> <!-- section-heading.// -->

    <main class="row mb-5">
      <form class="col-lg-6" method="POST" action="${pageContext.request.contextPath}/admin/categoryManager/update"
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
          <label for="category-name" class="form-label">Tên thể loại <span class="text-danger">*</span></label>
          <input type="text"
                 class="form-control ${not empty requestScope.violations.nameViolations
                   ? 'is-invalid' : (not empty requestScope.category.name ? 'is-valid' : '')}"
                 id="category-name"
                 name="name"
                 value="${requestScope.category.name}"
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
          <label for="category-description" class="form-label">Mô tả thể loại</label>
          <textarea class="form-control ${not empty requestScope.violations.descriptionViolations
                      ? 'is-invalid' : (not empty requestScope.category.description ? 'is-valid' : '')}"
                    id="category-description"
                    rows="5"
                    name="description">${requestScope.category.description}</textarea>
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
          <label for="category-imageName" class="form-label d-block">Hình thể loại</label>
          <c:choose>
            <c:when test="${not empty requestScope.category.imageName}">
              <img width="50"
                   class="img-thumbnail mb-3"
                   src="${pageContext.request.contextPath}/image/${requestScope.category.imageName}"
                   alt="${requestScope.category.imageName}"
                   title="${requestScope.category.imageName}">
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
                 id="category-imageName"
                 name="image"
                 accept="image/*">
        </div>
        <input type="hidden" name="id" value="${requestScope.category.id}">
        <input type="hidden" name="imageName" value="${requestScope.category.imageName}">
        <button type="submit" class="btn btn-primary me-2 mb-3">
          Sửa
        </button>
        <button type="reset"
                class="btn btn-warning me-2 mb-3"
                onclick="return confirm('Bạn có muốn để giá trị mặc định?')">
          Mặc định
        </button>
        <a class="btn btn-danger mb-3"
           href="${pageContext.request.contextPath}/admin/categoryManager"
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
