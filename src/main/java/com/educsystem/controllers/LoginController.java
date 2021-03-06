package com.educsystem.controllers;

import com.educsystem.common.exceptions.UserDaoException;
import com.educsystem.common.security.Crypt;
import com.educsystem.interfaces.UserServiceInf;
import com.educsystem.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Denis on 07.03.2017.
 */
@Controller
public class LoginController {
    private UserServiceInf userService;

    public LoginController(UserServiceInf userService) {
        this.userService = userService;
    }

    private static final Logger log = Logger.getLogger(LoginController.class);
    public static int userlevel;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
            return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
/*    public String getLogin(@RequestParam("login") String login,
                           @RequestParam("password") String oldPassword,
                           ModelAndView modelAndView,
                           HttpSession session) throws UserDaoException,
                                                ClassNotFoundException,
                                                NamingException,
                                                SQLException{
        String password = Crypt.crypting(oldPassword);
        if (userService.authorize(login, password)) {
            log.trace("Login successfull");
            sessionID = session.getId();
            modelAndView.addObject("session", sessionID);
            log.trace("Session ID: " + sessionID);
            return "redirect:chapters";
        } else {
            log.trace("Login failed");
            modelAndView.addObject("log_fail", "");
            return "login";
        }
    }
*/
}
