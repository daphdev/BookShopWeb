package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Order;
import com.bookshopweb.beans.OrderItem;
import com.bookshopweb.beans.Product;
import com.bookshopweb.service.OrderItemService;
import com.bookshopweb.service.OrderService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "OrderDetailServlet", value = "/orderDetail")
public class OrderDetailServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final OrderItemService orderItemService = new OrderItemService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id của order và đối tượng order từ database theo id này
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<Order> orderFromServer = Protector.of(() -> orderService.getById(id)).get(Optional::empty);

        if (orderFromServer.isPresent()) {
            Order order = orderFromServer.get();
            List<OrderItem> orderItems = Protector.of(() -> orderItemService.getByOrderId(id)).get(ArrayList::new);

            double tempPrice = 0;

            for (OrderItem orderItem : orderItems) {
                if (orderItem.getDiscount() == 0) {
                    tempPrice += orderItem.getPrice() * orderItem.getQuantity();
                } else {
                    tempPrice += (orderItem.getPrice() * (100 - orderItem.getDiscount()) / 100) * orderItem.getQuantity();
                }
                orderItem.setProduct(productService.getById(orderItem.getProductId()).orElseGet(Product::new));
            }

            request.setAttribute("order", order);
            request.setAttribute("createdAt", order.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
            request.setAttribute("tempPrice", tempPrice);
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/WEB-INF/views/orderDetailView.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Protector.of(() -> orderService.cancelOrder(id));
        response.sendRedirect(request.getContextPath() + "/orderDetail?id=" + id);
    }
}
