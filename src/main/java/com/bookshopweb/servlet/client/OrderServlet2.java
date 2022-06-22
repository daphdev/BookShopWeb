package com.bookshopweb.servlet.client;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshopweb.beans.*;
import com.bookshopweb.dto.OrderItemCustom;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.OrderItemService;
import com.bookshopweb.service.OrderService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.utils.Protector;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet2 extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");
        if(user != null){
            List<Order> orders = Protector.of(() -> orderService.getOrdersByUserId(user.getId())).get(ArrayList::new);

            request.setAttribute("orders", orders);
        }

        request.setAttribute("screen", "order");
        request.getRequestDispatcher("WEB-INF/views/orderView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
