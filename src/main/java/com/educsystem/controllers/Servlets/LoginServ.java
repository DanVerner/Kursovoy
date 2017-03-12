package com.educsystem.controllers.Servlets;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.common.security.Crypt;
import com.educsystem.database.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.educsystem.services.UserService;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Denis on 22.02.2017.
 */
public class LoginServ extends HttpServlet {

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = Logger.getLogger(LoginServ.class);
    public static String sessionID = null;
    public static HttpSession session;
    public static int userlevel;
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
        if (sessionID != null) {
            resp.sendRedirect("/kursovoy/chapters");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String oldPassword = req.getParameter("password");
        String password = Crypt.crypting(oldPassword);
        try {
            if(userService.authorize(login, password)){
                log.trace("Login successfull!");
                session = req.getSession();
                sessionID = session.getId();
                resp.sendRedirect("/kursovoy/chapters");
            } else {
                log.trace("Login failed!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (UserDaoException e){
            log.trace(e);
        } catch (ClassNotFoundException e) {
            log.trace(e);
        } catch (NamingException e){
            log.trace(e);
        } catch (SQLException e){
            log.trace(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
