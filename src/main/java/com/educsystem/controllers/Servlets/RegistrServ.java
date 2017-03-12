package com.educsystem.controllers.Servlets;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.common.security.Crypt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.educsystem.services.UserService;
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
public class RegistrServ extends HttpServlet {
    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static Logger log = Logger.getLogger(RegistrServ.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (LoginServ.sessionID != null) {
            resp.sendRedirect("/kursovoy/chapters");
        } else {
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String oldPassword = req.getParameter("password");
        String password = Crypt.crypting(oldPassword);
        try {
            if(userService.registration(login,email,password)){
                log.trace("User registration successfull!");
                resp.sendRedirect("/kursovoy/login");
            } else {
                log.trace("User registration failed!");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e){
            log.error("Cannot get reg function!", e);
        } catch (UserDaoException e){
            log.error(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
