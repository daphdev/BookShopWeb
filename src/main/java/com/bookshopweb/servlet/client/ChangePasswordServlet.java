package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.User;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ChangePassword", value = "/changePassword")
public class ChangePasswordServlet extends HomeServlet {
    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/changePasswordView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> values = new HashMap<>();
        values.put("currentPassword", request.getParameter("currentPassword"));
        values.put("newPassword", request.getParameter("newPassword"));
        values.put("newPasswordAgain", request.getParameter("newPasswordAgain"));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        boolean currentPasswordEqualsUserPassword = HashingUtils.hash(values.get("currentPassword")).equals(user.getPassword());
        boolean newPasswordEqualsNewPasswordAgain = values.get("newPassword").equals(values.get("newPasswordAgain"));

        if (currentPasswordEqualsUserPassword && newPasswordEqualsNewPasswordAgain) {
            String newPassword = HashingUtils.hash(values.get("newPassword"));
            userService.changePassword(user.getId(), newPassword);
            String successMessage = "Đổi mật khẩu thành công!";
            request.setAttribute("successMessage", successMessage);
        } else {
            String errorMessage = "Đổi mật khẩu thất bại!";
            request.setAttribute("errorMessage", errorMessage);
        }

        request.getRequestDispatcher("/WEB-INF/views/changePasswordView.jsp").forward(request, response);
    }
}
