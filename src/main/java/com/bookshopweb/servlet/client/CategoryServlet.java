package com.bookshopweb.servlet.client;

import com.bookshopweb.beans.Category;
import com.bookshopweb.beans.Product;
import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.utils.Protector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final ProductService productService = new ProductService();

    private static final int PRODUCTS_PER_PAGE = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id của category và đối tượng category từ database theo id này
        long id = Protector.of(() -> Long.parseLong(request.getParameter("id"))).get(0L);
        Optional<Category> categoryFromServer = Protector.of(() -> categoryService.getById(id)).get(Optional::empty);

        // Nếu id là số nguyên dương và có hiện diện trong bảng category
        if (id > 0L && categoryFromServer.isPresent()) {
            // Tiêu chí lọc 1: Nhà xuất bản
            Optional<String[]> checkedPublishersParam = Optional.ofNullable(request.getParameterValues("checkedPublishers"));
            List<String> checkedPublishers = checkedPublishersParam.map(Arrays::asList).orElseGet(ArrayList::new);

            // Tiêu chí lọc 2: Khoảng giá
            Optional<String[]> priceRangesParam = Optional.ofNullable(request.getParameterValues("priceRanges"));
            List<String> priceRanges = priceRangesParam.map(Arrays::asList).orElseGet(ArrayList::new);

            // Tiêu chí sắp xếp
            Optional<String> orderParam = Optional.ofNullable(request.getParameter("order"));
            String orderBy = orderParam.map(productService::getFirst).orElse("totalBuy");
            String orderDir = orderParam.map(productService::getLast).orElse("DESC");

            // Tổng hợp các tiêu chí lọc
            List<String> filters = new ArrayList<>();
            checkedPublishersParam.ifPresent(p -> filters.add(productService.filterByPublishers(checkedPublishers)));
            priceRangesParam.ifPresent(p -> filters.add(productService.filterByPriceRanges(priceRanges)));
            String filtersQuery = productService.createFiltersQuery(filters);

            // Tính tổng số sản phẩm của thể loại (và có thể là tiêu chí lọc)
            int totalProducts;

            // Nếu không có tiêu chí lọc
            if (filters.isEmpty()) {
                totalProducts = Protector.of(() -> productService.countByCategoryId(id)).get(0);
            } else {
                totalProducts = Protector.of(() -> productService.countByCategoryIdAndFilters(id, filtersQuery)).get(0);
            }

            // Tính tổng số trang (= tổng số sản phẩm / số sản phẩm trên mỗi trang)
            int totalPages = totalProducts / PRODUCTS_PER_PAGE;
            if (totalProducts % PRODUCTS_PER_PAGE != 0) {
                totalPages++;
            }

            // Lấy trang hiện tại, gặp ngoại lệ (chuỗi không phải số, nhỏ hơn 1, lớn hơn tổng số trang) thì gán bằng 1
            String pageParam = Optional.ofNullable(request.getParameter("page")).orElse("1");
            int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
            if (page < 1 || page > totalPages) {
                page = 1;
            }

            // Tính mốc truy vấn (offset)
            int offset = (page - 1) * PRODUCTS_PER_PAGE;

            // Lấy danh sách product của category, lấy với số lượng là PRODUCTS_PER_PAGE và tính từ mốc offset
            List<Product> products;

            // Nếu không có tiêu chí lọc
            if (filters.isEmpty()) {
                products = Protector.of(() -> productService.getOrderedPartByCategoryId(
                        PRODUCTS_PER_PAGE, offset, orderBy, orderDir, id
                )).get(ArrayList::new);
            } else {
                products = Protector.of(() -> productService.getOrderedPartByCategoryIdAndFilters(
                        PRODUCTS_PER_PAGE, offset, orderBy, orderDir, id, filtersQuery
                )).get(ArrayList::new);
            }

            // Lấy danh sách nhà xuất bản (tiêu chí lọc 1)
            List<String> publishers = Protector.of(() -> productService.getPublishersByCategoryId(id)).get(ArrayList::new);

            request.setAttribute("category", categoryFromServer.get());
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);
            request.setAttribute("products", products);
            request.setAttribute("publishers", publishers);
            request.setAttribute("checkedPublishers", checkedPublishers);
            request.setAttribute("priceRanges", priceRanges);
            request.setAttribute("order", orderParam.orElse("totalBuy-DESC"));
            request.setAttribute("filterQueryString",
                    request.getQueryString().replaceAll("^id=\\d{1,5}(&page=\\d{1,5}|)", ""));
            request.getRequestDispatcher("/WEB-INF/views/categoryView.jsp").forward(request, response);
        } else {
            // Nếu id không phải là số nguyên hoặc không hiện diện trong bảng category
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
