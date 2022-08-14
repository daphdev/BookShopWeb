package com.bookshopweb.servlet.admin.category;

import com.bookshopweb.beans.Category;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.utils.ImageUtils;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DeleteCategoryServlet", value = "/admin/categoryManager/delete")
public class DeleteCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<Category> categoryFromServer = Protector.of(() -> categoryService.getById(id)).get(Optional::empty);

        if (categoryFromServer.isPresent()) {
            String successMessage = String.format("Xóa thể loại #%s thành công!", id);
            String errorMessage = String.format("Xóa thể loại #%s thất bại!", id);

            Protector.of(() -> {
                        categoryService.delete(id);
                        Optional.ofNullable(categoryFromServer.get().getImageName()).ifPresent(ImageUtils::delete);
                    })
                    .done(r -> request.getSession().setAttribute("successMessage", successMessage))
                    .fail(e -> request.getSession().setAttribute("errorMessage", errorMessage));
        }

        response.sendRedirect(request.getContextPath() + "/admin/categoryManager");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
