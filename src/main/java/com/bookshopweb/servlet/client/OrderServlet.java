package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Order;
import com.bookshopweb.beans.OrderItem;
import com.bookshopweb.beans.Product;
import com.bookshopweb.beans.User;
import com.bookshopweb.dto.OrderItemCustom;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    private final OrderService orderService = new OrderService();
    private final OrderItemService orderItemService = new OrderItemService();
    private final ProductService productService = new ProductService();
    private static final int ORDER_ITEMS_PER_PAGE = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");

        if (user != null) {
            // Get list order by userId
            List<Order> orders = Protector.of(() -> orderService.getOrdersByUserId(user.getId())).get(ArrayList::new);
            List<Long> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());

            // Tổng số order item
            int totalOrderItem = Protector.of(() -> orderItemService.countByOrderId(orderIds)).get(0);

            // Tính tổng số trang (= tổng số order item / số order item trên mỗi trang)
            int totalPages = totalOrderItem / ORDER_ITEMS_PER_PAGE;
            if (totalOrderItem % ORDER_ITEMS_PER_PAGE != 0) {
                totalPages++;
            }

            // Lấy trang hiện tại, gặp ngoại lệ (chuỗi không phải số, nhỏ hơn 1, lớn hơn tổng số trang) thì gán bằng 1
            String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
            int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
            if (page < 1 || page > totalPages) {
                page = 1;
            }

            // Tính mốc truy vấn (offset)
            int offset = (page - 1) * ORDER_ITEMS_PER_PAGE;

            // Lấy danh sách order item, lấy với số lượng là ORDER_ITEMS_PER_PAGE và tính từ mốc offset
            List<OrderItem> orderItems = Protector.of(() -> orderItemService.getByOrderId(
                    orderIds, ORDER_ITEMS_PER_PAGE, offset
            )).get(ArrayList::new);

            List<OrderItemCustom> orderItemCustoms = new ArrayList<>();

<<<<<<< Updated upstream
=======
            List<OrderItemCustom> orderItemCustoms = new ArrayList<>();
>>>>>>> Stashed changes
            for (OrderItem orderItem : orderItems) {
                Optional<Product> product = productService.getById(orderItem.getId());
                Order order = orders.stream().filter(o -> o.getId() == orderItem.getOrderId()).findFirst().get();

                double total = 0.0;
                if (orderItem.getDiscount() == 0) {
                    total = orderItem.getPrice() * orderItem.getQuantity();
                } else {
                    total = (orderItem.getPrice() * (100 - orderItem.getDiscount()) / 100) * orderItem.getQuantity();
                }
<<<<<<< Updated upstream

                OrderItemCustom orderItemCustom = new OrderItemCustom(
                        order.getId(),
                        order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        product.get().getName(),
                        order.getStatus(),
                        total
                );
                orderItemCustoms.add(orderItemCustom);
=======
                OrderItemCustom item = new OrderItemCustom(order.getId(),
                        order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), product.get().getName(),
                        order.getStatus(), total);
                orderItemCustoms.add(item);
>>>>>>> Stashed changes
            }

            request.setAttribute("orders", orderItemCustoms);
            request.setAttribute("totalProducts", totalOrderItem);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);
        }

        request.setAttribute("screen", "order");
        request.getRequestDispatcher("WEB-INF/views/orderView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
}
