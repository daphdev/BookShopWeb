package com.bookshopweb.servlet.client.productreview;

import com.bookshopweb.beans.ProductReview;
import com.bookshopweb.service.ProductReviewService;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "AddProductReviewServlet", value = "/addProductReview")
public class AddProductReviewServlet extends HttpServlet {
    private final ProductReviewService productReviewService = new ProductReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> values = new HashMap<>();
        values.put("userId", request.getParameter("userId"));
        values.put("productId", request.getParameter("productId"));
        values.put("ratingScore", request.getParameter("ratingScore"));
        values.put("content", request.getParameter("content"));

        Map<String, List<String>> violations = new HashMap<>();
        violations.put("ratingScoreViolations", Validator.of(values.get("ratingScore"))
                .isNotNull()
                .toList());
        violations.put("contentViolations", Validator.of(values.get("content"))
                .isNotNullAndEmpty()
                .isAtLeastOfLength(10)
                .toList());

        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Đã đánh giá thành công!";
        String errorAddReviewMessage = "Đã có lỗi truy vấn!";
        AtomicReference<String> anchor = new AtomicReference<>("");

        if (sumOfViolations == 0) {
            ProductReview productReview = new ProductReview(
                    0L,
                    Protector.of(() -> Long.parseLong(values.get("userId"))).get(0L),
                    Protector.of(() -> Long.parseLong(values.get("productId"))).get(0L),
                    Protector.of(() -> Integer.parseInt(values.get("ratingScore"))).get(0),
                    values.get("content"),
                    1,
                    LocalDateTime.now(),
                    null
            );
            Protector.of(() -> productReviewService.insert(productReview))
                    .done(r -> {
                        request.getSession().setAttribute("successMessage", successMessage);
                        anchor.set("#review");
                    })
                    .fail(e -> {
                        request.getSession().setAttribute("values", values);
                        request.getSession().setAttribute("errorAddReviewMessage", errorAddReviewMessage);
                        anchor.set("#review-form");
                    });
        } else {
            request.getSession().setAttribute("values", values);
            request.getSession().setAttribute("violations", violations);
            anchor.set("#review-form");
        }

        response.sendRedirect(request.getContextPath() + "/product?id=" + values.get("productId") + anchor);
    }
}
