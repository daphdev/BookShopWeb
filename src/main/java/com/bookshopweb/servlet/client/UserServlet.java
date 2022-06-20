package com.bookshopweb.servlet.client;
import com.bookshopweb.beans.User;
import com.bookshopweb.service.CartService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        int countCartItemQuantityByUserId =  cartService.countCartItemQuantityByUserId(user.getId());
        request.setAttribute("countCartItemQuantity", countCartItemQuantityByUserId);


        int countOrderItemQuantityByUserId =  cartService.countOrderItemQuantityByUserId(user.getId());
        request.setAttribute("countOrderItemQuantity", countOrderItemQuantityByUserId);


        int countOrderDeliverByUserId =  cartService.countOrderDeliverByUserId(user.getId());
        request.setAttribute("countOrderDeliver", countOrderDeliverByUserId);


        int countOrderReceivedByUserId =  cartService.countOrderReceivedByUserId(user.getId());
        request.setAttribute("countOrderReceived", countOrderReceivedByUserId);


        request.getRequestDispatcher("WEB-INF/views/userView.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}