package com.bookshopweb.utils;

import com.google.gson.Gson;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtils {
    public static <T> T get(ServletRequest request, Class<T> objectClass) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(request.getReader(), objectClass);
    }

    public static <T> void out(ServletResponse response, T object, int status) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.print(jsonString);
        out.flush();
    }
}
