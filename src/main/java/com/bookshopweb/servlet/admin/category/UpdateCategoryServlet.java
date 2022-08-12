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
import java.util.Optional;

@WebServlet(name = "UpdateCategoryServlet", value = "/admin/categoryManager/update")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class UpdateCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<Category> categoryFromServer = Protector.of(() -> categoryService.getById(id)).get(Optional::empty);

        if (categoryFromServer.isPresent()) {
            request.setAttribute("category", categoryFromServer.get());
            request.getRequestDispatcher("/WEB-INF/views/updateCategoryView.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/categoryManager");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        category.setId(Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L));
        category.setName(request.getParameter("name"));
        category.setDescription(request.getParameter("description").trim().isEmpty()
                ? null : request.getParameter("description"));
        category.setImageName(request.getParameter("imageName").trim().isEmpty()
                ? null : request.getParameter("imageName"));

        String deleteImage = request.getParameter("deleteImage");

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
        String successMessage = "Sửa thành công!";
        String errorMessage = "Sửa thất bại!";

        if (sumOfViolations == 0) {
            if (category.getImageName() != null) {
                String currentImageName = category.getImageName();
                if (deleteImage != null) {
                    ImageUtils.delete(currentImageName);
                    category.setImageName(null);
                }
                ImageUtils.upload(request).ifPresent(imageName -> {
                    ImageUtils.delete(currentImageName);
                    category.setImageName(imageName);
                });
            } else {
                ImageUtils.upload(request).ifPresent(category::setImageName);
            }
            Protector.of(() -> categoryService.update(category))
                    .done(r -> {
                        request.setAttribute("category", category);
                        request.setAttribute("successMessage", successMessage);
                    })
                    .fail(e -> {
                        request.setAttribute("category", category);
                        request.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            request.setAttribute("category", category);
            request.setAttribute("violations", violations);
            request.setAttribute("deleteImage", deleteImage);
        }

        request.getRequestDispatcher("/WEB-INF/views/updateCategoryView.jsp").forward(request, response);
    }
}
