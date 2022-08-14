package com.bookshopweb.servlet.admin.user;

import com.bookshopweb.beans.User;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.HashingUtils;
import com.bookshopweb.utils.Protector;
import com.bookshopweb.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "CreateUserServlet", value = "/admin/userManager/create")
public class CreateUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/createUserView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullname(request.getParameter("fullname"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setGender(Protector.of(() -> Integer.parseInt(request.getParameter("gender"))).get(0));
        user.setAddress(request.getParameter("address"));
        user.setRole(request.getParameter("role"));

        Map<String, List<String>> violations = new HashMap<>();
        Optional<User> userByUsername = Protector.of(() -> userService.getByUsername(user.getUsername())).get(Optional::empty);
        Optional<User> userByEmail = Protector.of(() -> userService.getByEmail(user.getEmail())).get(Optional::empty);
        Optional<User> userByPhoneNumber = Protector.of(() -> userService.getByPhoneNumber(user.getPhoneNumber())).get(Optional::empty);
        violations.put("usernameViolations", Validator.of(user.getUsername())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(25)
                .isNotExistent(userByUsername.isPresent(), "Tên đăng nhập")
                .toList());
        violations.put("passwordViolations", Validator.of(user.getPassword())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(32)
                .toList());
        violations.put("fullnameViolations", Validator.of(user.getFullname())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());
        violations.put("emailViolations", Validator.of(user.getEmail())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^[^@]+@[^@]+\\.[^@]+$", "email")
                .isNotExistent(userByEmail.isPresent(), "Email")
                .toList());
        violations.put("phoneNumberViolations", Validator.of(user.getPhoneNumber())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^\\d{10,11}$", "số điện thoại")
                .isNotExistent(userByPhoneNumber.isPresent(), "Số điện thoại")
                .toList());
        violations.put("genderViolations", Validator.of(user.getGender())
                .isNotNull()
                .toList());
        violations.put("addressViolations", Validator.of(user.getAddress())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());
        violations.put("roleViolations", Validator.of(user.getRole())
                .isNotNull()
                .toList());

        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Thêm thành công!";
        String errorMessage = "Thêm thất bại!";

        if (sumOfViolations == 0) {
            user.setPassword(HashingUtils.hash(user.getPassword()));
            Protector.of(() -> userService.insert(user))
                    .done(r -> request.setAttribute("successMessage", successMessage))
                    .fail(e -> {
                        request.setAttribute("user", user);
                        request.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            request.setAttribute("user", user);
            request.setAttribute("violations", violations);
        }

        request.getRequestDispatcher("/WEB-INF/views/createUserView.jsp").forward(request, response);
    }
}
