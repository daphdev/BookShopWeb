package com.bookshopweb.servlet.client;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshopweb.beans.*;
import com.bookshopweb.dto.OrderResponse;
import com.bookshopweb.service.OrderItemService;
import com.bookshopweb.service.OrderService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.utils.Protector;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private static final int ORDERS_PER_PAGE = 3;
    private final ProductService productService = new ProductService();
    private final OrderItemService orderItemService = new OrderItemService();
    private final Order order = new Order();
    private final OrderItem orderItem = new OrderItem();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");
        int totalPages = 0;
        int page = 0;
        if (user != null) {
            int totalOrders = orderService.countByUserId(user.getId());

            // Tính tổng số trang (= tổng số order / số sản phẩm trên mỗi trang)
            totalPages = totalOrders / ORDERS_PER_PAGE;
            if (totalOrders % ORDERS_PER_PAGE != 0) {
                totalPages++;
            }

            // Lấy trang hiện tại, gặp ngoại lệ (chuỗi không phải số, nhỏ hơn 1, lớn hơn tổng số trang) thì gán bằng 1
            String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
            page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
            if (page < 1 || page > totalPages) {
                page = 1;
            }

            // Tính mốc truy vấn (offset)
            int offset = (page - 1) * ORDERS_PER_PAGE;

            // Lấy danh sách order, lấy với số lượng là ORDERS_PER_PAGE và tính từ mốc offset
            List<Order> orders = Protector.of(() -> orderService.getOrderedPartByUserId(user.getId(), ORDERS_PER_PAGE, offset)).get(ArrayList::new);

            double total = 0.0;
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (Order o : orders) {

//                Tính total
                List<OrderItem> orderItems = Protector.of(() -> orderItemService.getByOrderId(o.getId())).get(ArrayList::new);

                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getDiscount() == 0) {
                        total += orderItem.getPrice() * orderItem.getQuantity();
                    } else {
                        total += (orderItem.getPrice() * (100 - orderItem.getDiscount()) / 100) * orderItem.getQuantity();
                    }
                }

                //OrderResponse orderResponseList = new OrderResponse(o.getId(), o.getCreatedAt().format(DateTimeFormatter.BASIC_ISO_DATE), orderItemService.getProductNamesByOrderId(o.getId()).toString(), o.getStatus(), total);
                OrderResponse orderResponseList = new OrderResponse(o.getId(), o.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/mm/yyyy")), check(orderItemService.getProductNamesByOrderId(o.getId())), o.getStatus(), total);
                orderResponses.add(orderResponseList);

            }

            request.setAttribute("orders", orderResponses);
        }

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("WEB-INF/views/orderView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String check(List<String> list) {
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return list.get(0) + " và " + (list.size() - 1) + " sản phẩm khác";
        }
    }
}
