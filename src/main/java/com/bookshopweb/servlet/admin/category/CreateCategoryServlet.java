package com.bookshopweb.servlet.admin.category;

import com.bookshopweb.beans.Category;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.utils.ImageUtils;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CreateCategoryServlet", value = "/admin/categoryManager/create")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class CreateCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/createCategoryView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        category.setName(request.getParameter("name"));
        category.setDescription(request.getParameter("description").trim().isEmpty()
                ? null : request.getParameter("description"));

        Map<String, List<String>> violations = new HashMap<>();
        violations.put("nameViolations", Validator.of(category.getName())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(100)
                .toList());
        violations.put("descriptionViolations", Validator.of(category.getDescription())
                .isAtMostOfLength(350)
                .toList());

        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Thêm thành công!";
        String errorMessage = "Thêm thất bại!";

        if (sumOfViolations == 0) {
            ImageUtils.upload(request).ifPresent(category::setImageName);
            Protector.of(() -> categoryService.insert(category))
                    .done(r -> request.setAttribute("successMessage", successMessage))
                    .fail(e -> {
                        request.setAttribute("category", category);
                        request.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            request.setAttribute("category", category);
            request.setAttribute("violations", violations);
        }

        request.getRequestDispatcher("/WEB-INF/views/createCategoryView.jsp").forward(request, response);
    }
}
