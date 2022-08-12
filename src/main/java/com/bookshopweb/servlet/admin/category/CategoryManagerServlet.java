package com.bookshopweb.servlet.admin.category;

import com.bookshopweb.beans.Category;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CategoryManagerServlet", value = "/admin/categoryManager")
public class CategoryManagerServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    private static final int CATEGORIES_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalCategories = Protector.of(categoryService::count).get(0);
        int totalPages = totalCategories / CATEGORIES_PER_PAGE + (totalCategories % CATEGORIES_PER_PAGE != 0 ? 1 : 0);

        String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
        int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * CATEGORIES_PER_PAGE;

        List<Category> categories = Protector.of(() -> categoryService.getOrderedPart(
                CATEGORIES_PER_PAGE, offset, "id", "DESC"
        )).get(ArrayList::new);

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/categoryManagerView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
