<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <jsp:include page="_meta.jsp"/>
    <title>Trang sản phẩm yêu thích</title>
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

            <aside class="col-md-3 mb-md-0 mb-3">
                <nav class="list-group">
                    <a class="list-group-item" href="#"> Tài khoản </a>
                    <a class="list-group-item" href="#"> Đơn hàng của tôi </a>
                    <a class="list-group-item active" href="#"> Sản phẩm yêu thích </a>
                    <a class="list-group-item" href="#"> Đổi mật khẩu </a>
                    <a class="list-group-item" href="#"> Thiết đặt </a>
                    <a class="list-group-item" href="#"> Đăng xuất </a>
                </nav>
            </aside> <!-- col.// -->

            <main class="col-md-9">
                <article class="card">
                    <div class="card-body">
                        <div class="row g-3">
                            <div class="col-lg-6">
                                <figure class="d-flex align-items-center m-0">
                                    <div class="aside"><img src="img/80px.png" width="80" height="80"></div>
                                    <figcaption class="ps-3">
                                        <a href="#">Tiếng Việt lớp 1</a>
                                        <p class="mb-2">
                                            <span class="price">320.000₫</span>
                                            <span class="ms-2 text-muted text-decoration-line-through">
                          350.000₫
                        </span>
                                        </p>
                                        <button type="button" class="btn btn-primary btn-sm">
                                            Thêm vào giỏ hàng
                                        </button>
                                        <button type="button" class="btn btn-danger btn-sm ms-1" data-toggle="tooltip"
                                                title="Xóa khỏi danh sách yêu thích" data-original-title="Xóa khỏi danh sách yêu thích">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </figcaption>
                                </figure>
                            </div> <!-- col.// -->

                            <div class="col-lg-6">
                                <figure class="d-flex align-items-center m-0">
                                    <div class="aside"><img src="img/80px.png" width="80" height="80"></div>
                                    <figcaption class="ps-3">
                                        <a href="#">Tiếng Việt lớp 2</a>
                                        <p class="mb-2">
                                            <span class="price">320.000₫</span>
                                            <span class="ms-2 text-muted text-decoration-line-through">
                          350.000₫
                        </span>
                                        </p>
                                        <button type="button" class="btn btn-primary btn-sm">
                                            Thêm vào giỏ hàng
                                        </button>
                                        <button type="button" class="btn btn-danger btn-sm ms-1" data-toggle="tooltip"
                                                title="Xóa khỏi danh sách yêu thích" data-original-title="Xóa khỏi danh sách yêu thích">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </figcaption>
                                </figure>
                            </div> <!-- col.// -->

                            <div class="col-lg-6">
                                <figure class="d-flex align-items-center m-0">
                                    <div class="aside"><img src="img/80px.png" width="80" height="80"></div>
                                    <figcaption class="ps-3">
                                        <a href="#">Tiếng Việt lớp 3</a>
                                        <p class="mb-2">
                                            <span class="price">320.000₫</span>
                                        </p>
                                        <button type="button" class="btn btn-primary btn-sm">
                                            Thêm vào giỏ hàng
                                        </button>
                                        <button type="button" class="btn btn-danger btn-sm ms-1" data-toggle="tooltip"
                                                title="Xóa khỏi danh sách yêu thích" data-original-title="Xóa khỏi danh sách yêu thích">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </figcaption>
                                </figure>
                            </div> <!-- col.// -->
                        </div> <!-- row .//  -->
                    </div> <!-- card-body.// -->
                </article>
            </main> <!-- col.// -->

        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="_footer.jsp"/>

</body>

</html>