package com.codecool.shop.util;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Util {

    public static void handleError(HttpServletRequest req, HttpServletResponse resp, int statusCode, final String message) throws IOException {
        req.getSession().setAttribute("errorMessage", message);
        req.getSession().setAttribute("statusCode", statusCode);
        resp.sendRedirect("/error");
    }

    public static boolean checkPassword(String provided, String saved) {
        return BCrypt.hashpw(provided, BCrypt.gensalt()).equals(saved);
    }
}
