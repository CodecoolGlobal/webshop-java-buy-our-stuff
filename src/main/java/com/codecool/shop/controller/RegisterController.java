package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoController;
import com.codecool.shop.dao.UserDao;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private boolean registerError;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("registerError", registerError);
        engine.process("product/register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = DaoController.getUserDao();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isNameAvailable = userDao.isNameAvailable(username);

        if (isNameAvailable) {
            registerError = false;
            userDao.add(username, password);
            resp.sendRedirect("/");
        } else {
            registerError = true;
            doGet(req, resp);
        }
    }
}