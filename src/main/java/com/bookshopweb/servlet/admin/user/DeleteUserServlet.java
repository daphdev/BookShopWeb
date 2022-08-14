package com.bookshopweb.servlet.admin.user;

import com.bookshopweb.beans.User;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DeleteUserServlet", value = "/admin/userManager/delete")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<User> userFromServer = Protector.of(() -> userService.getById(id)).get(Optional::empty);

        if (userFromServer.isPresent()) {
            String successMessage = String.format("Xóa người dùng #%s thành công!", id);
            String errorMessage = String.format("Xóa người dùng #%s thất bại!", id);

            Protector.of(() -> userService.delete(id))
                    .done(r -> request.getSession().setAttribute("successMessage", successMessage))
                    .fail(e -> request.getSession().setAttribute("errorMessage", errorMessage));
        }

        response.sendRedirect(request.getContextPath() + "/admin/userManager");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
