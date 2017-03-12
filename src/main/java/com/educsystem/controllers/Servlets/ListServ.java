package com.educsystem.controllers.Servlets;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Denis on 23.02.2017.
 */
public class ListServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginServ.sessionID != null) {
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/kursovoy/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/kursovoy/login");
        LoginServ.session.invalidate();
        LoginServ.sessionID = null;
        LoginServ.userlevel = 0;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
