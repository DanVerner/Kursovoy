package com.educsystem.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by Denis on 15.03.2017.
 */
@ControllerAdvice
public class ExceptionController {
    public static final Logger log = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler
    public ModelAndView handleAllExceptions(Exception e) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "Лекция не была найдена!");
        log.error(e);
        return model;
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ModelAndView handleClassNotFoundExc(ClassNotFoundException e){
        ModelAndView model = new ModelAndView("error");
        model.addObject("CNFE", "Сlass not found!");
        log.error(e);
        return model;
    }

    @ExceptionHandler
    public ModelAndView handleSQLException(SQLException e) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("SQLError", "Ошибка с базой данных!");
        log.error(e);
        return model;
    }
}
