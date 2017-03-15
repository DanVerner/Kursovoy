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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Map;

/**
 * Created by Denis on 07.03.2017.
 */
@Controller
public class RegistrationController {
    private UserServiceInf userService;

    @Autowired
    public RegistrationController(UserServiceInf userService) {
        this.userService = userService;
    }

    private static Logger log = Logger.getLogger(RegistrationController.class);

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegistration(@RequestParam Map<String,String> requestParams) throws ClassNotFoundException, UserDaoException{
        String login = requestParams.get("login");
        String email = requestParams.get("email");
        String oldPassword = requestParams.get("password");
        String password = Crypt.crypting(oldPassword);

        if(userService.registration(login, email, password)){
            log.trace("Registration successfull!");
            return "redirect:login";
        } else {
            log.trace("Registration failed!");
            return "registration";
        }
    }
}
