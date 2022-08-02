<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="_meta.jsp"/>
    <title>Thông tin đơn hàng</title>
</head>

<body>
<jsp:include page="_header.jsp"/>

<section class="section-pagetop bg-light">
    <div class="container">
        <h2 class="title-page">Thông tin đơn hàng ${order.id}</h2>
    </div> <!-- container.// -->
</section> <!-- section-pagetop.// -->

<section class="section-content padding-y">
    <div class="container">
        <div class="row">

            <c:choose>
                <c:when test="${not empty sessionScope.currentUser}">
                    <jsp:include page="_navbar.jsp"/>


            <main class="col-md-9">

                <article class="card mb-4">

                    <header class="card-header" >
                        <strong class="d-inline-block me-4">Mã đơn hàng: ${order.id}</strong>
                        <span>Ngày mua: ${order.createdAt}</span>
                    </header> <!-- card-header.// -->

                    <div class="card-body pb-0">
                        <div class="row">
                            <div class="col-lg-8">
                                <h6 class="text-muted">Địa chỉ người nhận</h6>
                                <p class="lh-lg">
                                    Nguyễn Thị A <br>
                                    Số điện thoại: 0919944382 <br>
                                    Địa chỉ: Đường Nguyễn Duy Trinh, Phường Bình Trưng Đông, Quận 2, TP.HCM
                                </p>
                            </div>
                            <div class="col-lg-4">
                                <h6 class="text-muted">Hình thức thanh toán</h6>
                                <span class="text-success">
                    <i class="fab fa-lg fa-cc-visa"></i>
                    Giao tiêu chuẩn
                  </span>
                                <p class="lh-lg">
                                    Tạm tính: 50.000₫ <br>
                                    Phí vận chuyển: 10.000₫ <br>
                                    <strong>Tổng cộng: 60.000₫</strong>
                                </p>
                            </div>
                        </div> <!-- row.// -->
                    </div> <!-- card-body.// -->

                    <hr class="m-0">

                    <div class="table-responsive">
                        <table class="cart-table table table-borderless">
                            <thead class="text-muted">
                            <tr class="small text-uppercase">
                                <th scope="col" style="min-width: 280px;">Sản phẩm</th>
                                <th scope="col" width="150" style="min-width: 150px;">Giá</th>
                                <th scope="col" width="150" style="min-width: 150px;">Số lượng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <figure class="itemside">
                                        <div class="float-start me-3"><img src="img/80px.png"></div>
                                        <figcaption class="info">
                                            <a href="#" class="title">Tiếng Việt lớp 1</a>
                                        </figcaption>
                                    </figure>
                                </td>
                                <td>
                                    <div class="price-wrap">
                                        <span class="price">20.000₫</span>
                                    </div>
                                </td>
                                <td>
                                    1
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <figure class="itemside">
                                        <div class="float-start me-3"><img src="img/80px.png"></div>
                                        <figcaption class="info">
                                            <a href="#" class="title">Tiếng Việt lớp 2</a>
                                        </figcaption>
                                    </figure>
                                </td>
                                <td>
                                    <div class="price-wrap">
                                        <span class="price">30.000₫</span>
                                    </div>
                                </td>
                                <td>
                                    1
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div> <!-- table.responsive-md.// -->

                    <div class="card-footer py-3">
                        <a href="#" class="btn btn-primary me-2">Theo dõi đơn hàng</a>
                        <a href="#" class="btn btn-danger">Hủy đơn hàng</a>
                    </div> <!-- card-footer.// -->

                </article>

            </main> <!-- col.// -->

                </c:when>
                <c:otherwise>
                    <p>
                        Vui lòng <a href="${pageContext.request.contextPath}/signin">đăng nhập</a> để sử dụng trang này.
                    </p>
                </c:otherwise>
            </c:choose>

        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>

</body>

</html>
