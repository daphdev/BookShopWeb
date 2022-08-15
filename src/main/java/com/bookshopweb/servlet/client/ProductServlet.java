package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Category;
import com.bookshopweb.beans.Product;
import com.bookshopweb.beans.ProductReview;
import com.bookshopweb.beans.User;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.ProductReviewService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.service.WishlistItemService;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final ProductService productService = new ProductService();
    private final ProductReviewService productReviewService = new ProductReviewService();
    private final WishlistItemService wishlistItemService = new WishlistItemService();

    private static final int PRODUCT_REVIEWS_PER_PAGE = 2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id của product và đối tượng product từ database theo id này
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<Product> productFromServer = Protector.of(() -> productService.getById(id)).get(Optional::empty);

        // Nếu id là số nguyên dương và có hiện diện trong bảng product
        if (id > 0L && productFromServer.isPresent()) {
            // Lấy category theo productId
            Optional<Category> categoryFromServer = Protector.of(() -> categoryService.getByProductId(id)).get(Optional::empty);
            Category category = categoryFromServer.orElseGet(Category::new);

            // Lấy product từ productFromServer
            Product product = productFromServer.get();
            product.setDescription(TextUtils.toParagraph(Optional.ofNullable(product.getDescription()).orElse("")));

            // Lấy tổng số đánh giá (productReview) của sản phẩm
            int totalProductReviews = Protector.of(() -> productReviewService.countByProductId(id)).get(0);

            // Tính tổng số trang để phân trang phần đánh giá
            int totalPagesOfProductReviews = totalProductReviews / PRODUCT_REVIEWS_PER_PAGE;
            if (totalProductReviews % PRODUCT_REVIEWS_PER_PAGE != 0) {
                totalPagesOfProductReviews++;
            }

            // Lấy trang đánh giá hiện tại, gặp ngoại lệ (chuỗi không phải số, nhỏ hơn 1, lớn hơn tổng số trang) thì gán bằng 1
            String pageReviewParam = Optional.ofNullable(request.getParameter("pageReview")).orElse("1");
            int pageReview = Protector.of(() -> Integer.parseInt(pageReviewParam)).get(1);
            if (pageReview < 1 || pageReview > totalPagesOfProductReviews) {
                pageReview = 1;
            }

            // Tính mốc truy vấn (offset)
            int offset = (pageReview - 1) * PRODUCT_REVIEWS_PER_PAGE;

            // Lấy các productReview theo productId
            List<ProductReview> productReviews = Protector.of(() -> productReviewService.getOrderedPartByProductId(
                    PRODUCT_REVIEWS_PER_PAGE, offset, "createdAt", "DESC", id
            )).get(ArrayList::new);

            productReviews.forEach(productReview -> productReview.setContent(
                    TextUtils.toParagraph(productReview.getContent())));

            // Lấy tổng cộng số sao đánh giá của sản phẩm
            int sumRatingScores = Protector.of(() -> productReviewService.sumRatingScoresByProductId(id)).get(0);

            // Tính số sao đánh giá trung bình
            int averageRatingScore = (totalProductReviews == 0) ? 0 : (sumRatingScores / totalProductReviews);

            // Lấy các sản phẩm liên quan
            List<Product> relatedProducts = Protector.of(() -> productService.getRandomPartByCategoryId(
                    4, 0, category.getId()
            )).get(ArrayList::new);

            // Kiểm tra có phải là sản phẩm yêu thích
            int isWishlistItem = Optional.ofNullable((User) request.getSession().getAttribute("currentUser"))
                    .map(User::getId)
                    .map(userId -> Protector.of(() -> wishlistItemService
                            .countByUserIdAndProductId(userId, product.getId())).get(0))
                    .orElse(0);

            request.setAttribute("category", category);
            request.setAttribute("product", product);
            request.setAttribute("totalProductReviews", totalProductReviews);
            request.setAttribute("productReviews", productReviews);
            request.setAttribute("totalPagesOfProductReviews", totalPagesOfProductReviews);
            request.setAttribute("pageReview", pageReview);
            request.setAttribute("averageRatingScore", averageRatingScore);
            request.setAttribute("relatedProducts", relatedProducts);
            request.setAttribute("isWishlistItem", isWishlistItem);
            request.getRequestDispatcher("/WEB-INF/views/productView.jsp").forward(request, response);
        } else {
            // Nếu id không phải là số nguyên hoặc không hiện diện trong bảng product
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
