package com.bookshopweb.servlet.admin.product;

import com.bookshopweb.beans.Product;
import com.bookshopweb.service.ProductService;
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

@WebServlet(name = "ProductManagerServlet", value = "/admin/productManager")
public class ProductManagerServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    private static final int PRODUCTS_PER_PAGE = 15;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalProducts = Protector.of(productService::count).get(0);
        int totalPages = totalProducts / PRODUCTS_PER_PAGE + (totalProducts % PRODUCTS_PER_PAGE != 0 ? 1 : 0);

        String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
        int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * PRODUCTS_PER_PAGE;

        List<Product> products = Protector.of(() -> productService.getOrderedPart(
                PRODUCTS_PER_PAGE, offset, "id", "DESC"
        )).get(ArrayList::new);

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/productManagerView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
